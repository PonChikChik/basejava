package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.storage.serialize.XmlStreamSerializer;

class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
