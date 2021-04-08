package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    public AbstractPathStorage(String directory) {
        this.directory = Paths.get(directory);
        Objects.requireNonNull(this.directory, "directory must not be null");

        if (!Files.isDirectory(this.directory) || !Files.isWritable(this.directory)) {
            throw new IllegalArgumentException(directory + " is not directory or is not writable");
        }
    }

    @Override
    protected Path findSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try(OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.WRITE)) {
            doWrite(new BufferedOutputStream(outputStream), resume);
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Path path, Resume resume) {
        try(OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE_NEW)) {
            doWrite(new BufferedOutputStream(outputStream), resume);
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume doGet(Path path, String uuid) {
        try(InputStream inputStream = Files.newInputStream(path, StandardOpenOption.CREATE_NEW)) {
            return doRead(new BufferedInputStream(inputStream));
        } catch (IOException e) {
            throw new StorageException("Path write error", getFileName(path), e);
        }
    }

    @Override
    protected void doDelete(Path path, String uuid) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(path), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected List<Resume> doCopyAllResumes() {
        return getFilesList().map((Path path) -> doGet(path, null)).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(path -> doDelete(path, ""));
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }
    }

    protected abstract void doWrite(OutputStream file, Resume resume) throws IOException;

    protected abstract Resume doRead(InputStream file) throws IOException;
}