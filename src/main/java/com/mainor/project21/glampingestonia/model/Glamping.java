package com.mainor.project21.glampingestonia.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Glamping {
    private String id;
    private String name;
    private String description;
    private String picture;
    private String location;
    private BigDecimal price;
}
