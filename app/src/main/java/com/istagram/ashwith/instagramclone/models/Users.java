package com.istagram.ashwith.instagramclone.models;

public class Users {

    private String email_id;
    private String name;
    private String website;

    public Users(String email_id, String name, String website) {
        this.email_id = email_id;
        this.name = name;
        this.website = website;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
