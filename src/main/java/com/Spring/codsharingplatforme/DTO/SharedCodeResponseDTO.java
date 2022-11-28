package com.Spring.codsharingplatforme.DTO;

import com.Spring.codsharingplatforme.model.SharedCode;

public class SharedCodeResponseDTO {

    private SharedCode sharedCode;
    private Long timeLeft;
private Boolean isPresent;

    public SharedCodeResponseDTO(SharedCode sharedCode, Long timeLeft, Boolean isPresent) {
        this.sharedCode = sharedCode;
        this.timeLeft = timeLeft;
        this.isPresent = isPresent;
    }

    @Override
    public String toString() {
        return "SharedCodeResponseDTO{" +
                "sharedCode=" + sharedCode +
                ", timeLeft=" + timeLeft +
                ", isPresent=" + isPresent +
                '}';
    }

    public SharedCode getSharedCode() {
        return sharedCode;
    }

    public void setSharedCode(SharedCode sharedCode) {
        this.sharedCode = sharedCode;
    }

    public Long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }
}
