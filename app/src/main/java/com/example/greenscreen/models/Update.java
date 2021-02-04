
package com.example.greenscreen.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Update {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("post")
    @Expose
    private String post;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Update() {
    }

    /**
     * 
     * @param post
     * @param name
     * @param id
     */
    public Update(Integer id, String name, String post) {
        super();
        this.id = id;
        this.name = name;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
