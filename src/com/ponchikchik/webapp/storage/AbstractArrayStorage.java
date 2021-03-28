package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }

        insertResume(searchKey, resume);
        size++;
    }

    @Override
    protected Resume doGet(Integer searchKey, String uuid) {
        return storage[searchKey];
    }

    @Override
    protected void doDelete(Integer searchKey, String uuid) {
        removeResume(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected List<Resume> doCopyAllResumes() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    protected abstract void removeResume(int index);

    protected abstract void insertResume(int index, Resume resume);
}
