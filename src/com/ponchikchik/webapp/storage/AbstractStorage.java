package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume resume) {
        Object searchKey = getSearchKeyOrNotExistStorage(resume.getUuid());

        doUpdate(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getSearchKeyOrExistStorage(resume.getUuid());

        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKeyOrNotExistStorage(uuid);

        return doGet(searchKey, uuid);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKeyOrNotExistStorage(uuid);

        doDelete(searchKey, uuid);
    }

    private Object getSearchKeyOrNotExistStorage(String uuid) {
        Object searchKey = findResumeIndex(uuid);

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }

    private Object getSearchKeyOrExistStorage(String uuid) {
        Object searchKey = findResumeIndex(uuid);

        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }

    protected abstract Object findResumeIndex(String uuid);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract Resume doGet(Object searchKey, String uuid);

    protected abstract void doDelete(Object searchKey, String uuid);

    protected abstract boolean isExist(Object searchKey);
}
