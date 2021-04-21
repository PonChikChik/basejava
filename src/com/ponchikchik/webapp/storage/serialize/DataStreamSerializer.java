package com.ponchikchik.webapp.storage.serialize;

import com.ponchikchik.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), (entry) -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeCollection(dos, sections.entrySet(), (entry) -> {
                SectionType sType = entry.getKey();
                dos.writeUTF(sType.name());
                AbstractSection sectionValue = entry.getValue();

                switch (sType) {
                    case PERSONAL:
                    case OBJECTIVE: {
                        dos.writeUTF(((TextSection) sectionValue).getText());
                        break;
                    }
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        writeCollection(dos, ((ListSection) sectionValue).getList(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganizationList) sectionValue).getOrganizationList(), (organization) -> {
                            dos.writeUTF(organization.getWebsite().getName());
                            dos.writeUTF(Objects.requireNonNullElse(organization.getWebsite().getUrl(), "null"));

                            writeCollection(dos, organization.getExperienceList(), (experience) -> {
                                dos.writeUTF(experience.getTitle());
                                dos.writeUTF(Objects.requireNonNullElse(experience.getDescription(), "null"));
                                dos.writeUTF(prepareLocalDate(experience.getStartDate()));
                                dos.writeUTF(prepareLocalDate(experience.getEndDate()));
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
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readCollection(dis, () -> {
                String contactTypeName = dis.readUTF();
                resume.addContact(ContactType.valueOf(contactTypeName), dis.readUTF());
            });
            readCollection(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
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

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void readCollection(DataInputStream dis, Processor processor) throws IOException {
        int size = dis.readInt();
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

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATION:
                return new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationList(
                        readList(dis, () -> {
                            Link homePage = new Link(revertFromFile(dis.readUTF()), revertFromFile(dis.readUTF()));
                            return new Organization(
                                    homePage,
                                    readList(dis, () -> new Organization.Experience(
                                            revertFromFile(dis.readUTF()),
                                            revertFromFile(dis.readUTF()),
                                            revertToLocalDate(dis.readUTF()),
                                            revertToLocalDate(dis.readUTF())
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
        return "0000-01-01".equals(localDate) ? null : LocalDate.parse(localDate);
    }

    private String revertFromFile(String value) {
        return value.equals("null") ? null : value;
    }
}
