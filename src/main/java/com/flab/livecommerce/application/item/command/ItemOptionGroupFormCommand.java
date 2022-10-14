package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOptionGroupFormCommand {


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
    private List<ItemOptionFormCommand> itemOptions = new ArrayList<>();

    public ItemOptionGroup toEntity(Item item) {
        return ItemOptionGroup.builder()
            .itemId(item.getId())
            .ordering(ordering)
            .name(name)
            .basic(basic)
            .exclusive(exclusive)
            .minimumChoice(minimumChoice)
            .maximumChoice(maximumChoice)
            .build();
    }
}