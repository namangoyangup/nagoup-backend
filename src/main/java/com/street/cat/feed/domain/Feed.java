package com.street.cat.feed.domain;

import com.street.cat.common.model.BaseEntity;
import com.street.cat.user.domain.User;
import lombok.Getter;

import javax.persistence.*;

/**
 * 게시글
 */
@Entity
@Getter
public class Feed extends BaseEntity {

    private String content; // 피드 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 피드를 업로드한 유저 정보

    private Long notCatReport; // 고양이가 아니에요 신고
    private Long heart; // 좋아요 개수
}
