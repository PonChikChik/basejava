package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.storage.serialize.JsonStreamSerializer;

class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
