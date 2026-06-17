package org.example.Service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.transaction.annotation.Transactional;
import org.example.ItemGroup;
import org.example.Repository.ItemGroupWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemGroupWriteService {
    @Autowired
    ItemGroupWriteRepository itemGroupWriteRepository;

    public int writeItemGroup(ItemGroup itemGroup) {
        itemGroupWriteRepository.save(itemGroup);
        return 200;
    }

    @Transactional
    public int updateItemGroup(String queryId, ItemGroup newItemGroupDetail, boolean allowToAddNewItemGroup) {
        return itemGroupWriteRepository.findByPublicId(queryId)
                .map(existedItemGroup -> {
                    existedItemGroup.setName(newItemGroupDetail.getName());
                    existedItemGroup.setColor(newItemGroupDetail.getColor());
                    existedItemGroup.setTypeOfGroup(newItemGroupDetail.getTypeOfGroup());
                    itemGroupWriteRepository.save(existedItemGroup);
                    return 200;
                })
                .orElseGet(() -> {
                    if (allowToAddNewItemGroup) {
                        itemGroupWriteRepository.save(newItemGroupDetail);
                        return 200;
                    }
                    return 500;
                });
    }

    @Transactional
    public int deleteItemGroup(String queryId) {
        return itemGroupWriteRepository.findByPublicId(queryId)
                .map(markDeletionItemGroup -> {
                    itemGroupWriteRepository.delete(markDeletionItemGroup);
                    return 200;
                })
                .orElseGet(() -> {
                    return 500;
                });
    }
}
