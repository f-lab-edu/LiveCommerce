package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateItemOptionGroupCommand {

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

    private List<UpdateItemOptionCommand> itemOptions;

    public ItemOptionGroup toEntity(Item item) {
        var itemOptionGroup = ItemOptionGroup.builder()
            .ordering(this.ordering)
            .name(this.name)
            .basic(this.basic)
            .exclusive(this.exclusive)
            .minimumChoice(this.minimumChoice)
            .maximumChoice(this.maximumChoice)
            .build();
        itemOptionGroup.setItem(item);
        return itemOptionGroup.setId(id);
    }
}
