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
import java.util.Map;

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

    private GlampingDTO createMockGlamping(String id, Map<String, String> name, Map<String, String> description,
                                           List<String> pictures, String county, BigDecimal price,
                                           String linkToBook) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (pictures == null || pictures.isEmpty()) {
            throw new IllegalArgumentException("Pictures cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        GlampingDTO glamping = new GlampingDTO();
        glamping.setId(id);
        glamping.setName(name);
        glamping.setDescription(description);
        glamping.setCounty(county != null ? county : "Unknown county");
        glamping.setPicture(pictures);
        glamping.setPrice(price);
        glamping.setLinkToBook(linkToBook != null ? linkToBook : "No booking link provided");
        return glamping;
    }


    @Test
    void getAll_ReturnsGlampingList() throws Exception {

        List<String> pictures = Arrays.asList("pic1.jpg", "pic2.jpg");

        Map<String, String> name1 = Map.of("en", "Glamping 1", "et", "Glämping 1");
        Map<String, String> description1 = Map.of("en", "Description 1", "et", "Kirjeldus 1");

        Map<String, String> name2 = Map.of("en", "Glamping 2", "et", "Glämping 2");
        Map<String, String> description2 = Map.of("en", "Description 2", "et", "Kirjeldus 2");


        GlampingDTO mockGlamping1 = createMockGlamping(
                "1",
                name1,
                description1,
                pictures,
                "County 1",
                new BigDecimal("100.00"),
                "Link to book 1"
        );

        GlampingDTO mockGlamping2 = createMockGlamping(
                "2",
                name2,
                description2,
                pictures,
                "County 2",
                new BigDecimal("200.00"),
                "Link to book 2"
        );


        when(glampingService.getAll()).thenReturn(Arrays.asList(mockGlamping1, mockGlamping2));


        mockMvc.perform(get("/glamping")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name.en").value("Glamping 1"))
                .andExpect(jsonPath("$[0].description.en").value("Description 1"))
                .andExpect(jsonPath("$[0].picture[0]").value("pic1.jpg"))
                .andExpect(jsonPath("$[0].price").value("100.0"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name.en").value("Glamping 2"))
                .andExpect(jsonPath("$[1].description.en").value("Description 2"))
                .andExpect(jsonPath("$[1].price").value("200.0"));


        verify(glampingService, times(1)).getAll();
    }


    @Test
    void findById_ReturnsGlampingById() throws Exception {

        List<String> pictures = Arrays.asList("pic1.jpg", "pic2.jpg");

        Map<String, String> name = Map.of("en", "Glamping 1", "et", "Glämping 1");
        Map<String, String> description = Map.of("en", "Description 1", "et", "Kirjeldus 1");

        GlampingDTO mockGlamping1 = createMockGlamping(
                "1",
                name,
                description,
                pictures,
                "County 1",
                new BigDecimal("200.00"),
                "Link to book 1"
        );

        when(glampingService.getById(mockGlamping1.getId())).thenReturn(mockGlamping1);

        mockMvc.perform(get("/glamping/{id}", mockGlamping1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockGlamping1.getId()))
                .andExpect(jsonPath("$.name.en").value("Glamping 1")) // Проверка вложенного объекта
                .andExpect(jsonPath("$.description.en").value("Description 1")) // Проверка вложенного объекта
                .andExpect(jsonPath("$.picture[0]").value("pic1.jpg"))
                .andExpect(jsonPath("$.price").value("200.0"));

        verify(glampingService, times(1)).getById(mockGlamping1.getId());
    }

}
