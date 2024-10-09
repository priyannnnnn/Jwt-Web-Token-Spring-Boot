package com.supricode.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "reporttt")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Lob
//    @Convert(converter = ContentConverter.class)
    @JsonIgnore
    @Column(name = "picture", columnDefinition = "bytea")
    private byte[] picture;

    @Transient
    @JsonProperty("data")
    private String base64Data;

    private String category_report;

    private String description;

    private String location;

    private String time;

    private String status;

    private String description_admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public String getCategory_report() {
        return category_report;
    }

    public void setCategory_report(String category_report) {
        this.category_report = category_report;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription_admin() {
        return description_admin;
    }

    public void setDescription_admin(String description_admin) {
        this.description_admin = description_admin;
    }
    //    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
}
