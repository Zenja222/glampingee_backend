package com.mainor.project21.glampingestonia;

import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.model.Glamping;
import com.mainor.project21.glampingestonia.service.GlampingService;
import com.mainor.project21.glampingestonia.repository.GlampingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
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

    private Glamping createMockGlamping(String id, String name, String description, String location,
                                        List<String> picture, String county, BigDecimal price, String linkToBook) {
        Glamping glamping = new Glamping();
        glamping.setId(id);
        glamping.setName(name);
        glamping.setPicture(picture);
        glamping.setCounty(county);
        glamping.setLinkToBook(linkToBook);
        glamping.setDescription(description);
        glamping.setLocation(location);
        glamping.setPrice(price);
        return glamping;
    }

    @Test
    void getAll_ReturnsListOfGlampingDTOs() throws ExecutionException, InterruptedException, IOException {
        List<String> pictures = Arrays.asList("pic1.jpg", "pic2.jpg");

        Glamping mockGlamping1 = createMockGlamping("1", "Glamping 1", "Description 1", "Location 1",
                pictures, "County 1", new BigDecimal("100.00"), "Link to book 1");
        Glamping mockGlamping2 = createMockGlamping("2", "Glamping 2", "Description 2", "Location 2",
                pictures, "County 2", new BigDecimal("200.00"), "Link to book 2");

        when(glampingRepository.findAll()).thenReturn(Arrays.asList(mockGlamping1, mockGlamping2));

        List<GlampingDTO> result = glampingService.getAll();

        assertEquals(2, result.size());
        assertEquals("Glamping 1", result.getFirst().getName());
        assertEquals("100.00", result.getFirst().getPrice().toString());
        verify(glampingRepository, times(1)).findAll();
    }

    @Test
    void getById_ReturnsGlampingDTOById() throws ExecutionException, InterruptedException, IOException {
        List<String> pictures = Arrays.asList("pic1.jpg", "pic2.jpg");
        Glamping mockGlamping = createMockGlamping("1", "Glamping 1", "Description 1", "Location 1",
                pictures, "County 1", new BigDecimal("100.00"), "Link to book 1");

        when(glampingRepository.findById(mockGlamping.getId())).thenReturn(mockGlamping);

        GlampingDTO result = glampingService.getById(mockGlamping.getId());

        assertNotNull(result);
        assertEquals("Glamping 1", result.getName());
        assertEquals("100.00", result.getPrice().toString());
        verify(glampingRepository, times(1)).findById(mockGlamping.getId());
    }

    @Test
    void getById_ReturnsSortedGlampingDTOsByPrice() throws ExecutionException, InterruptedException, IOException {
        List<String> pictures1 = Arrays.asList("pic1.jpg", "pic2.jpg");
        Glamping mockGlamping1 = createMockGlamping("1", "Glamping 1", "Description 1", "Location 1",
                pictures1, "County 1", new BigDecimal("200.00"), "Link to book 1");

        List<String> pictures2 = Arrays.asList("pic3.jpg", "pic4.jpg");
        Glamping mockGlamping2 = createMockGlamping("2", "Glamping 2", "Description 2", "Location 2",
                pictures2, "County 2", new BigDecimal("100.00"), "Link to book 2");

        List<Glamping> mockGlampings = Arrays.asList(mockGlamping2, mockGlamping1);

        when(glampingRepository.filterByField("price", "asc")).thenReturn(mockGlampings);

        List<GlampingDTO> result = glampingService.filterByField("price", "asc");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Glamping 2", result.get(0).getName());
        assertEquals("100.00", result.get(0).getPrice().toString());
        assertEquals("Glamping 1", result.get(1).getName());
        assertEquals("200.00", result.get(1).getPrice().toString());

        verify(glampingRepository, times(1)).filterByField("price", "asc");
    }

}
