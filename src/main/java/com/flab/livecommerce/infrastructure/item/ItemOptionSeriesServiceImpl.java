package com.flab.livecommerce.infrastructure.item;

import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import com.flab.livecommerce.application.item.command.UpdateItemCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ItemOptionSeriesServiceImpl implements ItemOptionSeriesService {

    private final ItemOptionGroupRepository itemOptionGroupRepository;
    private final ItemOptionRepository itemOptionRepository;

    public ItemOptionSeriesServiceImpl(
        ItemOptionGroupRepository itemOptionGroupRepository,
        ItemOptionRepository itemOptionRepository
    ) {
        this.itemOptionGroupRepository = itemOptionGroupRepository;
        this.itemOptionRepository = itemOptionRepository;
    }


    @Override
    public List<ItemOptionGroup> save(RegisterItemCommand command, Item item) {
        var itemOptionGroupList = command.getItemOptionGroup();

        if (itemOptionGroupList.isEmpty()) {
            return Collections.emptyList();
        }

        return itemOptionGroupList.stream().map(
            requestItemOptionGroup -> {
                var optionGroup = requestItemOptionGroup.toEntity(item);
                var itemOptionGroup = itemOptionGroupRepository.save(optionGroup);

                requestItemOptionGroup.getItemOptions().forEach(
                    requestItemOption -> {
                        var itemOption = requestItemOption.toEntity(itemOptionGroup);
                        itemOptionRepository.save(itemOption);
                    }
                );
                return itemOptionGroup;
            }
        ).collect(Collectors.toList());
    }

    @Override
    public void update(UpdateItemCommand command, Item item) {
        var itemOptionGroupList = command.getItemOptionGroups();
        itemOptionGroupList.forEach(
            requestItemOptionGroup -> {
                var requestOptionGroup = requestItemOptionGroup.toEntity(item);
                var updatedOptionGroup = itemOptionGroupRepository.update(requestOptionGroup,
                    requestOptionGroup.getId());

                requestItemOptionGroup.getItemOptions().forEach(
                    requestItemOption -> {
                        var itemOption = requestItemOption.toEntity(updatedOptionGroup);
                        itemOptionRepository.update(itemOption, itemOption.getId());
                    }
                );
            }
        );
    }
}
