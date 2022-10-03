package com.flab.livecommerce.domain.item;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemImage {

    private Long id;

    private Long itemId;

    // 이미지 순서
    private int order;

    // 썸네일(필수) 이미지 여부
    private boolean basic;
    // 이미지 파일 이름
    private String name;

    // 이미지 경로
    private String path;


    @Builder
    public ItemImage(Long id, String name, String path, Long itemId) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.itemId = itemId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
