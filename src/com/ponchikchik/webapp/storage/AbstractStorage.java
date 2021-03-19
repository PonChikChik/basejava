package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfNotExist(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKeyIfNotExist(uuid);
        return doGet(searchKey, uuid);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfNotExist(uuid);
        doDelete(searchKey, uuid);
    }

    private Object getSearchKeyIfNotExist(String uuid) {
        Object searchKey = findSearchKey(uuid);

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }

    private Object getSearchKeyIfExist(String uuid) {
        Object searchKey = findSearchKey(uuid);

        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }

    protected abstract Object findSearchKey(String uuid);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract Resume doGet(Object searchKey, String uuid);

    protected abstract void doDelete(Object searchKey, String uuid);

    protected abstract boolean isExist(Object searchKey);
}
