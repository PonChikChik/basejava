package com.ponchikchik.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private String companyName;
    private String website;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Organization(String companyName, String website, String title, String description, LocalDate startDate, LocalDate endDate) {
        this.companyName = companyName;
        this.website = website;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(companyName, that.companyName) && Objects.equals(website, that.website) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, website, title, description, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "companyName='" + companyName + '\'' +
                ", website='" + website + '\'' +
                ", title='" + title + '\'' +
                ", description=" + description +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
