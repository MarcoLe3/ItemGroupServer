package org.example;

public class Item {
    private Long id;
    private String name;
    private String description;
    private String group;

    public Item(Long id, String name, String description, String group) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.group = group;
    }

    public void setItemId(Long id) {
        this.id = id;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public void setitemDescription(String description) {
        this.description = description;
    }

    public void setItemGroup(String group) {
        this.group = group;
    }
}
