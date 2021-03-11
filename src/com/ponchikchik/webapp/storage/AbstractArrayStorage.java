package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;

import java.util.Arrays;

abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }

        storage[index] = resume;
    }

    @Override
    protected void doSave(int index, Resume resume) {
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }

        insertResume(index, resume);
        size++;
    }

    @Override
    protected Resume doGet(int index, String uuid) {
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        return storage[index];
    }

    @Override
    protected void doDelete(int index, String uuid) {
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        removeResume(index);
        storage[size - 1] = null;
        size--;
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

    protected abstract void removeResume(int index);

    protected abstract void insertResume(int index, Resume resume);
}
