package com.mainor.project21.glampingestonia.service;

import com.mainor.project21.glampingestonia.dto.CreateGlampingRequest;
import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.mapper.GlampingMapper;
import com.mainor.project21.glampingestonia.model.Glamping;
import com.mainor.project21.glampingestonia.repository.GlampingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GlampingService {

    private final GlampingRepository glampingRepository;

//    public GlampingDTO create(CreateGlampingRequest createGlampingRequest) throws ExecutionException, InterruptedException {
//        Glamping glamping = GlampingMapper.toEntity(createGlampingRequest);
//        glampingRepository.save(glamping);
//        return GlampingMapper.toDto(glamping);
//    }

    public List<GlampingDTO> getAll() throws ExecutionException, InterruptedException, IOException {
        return glampingRepository.findAll()
                .stream()
                .map(GlampingMapper::toDto)
                .toList();
    }

}
