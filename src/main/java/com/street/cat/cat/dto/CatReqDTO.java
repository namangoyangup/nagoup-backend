package com.street.cat.cat.dto;

import com.street.cat.location.domain.Location;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CatReqDTO {
    private Integer age; // 나이
    private String gender; // 성별
    private String profileImage; // 프로필 사진
    private String neutralizationStatus; // 중성화 여부
    private Location location; // 고양이 발견 위치
    private List<String> catPoints; // 고앵이 특성

    @Builder
    public CatReqDTO(Integer age, String gender, String profileImage, String neutralizationStatus, Location location, List<String> catPoints) {
        this.age = age;
        this.gender = gender;
        this.profileImage = profileImage;
        this.neutralizationStatus = neutralizationStatus;
        this.location = location;
        this.catPoints = catPoints;
    }
}
