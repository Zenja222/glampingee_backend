package com.mainor.project21.glampingestonia.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GlampingDTO {
    private String id;
    private String name;
    private String description;
    private String picture;
    private String location;
    private BigDecimal price;


}
