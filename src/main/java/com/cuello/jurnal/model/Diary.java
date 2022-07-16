package com.cuello.jurnal.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "diary")
public class Diary {
    private int id;
    private String log;
    private Date date;
    private String username;

    public Diary() {
    }

    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
