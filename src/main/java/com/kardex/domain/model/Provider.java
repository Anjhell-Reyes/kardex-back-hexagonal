package com.kardex.domain.model;

public class Provider {

    private Long id;
    private String companyName;
    private String imageUrl;
    private String email;
    private String phone;
    private Boolean status;
    private String description;

    public Provider(Long id, String companyName, String imageUrl, String email, String phone, Boolean status, String description) {
        this.id = id;
        this.companyName = companyName;
        this.imageUrl = imageUrl;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
