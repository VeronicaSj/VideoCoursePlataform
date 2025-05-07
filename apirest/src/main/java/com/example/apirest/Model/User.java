package com.example.apirest.Model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User
    {
    
    @Id
    @Column(name="name")
    String name;

    @Column(name="mail")
    String mail;

    @Column(name="date")
    Date date;

    @Column(name="img")
    String img;

    @Column(name="type")
    String type;

    @Column(name="last_log")
    Date lastLog;

    public User() {
        
    }

    

    public User( String name, String mail, Date date, String img, String type, Date lastLog) {
        this.name = name;
        this.mail = mail;
        this.date = date;
        this.img = img;
        this.type = type;
        this.lastLog = lastLog;
    }

    public User( String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.name, other.name);
    }
    
}
