package com.street.cat.cat.domain;

import com.street.cat.common.model.BaseEntity;
import com.street.cat.feed.domain.Feed;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CatPoint extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private Point point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;


    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public static CatPoint of(Cat cat, Point point, Feed feed) {
        CatPoint catPoint = new CatPoint();
        catPoint.setPoint(point);
        catPoint.setCat(cat);
        catPoint.setFeed(feed);
        return catPoint;
    }
}
