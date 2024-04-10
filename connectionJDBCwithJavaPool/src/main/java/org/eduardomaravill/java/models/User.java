package org.eduardomaravill.java.models;

import java.time.LocalDateTime;

public class Users {

    private Long id;
    private String username;
    private String password;
    private LocalDateTime dateTime;

    public Users() {
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        this.dateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
