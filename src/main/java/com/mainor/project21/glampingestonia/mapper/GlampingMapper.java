package com.mainor.project21.glampingestonia.mapper;

import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.model.Glamping;

public class GlampingMapper {

    public static GlampingDTO toDto(Glamping glamping) {
        return new GlampingDTO(
                glamping.getId(),
                glamping.getName(),
                glamping.getDescription(),
                glamping.getPicture(),
                glamping.getLocation(),
                glamping.getPrice()
        );
    }
}
