package com.Spring.codsharingplatforme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table
public class SharedCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sharedCode_seq")
    @SequenceGenerator(name = "sharedCode_seq",
            sequenceName = "sharedCode_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "category_id")
    private Long id;
    @Column
    String code;
    @Column
    Long allowViewTime;
    @Column
    Integer views;
    @Column
    LocalDateTime createdDate;

    public SharedCode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAllowViewTime() {
        return allowViewTime;
    }

    public void setAllowViewTime(Long timeLeft) {
        this.allowViewTime = timeLeft;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "SharedCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", timeLeft=" + allowViewTime +
                ", views=" + views +
                ", createdDate=" + createdDate +
                '}';
    }

    @JsonIgnore
    public boolean isPublic() {
        return this.views == 0 && this.allowViewTime == 0;
    }
    @JsonIgnore
    public Long getSecondPassedSinceCrated() {
        return ChronoUnit.SECONDS.between(this.createdDate, LocalDateTime.now());
    }
    @JsonIgnore
    public boolean isExpired() {
        return (getSecondPassedSinceCrated() > this.getAllowViewTime()) || (this.getViews() == 500);
    }

    public Long getTimeLeft() {
        return this.getAllowViewTime() - getSecondPassedSinceCrated();
    }
}
