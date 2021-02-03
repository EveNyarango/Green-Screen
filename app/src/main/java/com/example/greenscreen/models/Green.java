
package com.example.greenscreen.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Green implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;
    @SerializedName("createdat")
    @Expose
    private String createdat;
    private final static long serialVersionUID = -7105607092608756392L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Green() {
    }

    /**
     * 
     * @param createdat
     * @param imageurl
     * @param bio
     * @param location
     * @param id
     * @param email
     * @param username
     */
    public Green(Integer id, String username, String bio, String location, String email, String imageurl, String createdat) {
        super();
        this.id = id;
        this.username = username;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.imageurl = imageurl;
        this.createdat = createdat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

}
