package com.mainor.project21.glampingestonia.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class Glamping {
    private String id;
    private String county;
    private String linkToBook;
    private List<String> picture;
    private BigDecimal price;
    private Map<String, String> name;
    private Map<String, String> description;

}

