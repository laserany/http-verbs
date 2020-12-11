package com.example.httpverbs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HttpVerbsService {

    @Autowired
    private HttpVerbsRepository httpVerbsRepository;

    public List<HttpVerbsModel> findByName(String name) {
        return httpVerbsRepository.findByName(name);
    }

    public void save(HttpVerbsModel model) { httpVerbsRepository.save(model); }

    public void updateFully(HttpVerbsModel model, Long id) {
        model.setId(id);
        httpVerbsRepository.save(model);
    }

    public void updatePartially(JsonPatch patch, Long id) throws JsonPatchException, JsonProcessingException {
        HttpVerbsModel httpVerbsModel = httpVerbsRepository.findById(id).get();
        HttpVerbsModel patchedHttpVerbsModel = applyPatchToHttpVerbsModel(patch, httpVerbsModel);
        httpVerbsRepository.save(patchedHttpVerbsModel);
    }

    public List<HttpVerbsModel> findAll() {
        return httpVerbsRepository.findAll();
    }

    private HttpVerbsModel applyPatchToHttpVerbsModel(JsonPatch patch, HttpVerbsModel httpVerbsModel) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = patch.apply(objectMapper.convertValue(httpVerbsModel, JsonNode.class));
        return objectMapper.treeToValue(jsonNode, HttpVerbsModel.class);
    }

}
