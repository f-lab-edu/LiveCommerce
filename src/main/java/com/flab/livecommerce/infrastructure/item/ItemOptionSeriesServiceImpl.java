package com.flab.livecommerce.infrastructure.item;

import com.flab.livecommerce.application.item.command.ItemFormCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public List<ItemOptionGroup> save(ItemFormCommand command, Item item) {
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
    public void update(ItemFormCommand command, Item item) {
        // 옵션 그룹 업데이트
        var itemOptionGroupList = command.getItemOptionGroup();
        var originalOptionGroupList = item.getItemOptionGroups();

        var itemOptionGroupMap =
            IntStream.range(0, itemOptionGroupList.size())
                .boxed()
                .collect(Collectors.toMap(originalOptionGroupList::get, itemOptionGroupList::get));

        var itemOptionMap = new HashMap<Long, ItemOption>();

        itemOptionGroupMap.forEach((k, v) -> {
            itemOptionGroupRepository.update(v.toEntity(item), k.getId());
        });

        // 옵션 업데이트
        itemOptionGroupList.forEach(
            requestItemOptionGroup -> {
                var optionGroup = requestItemOptionGroup.toEntity(item);
                var itemOptionGroup = itemOptionGroupRepository.update(optionGroup, item.getId());

                requestItemOptionGroup.getItemOptions().forEach(
                    requestItemOption -> {
                        var itemOption = requestItemOption.toEntity(itemOptionGroup);

                    }
                );
                itemOptionMap.forEach((k, v) -> {
                    itemOptionRepository.update(v, k);
                });
            }
        );
    }
}
