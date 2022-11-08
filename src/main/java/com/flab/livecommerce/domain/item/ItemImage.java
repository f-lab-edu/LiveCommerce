package com.flab.livecommerce.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // item과 item image를 분리된 aggregate로 설계했기 때문에 id로 참조
    private Long itemId;

    // 이미지 순서 - 썸네일 이미지 ordering=0
    @Column(columnDefinition = "Tinyint")
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
