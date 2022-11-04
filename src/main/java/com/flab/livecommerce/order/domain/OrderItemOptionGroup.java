package com.flab.livecommerce.order.domain;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderItemOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ordering;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_option_group_id")
    private List<OrderItemOption> orderItemOptions = new ArrayList<>();

    @Builder
    public OrderItemOptionGroup(
        Integer ordering,
        String name,
        List<OrderItemOption> orderItemOptions
    ) {
        if (ordering == null) {
            throw new InvalidParameterException("OrderItemOptionGroup.ordering");
        }
        if (name == null && name.length() == 0) {
            throw new InvalidParameterException("OrderItemOptionGroup.name");
        }

        this.ordering = ordering;
        this.name = name;
        this.orderItemOptions = orderItemOptions;
    }

    public OrderItemOptionGroup setId(Long id) {
        this.id = id;
        return this;
    }
}
