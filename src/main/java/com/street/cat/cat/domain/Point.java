package com.street.cat.cat.domain;

import com.street.cat.common.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends BaseEntity {
    private String Characteristics;

    public static Point of(String Characteristics) {
        Point point = new Point();
        point.Characteristics = Characteristics;

        return point;
    }
}
