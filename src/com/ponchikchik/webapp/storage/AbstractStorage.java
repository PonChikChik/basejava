package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SearchKey> implements Storage {
    @Override
    public void update(Resume resume) {
        SearchKey searchKey = getSearchKeyIfExist(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        SearchKey searchKey = getSearchKeyIfNotExist(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        SearchKey searchKey = getSearchKeyIfExist(uuid);
        return doGet(searchKey, uuid);
    }

    @Override
    public void delete(String uuid) {
        SearchKey searchKey = getSearchKeyIfExist(uuid);
        doDelete(searchKey, uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = doCopyAllResumes();
        Collections.sort(resumeList);

        return resumeList;
    }

    private SearchKey getSearchKeyIfExist(String uuid) {
        SearchKey searchKey = findSearchKey(uuid);

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }

    private SearchKey getSearchKeyIfNotExist(String uuid) {
        SearchKey searchKey = findSearchKey(uuid);

        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }

    protected abstract SearchKey findSearchKey(String uuid);

    protected abstract void doUpdate(SearchKey searchKey, Resume resume);

    protected abstract void doSave(SearchKey searchKey, Resume resume);

    protected abstract Resume doGet(SearchKey searchKey, String uuid);

    protected abstract void doDelete(SearchKey searchKey, String uuid);

    protected abstract boolean isExist(SearchKey searchKey);

    protected abstract List<Resume> doCopyAllResumes();
}
