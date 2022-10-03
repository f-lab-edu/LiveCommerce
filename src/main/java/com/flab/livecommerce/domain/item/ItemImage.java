package com.flab.livecommerce.domain.item;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemImage {

    private Long itemId;

    // 이미지 순서 - 썸네일 이미지 ordering=1
    private int ordering;

    // 썸네일(필수) 이미지 여부
    private boolean isThumbnail;

    // 이미지 경로
    private String url;

    @Builder
    public ItemImage(int ordering, boolean isThumbnail, String url) {
        this.ordering = ordering;
        this.isThumbnail = isThumbnail;
        this.url = url;
    }
}
