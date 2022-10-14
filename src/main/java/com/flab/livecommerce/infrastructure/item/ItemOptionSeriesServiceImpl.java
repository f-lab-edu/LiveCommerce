package com.flab.livecommerce.infrastructure.item;

import com.flab.livecommerce.application.item.command.ItemFormCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import java.util.Collections;
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

    // TODO 옵션 업데이트 안됨
    @Override
    public void update(ItemFormCommand command, Item item) {
        var requestItemOptionGroups = command.getItemOptionGroup();
        var optionGroupIdList = item.getOptionGroupId();

        var itemOptionGroupMap =
            IntStream.range(0, requestItemOptionGroups.size())
                .boxed()
                .collect(Collectors.toMap(optionGroupIdList::get, requestItemOptionGroups::get));

        itemOptionGroupMap.forEach((optionGroupId, OptionGroupRequest) -> {
            var optionGroup = OptionGroupRequest.toEntity(item);
            itemOptionGroupRepository.update(optionGroup, optionGroupId);

            var requestItemOptions = optionGroup.getItemOptions();
            var optionIdList = optionGroup.getOptionId();

            var itemOptionMap = IntStream.range(0, requestItemOptions.size()).boxed()
                .collect(Collectors.toMap(optionIdList::get, requestItemOptions::get));

            itemOptionMap.forEach((optionId, optionRequest)-> {
                itemOptionRepository.update(optionRequest, optionId);
            });
        });
    }
}
