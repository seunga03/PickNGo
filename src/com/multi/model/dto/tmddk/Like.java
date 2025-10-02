package com.multi.model.dto.tmddk;

import java.time.LocalDateTime;

public class Like {
    private long seq;
    private String userId;
    private String no;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Like() {
    }

    public Like(long seq, String userId, String no, LocalDateTime createAt, LocalDateTime updateAt) {
        this.seq = seq;
        this.userId = userId;
        this.no = no;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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
        return seq + ". [" + no + "] " + updateAt ;
    }
}
