package com.mainor.project21.glampingestonia.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GlampingDTO {
    private String id;
    private String name;
    private String description;
    private String county;
    private String linkToBook;
    private List<String> picture;
    private BigDecimal price;

}
