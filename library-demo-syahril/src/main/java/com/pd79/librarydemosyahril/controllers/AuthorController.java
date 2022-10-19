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

import com.pd79.librarydemosyahril.dtos.AuthorDTO;
import com.pd79.librarydemosyahril.models.Author;
import com.pd79.librarydemosyahril.repositories.AuthorRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/author")
public class AuthorController {

  @Autowired
  private AuthorRepository authorRepository;

  @PostMapping("/create")
  public ResponseEntity<Object> createauthor(@RequestBody Author body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Author author = authorRepository.save(body);
      HttpStatus status = HttpStatus.CREATED;
      result.put("status", "201");
      result.put("message", "author created successfully!");
      result.put("data", author);
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/read")
  public ResponseEntity<Object> getAllauthor() {
    try {
      Map<String, Object> result = new HashMap<String, Object>();
      List<Author> listAllauthor = authorRepository.findAll();
      HttpStatus status = HttpStatus.OK;
      List<AuthorDTO> listAllauthorDTO = new ArrayList<>();
      for (Author authorEntity : listAllauthor) {
        ModelMapper modelMapper = new ModelMapper();
        AuthorDTO authorDTO = modelMapper.map(authorEntity, AuthorDTO.class);
        listAllauthorDTO.add(authorDTO);
      }
      if (listAllauthor.isEmpty()) {
        status = HttpStatus.NOT_FOUND;
        result.put("status", "404");
        result.put("message", "Get data success but no data.");
        result.put("data", listAllauthorDTO);
        return new ResponseEntity<Object>(result, status);
      }
      result.put("data", listAllauthorDTO);
      result.put("total", listAllauthorDTO.size());
      result.put("status", "200");
      result.put("message", "Read all data author success.");
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Object> updateauthor(@PathVariable("id") long id, @RequestBody Author body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Author> authorData = authorRepository.findById(id);
      HttpStatus status;
      if (authorData.isPresent()) {
        Author author = authorData.get();
        author.setAuthorName(body.getAuthorName());
        author.setGender(body.getGender());
        author.setAge(body.getAge());
        author.setCountry(body.getCountry());
        author.setRating(body.getRating());
        authorRepository.save(author);
        result.put("status", "200");
        result.put("message", "Data updated successfully.");
        result.put("data", authorData);
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
  public ResponseEntity<Object> deleteauthor(@PathVariable("id") long id) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Author> author = authorRepository.findById(id);
      HttpStatus status;
      if (author.isPresent()) {
        authorRepository.deleteById(id);
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
