package com.mainor.project21.glampingestonia.web;

import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.service.GlampingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("glamping")
public class GlampingController {

    private final GlampingService glampingService;

    @GetMapping
    public List<GlampingDTO> getAll() throws ExecutionException, InterruptedException, IOException {
        return glampingService.getAll();
    }

    @GetMapping("/{id}")
    public GlampingDTO findById(@PathVariable String id) throws IOException, ExecutionException, InterruptedException {
        return glampingService.getById(id);
    }
}
