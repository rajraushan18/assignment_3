package com.nagarro.javaAdvance.assignment3.model;

import java.io.File;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue
    private int id;

    @Transient
    private File file;

    @Column(name = "Image_Name")
    private String imgName;

    private long imgSize;

    @Lob
    private byte[] imageData;

    @ManyToOne
    private User user;

    public int getId() {
        return id;
    }

    public String getImgName() {
        return imgName;
    }

    public long getImgSize() {
        return imgSize;
    }

    public void setImg(File file) {
        this.file = file;
        this.imgSize = file.length();
        this.imgName = file.getName();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

}
