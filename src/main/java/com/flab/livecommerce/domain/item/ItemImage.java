package com.flab.livecommerce.domain.item;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemImage {

    private Long id;
    private Long itemId;

    // 이미지 순서 - 썸네일 이미지 ordering=1
    private int ordering;

    // 이미지 파일 이름 (uuid)
    private String name;

    // 이미지 경로
    private String url;

    @Builder
    public ItemImage(Long itemId, int ordering, String name, String url) {
        this.itemId = itemId;
        this.ordering = ordering;
        this.name = name;
        this.url = url;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
