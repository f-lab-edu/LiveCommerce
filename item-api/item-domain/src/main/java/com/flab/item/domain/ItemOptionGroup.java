package com.flab.item.domain;

import java.util.ArrayList;
import java.util.List;

public class ItemOptionGroup {

    private Long id;
    private Long itemId;
    private String name;
    private Integer ordering;
    //기본 옵션 여부
    private boolean basic;
    //배타 선택 여부
    private boolean exclusive;
    //최소 선택 개수
    private int minimumChoice;
    //최대 선택 개수
    private int maximumChoice;
    private List<ItemOption> itemOptions = new ArrayList<>();

    protected ItemOptionGroup() {
    }

    public ItemOptionGroup(
        Long itemId,
        String name,
        Integer ordering,
        boolean basic,
        boolean exclusive,
        int minimumChoice,
        int maximumChoice,
        List<ItemOption> itemOptions
    ) {
        this.itemId = itemId;
        this.name = name;
        this.ordering = ordering;
        this.basic = basic;
        this.exclusive = exclusive;
        this.minimumChoice = minimumChoice;
        this.maximumChoice = maximumChoice;
        this.itemOptions = itemOptions;
    }

    public ItemOptionGroup addItemOption(ItemOption itemOption) {
        this.itemOptions.add(itemOption);
        return this;
    }

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public boolean isBasic() {
        return basic;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public int getMinimumChoice() {
        return minimumChoice;
    }

    public int getMaximumChoice() {
        return maximumChoice;
    }

    public List<ItemOption> getItemOptions() {
        return itemOptions;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
