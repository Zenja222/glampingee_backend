package com.mainor.project21.glampingestonia.mapper;

import com.mainor.project21.glampingestonia.dto.CreateGlampingRequest;
import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.model.Glamping;

public class GlampingMapper {

    public static GlampingDTO toDto(Glamping glamping){
        GlampingDTO glampingDTO = new GlampingDTO();
        glampingDTO.setId(glamping.getId());
        glampingDTO.setName(glamping.getName());
        glampingDTO.setDescription(glamping.getDescription());
        glampingDTO.setPicture(glamping.getPicture());
        glampingDTO.setPrice(glamping.getPrice());
        glampingDTO.setCounty(glamping.getCounty());
        glampingDTO.setLinkToBook(glamping.getLinkToBook());
        return glampingDTO;
    }

    public static Glamping toEntity(CreateGlampingRequest request){
        Glamping glamping = new Glamping();
        glamping.setName(request.getName());
        glamping.setDescription(request.getDescription());
        glamping.setPicture(request.getPicture());
        glamping.setCounty(request.getCounty());
        glamping.setPrice(request.getPrice());
        glamping.setLinkToBook(request.getLinkToBook());
        return glamping;
    }

    public static Glamping updateEntity(GlampingDTO source, Glamping target){
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setPrice(source.getPrice());
        target.setPicture(source.getPicture());
        target.setCounty(source.getCounty());
        target.setLinkToBook(source.getLinkToBook());
        return target;
    }
}
