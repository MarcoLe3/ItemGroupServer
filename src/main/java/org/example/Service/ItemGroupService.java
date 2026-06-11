package org.example.Service;

import org.example.ItemGroup;
import org.example.Repository.ItemGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ItemGroupService {

    private final ItemGroupRepository itemGroupRepository;

    public ItemGroupService(ItemGroupRepository itemGroupRepository) {
        this.itemGroupRepository = itemGroupRepository;
    }

    public ItemGroup publishItemGroup(@RequestBody ItemGroup itemGroup) {
        return itemGroupRepository.save(itemGroup);
    }

    public List<ItemGroup> findAllItemGroup() {
        return itemGroupRepository.findAll();
    }

}
