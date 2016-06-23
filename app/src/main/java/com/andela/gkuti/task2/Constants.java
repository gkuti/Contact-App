package com.andela.gkuti.task2;

public enum Constants {
    DATABASE_NAME("Contacts"),
    TABLE_NAME("MyContact"),
    NAME_COLUMN("Name"),
    ADDRESS_COLUMN("Address"),
    PHONE_COLUMN("Phone"),
    EMAIL_COLUMN("Email"),
    ID_COLUMN("_id");
    String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
