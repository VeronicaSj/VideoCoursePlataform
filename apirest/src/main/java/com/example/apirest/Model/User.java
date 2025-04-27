package com.example.apirest.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="User")
public class User {
    
    @Id
    int id;

    @Column(name="name", length = 20)
    String name;

    @Column(name="mail", length = 20)
    String mail;

    @Column(name="pw", length = 20)
    String pw;

    @Column(name="date", length = 20)
    Date date;

    @Column(name="img", length = 20)
    String img;

    @Column(name="type", length = 20)
    String type;

    @Column(name="last_log", length = 20)
    Date lastLog;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastLog() {
        return lastLog;
    }

    public void setLastLog(Date lastLog) {
        this.lastLog = lastLog;
    }
}
