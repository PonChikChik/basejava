package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {
    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(InputStream inputStream) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}