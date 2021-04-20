package com.ponchikchik.webapp.storage.serialize;

import com.ponchikchik.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dataOutputStream, contacts.entrySet(), (entry) -> {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeCollection(dataOutputStream, sections.entrySet(), (entry) -> {
                SectionType sectionName = entry.getKey();
                dataOutputStream.writeUTF(sectionName.name());
                AbstractSection sectionValue = entry.getValue();

                switch (sectionName) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        dataOutputStream.writeUTF(((TextSection) sectionValue).getText());
                        break;
                    }
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        writeCollection(dataOutputStream, ((ListSection) sectionValue).getList(), dataOutputStream::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dataOutputStream, ((OrganizationList) sectionValue).getOrganizationList(), (organization) -> {
                            dataOutputStream.writeUTF(Objects.requireNonNullElse(organization.getWebsite().getName(), "null"));
                            dataOutputStream.writeUTF(Objects.requireNonNullElse(organization.getWebsite().getUrl(), "null"));

                            writeCollection(dataOutputStream, organization.getExperienceList(), (experience) -> {
                                dataOutputStream.writeUTF(Objects.requireNonNullElse(experience.getTitle(), "null"));
                                dataOutputStream.writeUTF(Objects.requireNonNullElse(experience.getDescription(), "null"));

                                LocalDate startDate = experience.getStartDate();
                                if (startDate != null) {
                                    dataOutputStream.writeUTF(prepareLocalDate(experience.getStartDate()));
                                } else {
                                    dataOutputStream.writeUTF(prepareLocalDate(LocalDate.of(0, 1, 1)));
                                }

                                LocalDate endDate = experience.getEndDate();
                                if (endDate != null) {
                                    dataOutputStream.writeUTF(prepareLocalDate(experience.getEndDate()));
                                } else {
                                    dataOutputStream.writeUTF(prepareLocalDate(LocalDate.of(0, 1, 1)));
                                }
                            });
                        });
                        break;
                    default:
                        throw new Error("Unknown category");
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readCollection(dataInputStream, () -> {
                String contactTypeName = dataInputStream.readUTF();
                resume.addContact(ContactType.valueOf(contactTypeName), dataInputStream.readUTF());
            });
            readCollection(dataInputStream, () -> {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                resume.addSection(sectionType, readSection(dataInputStream, sectionType));
            });

            return resume;
        }
    }

    private interface Processor {
        void process() throws IOException;
    }

    private interface Reader<T> {
        T read() throws IOException;
    }

    private interface Writer<T> {
        void write(T item) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dataOutputStream, Collection<T> collection, Writer<T> writer) throws IOException {
        dataOutputStream.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void readCollection(DataInputStream dataInputStream, Processor processor) throws IOException {
        int size = dataInputStream.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private <T> List<T> readList(DataInputStream dis, Reader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }

        return list;
    }

    private AbstractSection readSection(DataInputStream dataInputStream, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dataInputStream.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATION:
                return new ListSection(readList(dataInputStream, dataInputStream::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationList(
                        readList(dataInputStream, () -> {
                            Link homePage = new Link(revertFromFile(dataInputStream.readUTF()), revertFromFile(dataInputStream.readUTF()));
                            return new Organization(
                                    homePage,
                                    readList(dataInputStream, () -> new Organization.Experience(
                                            revertFromFile(dataInputStream.readUTF()),
                                            revertFromFile(dataInputStream.readUTF()),
                                            revertToLocalDate(dataInputStream.readUTF()),
                                            revertToLocalDate(dataInputStream.readUTF())
                                    ))
                            );
                        }));
            default:
                throw new Error("Unknown category");
        }
    }

    private String prepareLocalDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private LocalDate revertToLocalDate(String localDate) {
        if ("0000-01-01".equals(localDate)) {
            return null;
        }
        return LocalDate.parse(localDate);
    }

    private String revertFromFile(String value) {
        if (value.equals("null")) {
            return null;
        }

        return value;
    }
}
