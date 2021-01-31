package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume resume, String newUuid) {
        Resume updateResume = findResume(resume.getUuid());

        if (updateResume != null) {
            updateResume.setUuid(newUuid);
        } else {
            System.out.println("Error: Resume not found");
        }
    }

    public void save(Resume resume) {
        Resume resumeInStorage = findResume(resume.getUuid());

        if (resumeInStorage != null) {
            System.out.println("Error: You can't create duplicate resume");
        } else if (size == storage.length) {
            System.out.println("Error: Storage overflow");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        Resume resume = findResume(uuid);

        if (resume == null) {
            System.out.println("Error: Resume not found");
        }

        return resume;

    }

    public void delete(String uuid) {
        Resume deleteResume = findResume(uuid);

        if (deleteResume == null) {
            System.out.println("Error: Resume not found");
        } else {
            for (int i = 0; i < size; i++) {
                Resume resume = storage[i];

                if (resume.getUuid().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, size - 1);
                    size--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }


    private Resume findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume resume = storage[i];

            if (resume.getUuid().equals(uuid)) {
                return resume;
            }
        }

        return null;
    }
}
