package com.ponchikchik.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationList extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<Organization> organizationList;

    public OrganizationList(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationList(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizationList = organizations;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationList that = (OrganizationList) o;
        return Objects.equals(organizationList, that.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }

    @Override
    public String toString() {
        return organizationList.toString();
    }
}
