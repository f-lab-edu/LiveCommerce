package com.flab.livecommerce.domain.item;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ItemOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "Tinyint")
    private Integer ordering;
    //기본 옵션 여부
    private boolean basic;
    //배타 선택 여부
    private boolean exclusive;
    //최소 선택 개수
    private int minimumChoice;
    //최대 선택 개수
    private int maximumChoice;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @OneToMany(mappedBy = "itemOptionGroup", cascade = CascadeType.ALL)
    private List<ItemOption> itemOptions = new ArrayList<>();

    @Builder
    public ItemOptionGroup(
        String name,
        Integer ordering,
        boolean basic,
        boolean exclusive,
        int minimumChoice,
        int maximumChoice,
        List<ItemOption> itemOptions
    ) {
        if (name == null && name.length() == 0) {
            throw new InvalidParameterException("ItemOptionGroup.name");
        }
        if (ordering == null) {
            throw new InvalidParameterException("ItemOptionGroup.itemId");
        }
        this.item = getItem();
        this.name = name;
        this.ordering = ordering;
        this.basic = basic;
        this.exclusive = exclusive;
        this.minimumChoice = minimumChoice;
        this.maximumChoice = maximumChoice;
        this.itemOptions = itemOptions;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemOptionGroup addItemOption(ItemOption itemOption) {
        this.itemOptions.add(itemOption);
        itemOption.setItemOptionGroup(this);
        return this;
    }

    public ItemOptionGroup setId(Long id) {
        this.id = id;
        return this;
    }

}
