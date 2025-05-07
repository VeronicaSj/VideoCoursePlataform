package com.example.apirest.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="image_id")
    private Integer imageID;

    @Column(name="name")
    private String type;

    @Lob
    @Column(name = "picByte", length = 5000)
    private byte[] picByte;

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public Image() {
    }

    public Image(Integer imageID, String type, byte[] picByte) {
        this.imageID = imageID;
        this.type = type;
        this.picByte = picByte;
    }

    
}