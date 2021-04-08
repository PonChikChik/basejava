package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.storage.serialize.ObjectStreamPathSerializer;

class ObjectStreamPathSerializerTest extends AbstractStorageTest {
    public ObjectStreamPathSerializerTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamPathSerializer()));
    }
}
