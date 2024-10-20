package com.mainor.project21.glampingestonia.mapper;

import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.model.Glamping;

public class GlampingMapper {

    public static GlampingDTO toDto(Glamping glamping){
        GlampingDTO glampingDTO = new GlampingDTO();
        glampingDTO.setId(glamping.getId());
        glampingDTO.setName(glamping.getName());
        glampingDTO.setDescription(glamping.getDescription());
        glampingDTO.setPicture(glamping.getPicture());
        glampingDTO.setLocation(glamping.getLocation());
        glampingDTO.setPrice(glamping.getPrice());
        return glampingDTO;
    }
}
