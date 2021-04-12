package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SearchKey> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Resume resume) {
        LOG.info("Update" + resume);
        SearchKey searchKey = getSearchKeyIfExist(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save" + resume);
        SearchKey searchKey = getSearchKeyIfNotExist(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get" + uuid);
        SearchKey searchKey = getSearchKeyIfExist(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Update" + uuid);
        SearchKey searchKey = getSearchKeyIfExist(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumeList = doCopyAll();
        Collections.sort(resumeList);

        return resumeList;
    }

    private SearchKey getSearchKeyIfExist(String uuid) {
        SearchKey searchKey = findSearchKey(uuid);

        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }

    private SearchKey getSearchKeyIfNotExist(String uuid) {
        SearchKey searchKey = findSearchKey(uuid);

        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }

    protected abstract SearchKey findSearchKey(String uuid);

    protected abstract void doUpdate(SearchKey searchKey, Resume resume);

    protected abstract void doSave(SearchKey searchKey, Resume resume);

    protected abstract Resume doGet(SearchKey searchKey);

    protected abstract void doDelete(SearchKey searchKey);

    protected abstract boolean isExist(SearchKey searchKey);

    protected abstract List<Resume> doCopyAll();
}
