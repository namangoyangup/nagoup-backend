package com.street.cat.cat.dto;

import com.street.cat.location.domain.Location;
import lombok.Builder;
import lombok.Data;

@Data
public class CatResDTO {
    private Integer age; // 나이
    private String gender; // 성별
    private String profileImage; // 프로필 사진
    private String neutralizationStatus; // 중성화 여부
    private Location location; // 고양이 발견 위치

    @Builder
    public CatResDTO(Integer age, String gender, String profileImage, String neutralizationStatus, Location location) {
        this.age = age;
        this.gender = gender;
        this.profileImage = profileImage;
        this.neutralizationStatus = neutralizationStatus;
        this.location = location;
    }
}
