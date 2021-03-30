package com.ponchikchik.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private String companyName;
    private String website;
    private List<OrganizationInformation> organizationInformationList;

    public Organization(String companyName, String website, List<OrganizationInformation> organizationInformationList) {
        this.companyName = companyName;
        this.website = website;
        this.organizationInformationList = organizationInformationList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<OrganizationInformation> getOrganizationInformationList() {
        return organizationInformationList;
    }

    public void setOrganizationInformationList(List<OrganizationInformation> organizationInformationList) {
        this.organizationInformationList = organizationInformationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(companyName, that.companyName) && Objects.equals(website, that.website) && Objects.equals(organizationInformationList, that.organizationInformationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, website, organizationInformationList);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "companyName='" + companyName + '\'' +
                ", website='" + website + '\'' +
                ", organizationInformationList=" + organizationInformationList +
                '}';
    }
}
