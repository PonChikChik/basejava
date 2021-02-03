package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.model.Resume;

import java.util.Arrays;

abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = findResumeIndex(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Error: Resume " + resume.getUuid() + " not found");
        }
    }

    public void save(Resume resume) {
        int index = findResumeIndex(resume.getUuid());

        if (index >= 0) {
            System.out.println("Error: You can't create duplicate resume " + resume.getUuid());
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Error: Storage overflow");
        } else {
            insertItem(index, resume);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);

        if (index == -1) {
            System.out.println("Error: Resume " + uuid + " not found");
            return null;
        }

        return storage[index];
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);

        if (index == -1) {
            System.out.println("Error: Resume " + uuid + " not found");
        } else {
            removeItem(index);
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int findResumeIndex(String uuid);

    protected abstract void removeItem(int index);

    protected abstract void insertItem(int index, Resume resume);
}
