package com.flab.livecommerce.domain.item;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;
    private String name;
    @Column(columnDefinition = "Tinyint")
    private Integer ordering;
    @Column(columnDefinition = "Integer")
    private Long price;

    @Builder
    public ItemOption(String name, Integer ordering, Long price) {
        if (name == null && name.length() == 0) {
            throw new InvalidParameterException("ItemOptionGroup.name");
        }
        if (ordering == null) {
            throw new InvalidParameterException("ItemOptionGroup.ordering");
        }
        if (price == null) {
            throw new InvalidParameterException("ItemOptionGroup.price");
        }
        this.item = this.getItem();
        this.itemOptionGroup = this.getItemOptionGroup();
        this.name = name;
        this.ordering = ordering;
        this.price = price;
    }

    public void setItemOptionGroup(ItemOptionGroup itemOptionGroup) {
        this.itemOptionGroup = itemOptionGroup;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemOption setId(Long id) {
        this.id = id;
        return this;
    }

}
