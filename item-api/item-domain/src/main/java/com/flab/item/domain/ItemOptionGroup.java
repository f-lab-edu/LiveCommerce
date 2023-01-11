package com.flab.item.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ItemOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer ordering;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_option_group_id")
    private List<ItemOption> itemOptions = new ArrayList<>();

    protected ItemOptionGroup() {
    }

    public ItemOptionGroup(
        String name,
        Integer ordering,
        List<ItemOption> itemOptions
    ) {
        this.name = name;
        this.ordering = ordering;
        this.itemOptions = itemOptions;
    }

    public ItemOptionGroup addItemOption(ItemOption itemOption) {
        this.itemOptions.add(itemOption);
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public List<ItemOption> getItemOptions() {
        return itemOptions;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
