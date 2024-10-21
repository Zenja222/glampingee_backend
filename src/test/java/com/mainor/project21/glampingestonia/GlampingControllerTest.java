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
import java.util.List;

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

    private GlampingDTO createMockGlamping(String id, String name, String description, String location,
                                        List<String> picture, String county, BigDecimal price, String linkToBook){
        GlampingDTO glamping = new GlampingDTO();
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
    void getAll_ReturnsGlampingList() throws Exception {
        List<String> pictures = Arrays.asList("pic1.jpg","pic2.jpg");

        GlampingDTO mockGlamping1 = createMockGlamping("1", "Glamping 1", "Description 1", "Location 1",
                pictures, "County 1", new BigDecimal("100.00"), "Link to book 1");
        GlampingDTO mockGlamping2 = createMockGlamping("2", "Glamping 2", "Description 2", "Location 2",
                pictures, "County 2", new BigDecimal("200.00"), "Link to book 2");
        when(glampingService.getAll()).thenReturn(Arrays.asList(mockGlamping1, mockGlamping2));

        mockMvc.perform(get("/glamping")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Glamping 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].picture[0]").value("pic1.jpg"))
                .andExpect(jsonPath("$[0].location").value("Location 1"))
                .andExpect(jsonPath("$[0].price").value("100.00"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].price").value("200.00"));


        verify(glampingService, times(1)).getAll();
    }

    @Test
    void findById_ReturnsGlampingById() throws Exception {
        List<String> pictures = Arrays.asList("pic1.jpg","pic2.jpg");

        GlampingDTO mockGlamping1 = createMockGlamping("1", "Glamping 1", "Description 1", "Location 1",
                pictures, "County 1", new BigDecimal("200.00"), "Link to book 1");

        when(glampingService.getById(mockGlamping1.getId())).thenReturn(mockGlamping1);

        mockMvc.perform(get("/glamping/{id}", mockGlamping1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockGlamping1.getId()))
                .andExpect(jsonPath("$.name").value("Glamping 1"))
                .andExpect(jsonPath("$.description").value("Description 1"))
                .andExpect(jsonPath("$.picture[0]").value("pic1.jpg"))
                .andExpect(jsonPath("$.location").value("Location 1"))
                .andExpect(jsonPath("$.price").value("100.00"));

        verify(glampingService, times(1)).getById(mockGlamping1.getId());
    }
}
