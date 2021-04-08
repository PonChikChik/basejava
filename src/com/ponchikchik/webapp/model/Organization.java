package com.ponchikchik.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private String companyName;
    private String website;
    private List<Experience> experienceList;

    public Organization(String companyName, String website, List<Experience> experienceList) {
        this.companyName = companyName;
        this.website = website;
        this.experienceList = experienceList;
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

    public List<Experience> getOrganizationInformationList() {
        return experienceList;
    }

    public void setOrganizationInformationList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(companyName, that.companyName) && Objects.equals(website, that.website) && Objects.equals(experienceList, that.experienceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, website, experienceList);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "companyName='" + companyName + '\'' +
                ", website='" + website + '\'' +
                ", organizationInformationList=" + experienceList +
                '}';
    }
}
