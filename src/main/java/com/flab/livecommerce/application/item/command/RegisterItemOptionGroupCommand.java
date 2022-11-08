package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterItemOptionGroupCommand {

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

    private List<RegisterItemOptionCommand> itemOptions;


    public ItemOptionGroup toEntity(Item item) {
        var itemOptionGroup = ItemOptionGroup.builder()
            .ordering(this.ordering)
            .name(this.name)
            .basic(this.basic)
            .exclusive(this.exclusive)
            .minimumChoice(this.minimumChoice)
            .maximumChoice(this.maximumChoice)
            .itemOptions(toItemOptions())
            .build();
        itemOptionGroup.setItem(item);
        return itemOptionGroup;
    }

    public List<ItemOption> toItemOptions() {
        return this.itemOptions.stream().map(
            itemOption -> ItemOption.builder()
                .price(itemOption.getPrice())
                .name(itemOption.getName())
                .ordering(itemOption.getOrdering()).build()
        ).collect(Collectors.toList());

    }
}
