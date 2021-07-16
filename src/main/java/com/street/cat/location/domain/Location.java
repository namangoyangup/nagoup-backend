package com.street.cat.location.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class Location {
    private String city;
    private String district;
    private String street;

    @Builder
    public Location(String city, String district, String street) {
        this.city = city;
        this.district = district;
        this.street = street;
    }
}
