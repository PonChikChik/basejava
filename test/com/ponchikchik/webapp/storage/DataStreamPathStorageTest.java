package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.storage.serialize.DataStreamSerializer;

class DataStreamPathStorageTest extends AbstractStorageTest {
    public DataStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
