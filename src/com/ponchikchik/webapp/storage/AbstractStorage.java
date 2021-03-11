package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected Storage storage;

    @Override
    public void update(Resume resume) {
        int index = findResumeIndex(resume.getUuid());

        doUpdate(index, resume);
    }

    @Override
    public void save(Resume resume) {
        int index = findResumeIndex(resume.getUuid());

        doSave(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        return doGet(index, uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = findResumeIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        doDelete(index, uuid);
    }

    protected abstract int findResumeIndex(String uuid);

    protected abstract void doUpdate(int index, Resume resume);

    protected abstract void doSave(int index, Resume resume);

    protected abstract Resume doGet(int index, String uuid);

    protected abstract void doDelete(int index, String uuid);
}
