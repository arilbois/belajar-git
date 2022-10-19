package com.pd79.librarydemosyahril.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pd79.librarydemosyahril.dtos.PublisherDTO;
import com.pd79.librarydemosyahril.models.Publisher;
import com.pd79.librarydemosyahril.repositories.PublisherRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
  @Autowired
  private PublisherRepository publisherRepository;

  @PostMapping("/create")
  public ResponseEntity<Object> createpublisher(@RequestBody Publisher body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Publisher publisher = publisherRepository.save(body);
      HttpStatus status = HttpStatus.CREATED;
      result.put("status", "201");
      result.put("message", "Publisher created successfully!");
      result.put("data", publisher);
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/read")
  public ResponseEntity<Object> getAllpublisher() {
    try {
      Map<String, Object> result = new HashMap<String, Object>();
      List<Publisher> listAllpublisher = publisherRepository.findAll();
      HttpStatus status = HttpStatus.OK;
      List<PublisherDTO> listAllpublisherDTO = new ArrayList<>();
      for (Publisher publisherEntity : listAllpublisher) {
        ModelMapper modelMapper = new ModelMapper();
        PublisherDTO publisherDTO = modelMapper.map(publisherEntity, PublisherDTO.class);
        listAllpublisherDTO.add(publisherDTO);
      }
      if (listAllpublisher.isEmpty()) {
        status = HttpStatus.NOT_FOUND;
        result.put("status", "404");
        result.put("message", "Get data success but no data.");
        result.put("data", listAllpublisherDTO);
        return new ResponseEntity<Object>(result, status);
      }
      result.put("data", listAllpublisherDTO);
      result.put("total", listAllpublisherDTO.size());
      result.put("status", "200");
      result.put("message", "Read all data publisher success.");
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Object> updatepublisher(@PathVariable("id") long id, @RequestBody Publisher body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Publisher> publisherData = publisherRepository.findById(id);
      HttpStatus status;
      if (publisherData.isPresent()) {
        Publisher publisher = publisherData.get();
        publisher.setPublisherName(body.getPublisherName());
        publisherRepository.save(publisher);
        result.put("status", "200");
        result.put("message", "Data updated successfully.");
        result.put("data", publisherData);
        status = HttpStatus.OK;
      } else {
        result.put("status", "404");
        result.put("message", "Update data fail. Data not found.");
        status = HttpStatus.NOT_FOUND;
      }
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Object> deletepublisher(@PathVariable("id") long id) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Publisher> publisher = publisherRepository.findById(id);
      HttpStatus status;
      if (publisher.isPresent()) {
        publisherRepository.deleteById(id);
        status = HttpStatus.OK;
        result.put("status", "200");
        result.put("message", "Data deleted successfully.");
      } else {
        status = HttpStatus.NOT_FOUND;
        result.put("status", "404");
        result.put("message", "Delete data fail. Data not found.");
      }
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
