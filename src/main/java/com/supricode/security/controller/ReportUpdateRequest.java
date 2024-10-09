package com.supricode.security.controller;

public class ReportUpdateRequest {
    private String category_report;
    private String description;
    private String location;
    private String time;
    private String status;
    private String description_admin;

    // Getters and Setters
    public String getCategory_report() { return category_report; }
    public void setCategory_report(String category_report) { this.category_report = category_report; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription_admin() { return description_admin; }
    public void setDescription_admin(String description_admin) { this.description_admin = description_admin; }
}
