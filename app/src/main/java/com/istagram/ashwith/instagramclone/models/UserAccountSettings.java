package com.istagram.ashwith.instagramclone.models;

public class UserAccountSettings {

    private String description;
    private long followers;
    private long following;
    private long posts;
    private String display_name;
    private String profile_image;
    private String user_name;
    private String website;

    public UserAccountSettings(String description, long followers,
                               long following, long posts, String display_name,
                               String profile_image, String user_name, String website) {
        this.description = description;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.display_name = display_name;
        this.profile_image = profile_image;
        this.user_name = user_name;
        this.website = website;
    }

    public UserAccountSettings() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
