package com.flab.livecommerce.domain.item;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemImage {

    private Long id;

    private Long itemId;

    // 이미지 순서 - 썸네일 이미지 ordering=1
    private int ordering;

    // 썸네일(필수) 이미지 여부
    private boolean basic;

    // 이미지 경로
    private String path;

    @Builder
    public ItemImage(int ordering, boolean basic, String path) {
        this.ordering = ordering;
        this.basic = basic;
        this.path = path;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
