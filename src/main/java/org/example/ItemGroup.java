package org.example;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;

import org.example.Item;

@Entity
@Table(name = "ItemGroups")
public class ItemGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;
    private String typeOfGroup;
    private final ArrayList<Item> itemList = new ArrayList<>();

    public ItemGroup() {

    }

    public ItemGroup(Long id, String name, String color, String typeOfGroup) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.typeOfGroup = typeOfGroup;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public void setNamee(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTypeOfGroup(String typeOfGroup) {
        this.typeOfGroup = typeOfGroup;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getTypeOfGroup() {
        return typeOfGroup;
    }
}
