package com.mainor.project21.glampingestonia.dto;

import lombok.Data;

@Data
public class CreateGlampingRequest {
    private String name;
    private String description;
    private String picture;
    private String location;
}
