package com.mainor.project21.glampingestonia.web;

import com.mainor.project21.glampingestonia.dto.CreateGlampingRequest;
import com.mainor.project21.glampingestonia.dto.GlampingDTO;
import com.mainor.project21.glampingestonia.service.GlampingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/filter")
    public List<GlampingDTO> filterGlampings(
            @RequestParam(defaultValue = "price") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) throws IOException, ExecutionException, InterruptedException {
        return glampingService.filterByField(sortField, sortDirection);
    }

    @PostMapping
    public GlampingDTO create(@RequestBody CreateGlampingRequest request) throws IOException, ExecutionException, InterruptedException {
        return glampingService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws IOException, ExecutionException, InterruptedException {
        glampingService.delete(id);
    }

    @PutMapping("/{id}")
    public GlampingDTO update(@PathVariable String id, @RequestBody GlampingDTO request) throws IOException, ExecutionException, InterruptedException {
        return glampingService.update(id, request);
    }

    @GetMapping("/search")
    public List<GlampingDTO> searchGlampings(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "en") String language
    ) throws IOException, ExecutionException, InterruptedException {
        return glampingService.searchByName(keyword, language);
    }

}
