package com.flab.order.domain;

import com.flab.order.domain.data.DecreaseInventoryData;
import com.flab.order.domain.data.ItemQuantityData;
import java.util.List;

public interface DecreaseInventoryService {

    DecreaseInventoryData service(List<ItemQuantityData> itemQuantityDataList);
}
