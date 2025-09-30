package com.multi.model.dto;

import java.time.LocalDateTime;

public class TravelDTO {

    private long no;
    private String title;
    private String district;
    private String description;
    private String address;
    private String phone;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public TravelDTO() {}

    public TravelDTO(long no, String district, String title, String description, String address, String phone, LocalDateTime createAt, LocalDateTime updateAt) {
        this.no = no;
        this.district = district;
        this.title = title;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "TravelDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", district='" + district + '\'' +
//                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
