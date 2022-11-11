package com.flab.livecommerce.item.infrastructure.item;

import com.flab.livecommerce.item.application.command.RegisterItemCommand;
import com.flab.livecommerce.item.domain.Item;
import com.flab.livecommerce.item.domain.ItemOptionGroup;
import com.flab.livecommerce.item.domain.ItemOptionGroupRepository;
import com.flab.livecommerce.item.domain.ItemOptionRepository;
import com.flab.livecommerce.item.domain.ItemOptionSeriesService;
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
}
