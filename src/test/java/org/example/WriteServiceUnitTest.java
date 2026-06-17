package org.example;


import org.example.Service.ItemGroupReadService;
import org.example.Service.ItemGroupWriteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WriteServiceUnitTest {
    @Mock
    private ItemGroupWriteService itemGroupWriteService;

    @Mock
    private ItemGroupReadService itemGroupWriteRepository;

    @Test
    void writeItemGroup() {
        ItemGroup itemGroup = new ItemGroup("Reading","green","Reading");
        when(itemGroupWriteService.writeItemGroup(itemGroup)).thenReturn(1);
        int statusCode = itemGroupWriteService.writeItemGroup(itemGroup);
        assertEquals(1,statusCode);
    }
}
