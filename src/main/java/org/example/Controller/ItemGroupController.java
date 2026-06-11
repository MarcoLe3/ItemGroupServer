package org.example.Controller;

import org.example.Item;
import org.example.ItemGroup;
import org.example.Service.ItemGroupService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class ItemGroupController {
    @Autowired
    private final ItemGroupService itemGroupService;

    public ItemGroupController(ItemGroupService itemGroupService) {
        this.itemGroupService = itemGroupService;
    }

    @GetMapping("/findAllItemGroup")
    public ResponseEntity<List<ItemGroup>> findAllItemGroup() {
        List<ItemGroup> itemGroups = itemGroupService.findAllItemGroup();
        return new ResponseEntity<>(itemGroups, HttpStatus.ACCEPTED);
    }

    @PostMapping("/publishItemGroup")
    public ResponseEntity<ItemGroup> createGroup(@RequestBody ItemGroup itemGroup) {
        ItemGroup publishedItemGroup = itemGroupService.publishItemGroup(itemGroup);
        return new ResponseEntity<>(publishedItemGroup, HttpStatus.CREATED);
    }
}
