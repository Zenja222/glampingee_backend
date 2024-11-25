package com.mainor.project21.glampingestonia;

import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.model.Glamping;
import com.mainor.project21.glampingestonia.repository.GlampingRepository;
import com.mainor.project21.glampingestonia.service.GlampingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlampingServiceTest {

    @Mock
    private GlampingRepository glampingRepository;

    @InjectMocks
    private GlampingService glampingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Glamping createMockGlampingEntity(String id, String name, String description, BigDecimal price, String county) {
        Glamping glamping = new Glamping();
        glamping.setId(id);
        glamping.setName(Map.of("en", name));
        glamping.setDescription(Map.of("en", description));
        glamping.setCounty(county);
        glamping.setPrice(price);
        glamping.setPicture(Arrays.asList("pic1.jpg", "pic2.jpg"));
        glamping.setLinkToBook("Link to book");
        return glamping;
    }

    @Test
    void getAll_ReturnsListOfGlampingDTOs() throws IOException, ExecutionException, InterruptedException {
        Glamping glamping1 = createMockGlampingEntity("1", "Glamping 1", "Description 1", new BigDecimal("100.00"), "County 1");
        Glamping glamping2 = createMockGlampingEntity("2", "Glamping 2", "Description 2", new BigDecimal("200.00"), "County 2");
        when(glampingRepository.findAll()).thenReturn(Arrays.asList(glamping1, glamping2));

        List<GlampingDTO> result = glampingService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Glamping 1", result.get(0).getName().get("en"));
        assertEquals("100.00", result.get(0).getPrice().toString());
        verify(glampingRepository, times(1)).findAll();
    }

    @Test
    void getById_ReturnsGlampingDTOById() throws IOException, ExecutionException, InterruptedException {
        Glamping glamping = createMockGlampingEntity("1", "Glamping 1", "Description 1", new BigDecimal("100.00"), "County 1");
        when(glampingRepository.findById("1")).thenReturn(glamping);

        GlampingDTO result = glampingService.getById("1");

        assertNotNull(result);
        assertEquals("Glamping 1", result.getName().get("en"));
        assertEquals("100.00", result.getPrice().toString());
        verify(glampingRepository, times(1)).findById("1");
    }


    @Test
    void filterByField_ReturnsSortedGlampingDTOsByPrice() throws IOException, ExecutionException, InterruptedException {
        Glamping glamping1 = createMockGlampingEntity("1", "Glamping 1", "Description 1", new BigDecimal("200.00"), "County 1");
        Glamping glamping2 = createMockGlampingEntity("2", "Glamping 2", "Description 2", new BigDecimal("100.00"), "County 2");

        when(glampingRepository.filterByField("price", "asc")).thenReturn(Arrays.asList(glamping2, glamping1));

        List<GlampingDTO> result = glampingService.filterByField("price", "asc");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Glamping 2", result.get(0).getName().get("en"));
        assertEquals("100.00", result.get(0).getPrice().toString());
        assertEquals("Glamping 1", result.get(1).getName().get("en"));
        assertEquals("200.00", result.get(1).getPrice().toString());

        verify(glampingRepository, times(1)).filterByField("price", "asc");
    }
}
