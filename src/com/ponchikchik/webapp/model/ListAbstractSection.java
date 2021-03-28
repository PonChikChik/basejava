package com.ponchikchik.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListAbstractSection extends AbstractSection {
    private final List<String> list;

    public ListAbstractSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListAbstractSection that = (ListAbstractSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
