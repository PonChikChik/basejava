package com.ponchikchik.webapp.storage.serialize;

import com.ponchikchik.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    void doWrite(OutputStream file, Resume resume) throws IOException;

    Resume doRead(InputStream file) throws IOException;
}
