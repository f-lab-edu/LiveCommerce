package com.flab.livecommerce.order.domain;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ordering;
    private String name;
    private Long price;

    @Builder
    public OrderItemOption(
        Integer ordering,
        String name,
        Long price
    ) {
        if (ordering == null) {
            throw new InvalidParameterException("orderItemOption.ordering");
        }
        if (name == null && name.length() == 0) {
            throw new InvalidParameterException("orderItemOption.name");
        }
        if (price == null) {
            throw new InvalidParameterException("orderItemOption.price");
        }
        this.ordering = ordering;
        this.name = name;
        this.price = price;
    }

    public OrderItemOption setId(Long id) {
        this.id = id;
        return this;
    }
}
