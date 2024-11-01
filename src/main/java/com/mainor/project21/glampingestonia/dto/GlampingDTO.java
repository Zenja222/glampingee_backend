package com.mainor.project21.glampingestonia.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class GlampingDTO {
    private String id;
    private String county;
    private String linkToBook;
    private List<String> picture;
    private BigDecimal price;

    private Map<String, String> name;
    private Map<String, String> description;

}
