package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = findResumeIndex(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Error: Resume not found");
        }
    }

    public void save(Resume resume) {
        int index = findResumeIndex(resume.getUuid());

        if (index != -1) {
            System.out.println("Error: You can't create duplicate resume");
        } else if (size == storage.length) {
            System.out.println("Error: Storage overflow");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);

        if (index != -1) {
            return storage[index];
        }

        System.out.println("Error: Resume not found");
        return null;
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);

        if (index == -1) {
            System.out.println("Error: Resume not found");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
            size--;
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


    private int findResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume resume = storage[i];

            if (resume.getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }
}
