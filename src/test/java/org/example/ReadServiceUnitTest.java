package org.example;

import org.example.Repository.ItemGroupReadRepository;
import org.example.Service.ItemGroupReadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReadServiceUnitTest {
    @Mock
    private ItemGroupReadRepository itemGroupRepository;

    @InjectMocks
    private ItemGroupReadService itemGroupReadService;

    @Test
    void findItemGroupByName_returnsHit() {
        List<ItemGroup> cacheItemGroup = List.of(
                new ItemGroup("Reading", "green", "reading"),
                new ItemGroup("Reading", "red", "reading")
        );

        when(itemGroupRepository.findItemGroupByName("Reading")).thenReturn(cacheItemGroup);

        itemGroupReadService.findItemGroupByName("Reading");
        itemGroupReadService.findItemGroupByName("Reading");
        verify(itemGroupRepository, times(2)).findItemGroupByName("Reading");
    }

    @Test
    void findItemGroupByName_returnsNotHit() {
        List<ItemGroup> cacheItemGroup = List.of();
        when(itemGroupRepository.findItemGroupByName("Reading")).thenReturn(
                List.of(new ItemGroup("Reading", "green", "reading")
                ));
        List<ItemGroup> resultItemGroup = itemGroupReadService.findItemGroupByName("Reading");

        assertNotEquals(cacheItemGroup, resultItemGroup);
    }
}
