package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.storage.serialize.ObjectStreamFileSerializer;

class ObjectStreamFileSerializerTest extends AbstractStorageTest {
    public ObjectStreamFileSerializerTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamFileSerializer()));
    }
}
