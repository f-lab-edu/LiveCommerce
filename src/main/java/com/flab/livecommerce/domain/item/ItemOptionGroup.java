package com.flab.livecommerce.domain.item;

import java.util.ArrayList;
import java.util.List;

public class ItemOptionGroup {

    private Long id;
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

    public ItemOptionGroup(
        String name,
        Integer ordering,
        boolean basic,
        boolean exclusive,
        int minimumChoice,
        int maximumChoice,
        List<ItemOption> itemOptions
    ) {
        this.name = name;
        this.ordering = ordering;
        this.basic = basic;
        this.exclusive = exclusive;
        this.minimumChoice = minimumChoice;
        this.maximumChoice = maximumChoice;
        this.itemOptions.addAll(itemOptions);
    }

    public ItemOptionGroup addItemOption(ItemOption itemOption) {
        this.itemOptions.add(itemOption);
        return this;
    }
}
