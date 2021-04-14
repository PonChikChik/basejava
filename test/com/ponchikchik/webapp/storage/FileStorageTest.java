package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.storage.serialize.ObjectStreamSerializer;

class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
