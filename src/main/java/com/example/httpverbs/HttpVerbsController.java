package com.example.httpverbs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HttpVerbsController {

    @Autowired
    HttpVerbsService httpVerbsService;

    @GetMapping("/")
    public List<HttpVerbsModel> findAllModels() {
        return httpVerbsService.findAll();
    }

    @GetMapping("/{name}")
    public List<HttpVerbsModel> findModelsByName(@PathVariable("name") String name) {
        return httpVerbsService.findByName(name);
    }

    @PostMapping("/")
    public void saveModel(@RequestBody HttpVerbsModel httpVerbsModel) {
        httpVerbsService.save(httpVerbsModel);
    }

    @PutMapping("/{id}")
    public void updateFullModel(@RequestBody HttpVerbsModel httpVerbsModel, @PathVariable("id") Long id) {
        httpVerbsService.updateFully(httpVerbsModel, id);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    public void updatePartialModel(@RequestBody JsonPatch patch, @PathVariable("id") Long id) throws JsonPatchException, JsonProcessingException {
        httpVerbsService.updatePartially(patch, id);
    }
}
