package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;
import com.ponchikchik.webapp.storage.serialize.StreamSerializer;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final StreamSerializer streamSerializer;

    public FileStorage(File directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must not be null");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }

        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }

        this.directory = directory;
        this.streamSerializer = streamSerializer;
    }

    @Override
    public void clear() {
        File[] files = getFiles();

        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        File[] files = getFiles();
        return files.length;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            streamSerializer.doWrite(new BufferedOutputStream(new FileOutputStream(file)), resume);
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Don't create file", file.getName(), e);
        }

        doUpdate(file, resume);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = getFiles();
        List<Resume> list = new ArrayList<>(files.length);

        for (File file : files) {
            list.add(doGet(file));
        }

        return list;
    }

    private File[] getFiles() {
        File[] files = directory.listFiles();

        if (files == null) {
            throw new StorageException("Directory read error", "");
        }

        return files;
    }
}
