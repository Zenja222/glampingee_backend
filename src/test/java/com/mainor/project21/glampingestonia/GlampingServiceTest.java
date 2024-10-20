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

    @Test
    void getAll_ReturnsListOfGlampingDTOs() throws ExecutionException, InterruptedException, IOException {
        Glamping mockGlamping1 = new Glamping();
        mockGlamping1.setId("1");
        mockGlamping1.setName("Glamping 1");
        mockGlamping1.setDescription("Description 1");
        mockGlamping1.setPicture("pic1.jpg");
        mockGlamping1.setLocation("Location 1");
        mockGlamping1.setPrice(new BigDecimal("100.00"));

        Glamping mockGlamping2 = new Glamping();
        mockGlamping2.setId("2");
        mockGlamping2.setName("Glamping 2");
        mockGlamping2.setDescription("Description 2");
        mockGlamping2.setPicture("pic2.jpg");
        mockGlamping2.setLocation("Location 2");
        mockGlamping2.setPrice(new BigDecimal("200.00"));

        when(glampingRepository.findAll()).thenReturn(Arrays.asList(mockGlamping1, mockGlamping2));

        List<GlampingDTO> result = glampingService.getAll();

        assertEquals(2, result.size());
        assertEquals("Glamping 1", result.get(0).getName());
        assertEquals("100.00", result.get(0).getPrice().toString());
        verify(glampingRepository, times(1)).findAll();
    }

    @Test
    void getById_ReturnsGlampingDTOById() throws ExecutionException, InterruptedException, IOException {
        String id = "1";
        Glamping mockGlamping = new Glamping();
        mockGlamping.setId(id);
        mockGlamping.setName("Glamping 1");
        mockGlamping.setDescription("Description 1");
        mockGlamping.setPicture("pic1.jpg");
        mockGlamping.setLocation("Location 1");
        mockGlamping.setPrice(new BigDecimal("100.00"));

        when(glampingRepository.findById(id)).thenReturn(mockGlamping);

        GlampingDTO result = glampingService.getById(id);

        assertNotNull(result);
        assertEquals("Glamping 1", result.getName());
        assertEquals("100.00", result.getPrice().toString());
        verify(glampingRepository, times(1)).findById(id);
    }
}
