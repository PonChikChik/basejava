package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }

        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }

        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                doDelete(file, "");
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();

        if (list == null) {
            throw new StorageException("Directory read error", null);
        }

        return list.length;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            doUpdate(file, resume);
        } catch (IOException e) {
            throw new StorageException("Don't create file", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file, String uuid) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file, String uuid) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> doCopyAllResumes() {
        File[] files = directory.listFiles();

        if (files == null) {
            throw new StorageException("Directory read error", null);
        }

        List<Resume> list = new ArrayList<>(files.length);

        for (File file : files) {
            list.add(doGet(file, ""));
        }

        return list;
    }

    protected abstract void doWrite(File file, Resume resume) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
