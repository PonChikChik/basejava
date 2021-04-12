package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.storage.serialize.ObjectStreamSerializer;

class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}
