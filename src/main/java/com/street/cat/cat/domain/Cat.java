package com.street.cat.cat.domain;

import com.street.cat.common.model.NamedEntity;
import com.street.cat.location.domain.Location;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * 고양이
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cat extends NamedEntity {
    private Integer age; // 나이
    private String gender; // 성별
    private String profileImage; // 프로필 사진
    private String neutralizationStatus; // 중성화 여부
    private Location location; // 고양이 발견 위치

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cat")
    private Set<CatPoint> catPoints = new HashSet<>();

    public void setAge(Integer age) {
        if(age <= 0 || age >= 100) throw new IllegalArgumentException();
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setNeutralizationStatus(String neutralizationStatus) {
        this.neutralizationStatus = neutralizationStatus;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void addCatPoints(CatPoint catPoint) {
        getCatPoints().add(catPoint);
        catPoint.setCat(this);
    }

    public static Cat of(Integer age, String gender, String profileImage, String neutralizationStatus, Location location){
        Cat cat = new Cat();

        cat.setAge(age);
        cat.setGender(gender);
        cat.setProfileImage(profileImage);
        cat.setNeutralizationStatus(neutralizationStatus);
        cat.setLocation(location);

        return cat;
    }
}
