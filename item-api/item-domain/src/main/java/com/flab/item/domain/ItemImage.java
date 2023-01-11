package com.flab.item.domain;


public class ItemImage {

    private Long id;
    private String name;
    private String url;
    private Long itemId;

    protected ItemImage() {
    }

    public ItemImage(String name, String url, Long itemId) {
        this.name = name;
        this.url = url;
        this.itemId = itemId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
