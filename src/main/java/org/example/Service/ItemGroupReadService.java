package org.example.Service;

import org.example.ItemGroup;
import org.example.Repository.ItemGroupReadRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemGroupReadService {

    @Autowired
    private ItemGroupReadRepository itemGroupReadRepository;

    @Autowired
    private RedisTemplate<String, Object> redis;

    @Cacheable(value = "itemGroup", key = "#name", unless = "#result.isEmpty()")
    public List<ItemGroup> findItemGroupByName(String name) {
        return itemGroupReadRepository.findItemGroupByName(name);
    }

    public List<ItemGroup> findAllItemGroups() {
        String commandGetAllItemGroups = "itemGroups:all";
        List<ItemGroup> redisItemGroups = (List<ItemGroup>) redis.opsForValue().get(commandGetAllItemGroups);

        return Optional
                .ofNullable(redisItemGroups)
                .orElseGet(itemGroupReadRepository::findAll);
    }
}
