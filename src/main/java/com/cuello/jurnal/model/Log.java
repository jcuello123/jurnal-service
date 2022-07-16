package com.cuello.jurnal.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "log")
public class Log {
    private int id;
    private String text;
    private LocalDate date;
    private String username;

    public Log() {
    }

    public Log(String text, LocalDate date, String username) {
        this.text = text;
        this.date = date;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String log) {
        this.text = log;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", username='" + username + '\'' +
                '}';
    }
}
