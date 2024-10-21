package com.mainor.project21.glampingestonia.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Glamping {
    private String id;
    private String name;
    private String description;
    private String county;
    private String linkToBook;
    private List<String> picture;
    private String location;
    private BigDecimal price;
}

