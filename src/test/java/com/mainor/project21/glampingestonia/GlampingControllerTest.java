package com.mainor.project21.glampingestonia;

import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.service.GlampingService;
import com.mainor.project21.glampingestonia.web.GlampingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GlampingControllerTest {

    @Mock
    private GlampingService glampingService;

    @InjectMocks
    private GlampingController glampingController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(glampingController).build();
    }

    @Test
    void getAll_ReturnsGlampingList() throws Exception {
        GlampingDTO mockGlamping1 = new GlampingDTO();
        mockGlamping1.setId("1");
        mockGlamping1.setName("Glamping 1");
        mockGlamping1.setDescription("Description 1");
        mockGlamping1.setPicture("pic1.jpg");
        mockGlamping1.setLocation("Location 1");
        mockGlamping1.setPrice(new BigDecimal("100.00"));

        GlampingDTO mockGlamping2 = new GlampingDTO();
        mockGlamping2.setId("2");
        mockGlamping2.setName("Glamping 2");
        mockGlamping2.setDescription("Description 2");
        mockGlamping2.setPicture("pic2.jpg");
        mockGlamping2.setLocation("Location 2");
        mockGlamping2.setPrice(new BigDecimal("200.00"));
        when(glampingService.getAll()).thenReturn(Arrays.asList(mockGlamping1, mockGlamping2));

        mockMvc.perform(get("/glamping")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Glamping 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].picture").value("pic1.jpg"))
                .andExpect(jsonPath("$[0].location").value("Location 1"))
                .andExpect(jsonPath("$[0].price").value("100.0"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].price").value("200.0"));


        verify(glampingService, times(1)).getAll();
    }

    @Test
    void findById_ReturnsGlampingById() throws Exception {
        String id = "1";
        GlampingDTO mockGlamping1 = new GlampingDTO();
        mockGlamping1.setId("1");
        mockGlamping1.setName("Glamping 1");
        mockGlamping1.setDescription("Description 1");
        mockGlamping1.setPicture("pic1.jpg");
        mockGlamping1.setLocation("Location 1");
        mockGlamping1.setPrice(new BigDecimal("100.00"));
        when(glampingService.getById(id)).thenReturn(mockGlamping1);

        mockMvc.perform(get("/glamping/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Glamping 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.picture").value("pic1.jpg"))
                .andExpect(jsonPath("$.location").value("Location 1"))
                .andExpect(jsonPath("$.price").value("100.0"));

        verify(glampingService, times(1)).getById(id);
    }
}
