package com.ponchikchik.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Номер телефона"),
    SKYPE("SKYPE"),
    EMAIL("Почта"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }
}
