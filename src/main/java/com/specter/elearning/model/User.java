package com.specter.elearning.model;

/**
 * Minimal placeholder so the module system finds the package.
 * Replace this with your full model later.
 */
public class User {
    private int id;
    private String username;
    private String role;

    public User() {}

    public User(int id, String username, String role) {
        this.id = id; this.username = username; this.role = role;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username=" + username + ", role=" + role + "}";
    }
}
