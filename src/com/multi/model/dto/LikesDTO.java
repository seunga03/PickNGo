package com.multi.model.dto;

import java.time.LocalDateTime;

public class LikesDTO {

    private Long seq;
    private String user_Id;
    private Long No;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public Long getNo() {
        return No;
    }

    public void setNo(Long no) {
        No = no;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return  "seq=" + seq +
                ", user_Id='" + user_Id + '\'' +
                ", No=" + No +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at;
    }
}
