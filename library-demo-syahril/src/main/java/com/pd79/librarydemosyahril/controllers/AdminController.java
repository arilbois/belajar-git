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

import com.pd79.librarydemosyahril.dtos.AdminDTO;
import com.pd79.librarydemosyahril.models.Admin;
import com.pd79.librarydemosyahril.repositories.AdminRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  private AdminRepository adminRepository;

  @PostMapping("/create")
  public ResponseEntity<Object> createAdmin(@RequestBody Admin body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Admin admin = adminRepository.save(body);
      HttpStatus status = HttpStatus.CREATED;
      result.put("status", "201");
      result.put("message", "Admin created successfully!");
      result.put("data", admin);
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/read")
  public ResponseEntity<Object> getAllAdmin() {
    try {
      Map<String, Object> result = new HashMap<String, Object>();
      List<Admin> listAllAdmin = adminRepository.findAll();
      HttpStatus status = HttpStatus.OK;
      List<AdminDTO> listAllAdminDTO = new ArrayList<>();
      for (Admin adminEntity : listAllAdmin) {
        ModelMapper modelMapper = new ModelMapper();
        AdminDTO adminDTO = modelMapper.map(adminEntity, AdminDTO.class);
        listAllAdminDTO.add(adminDTO);
      }
      if (listAllAdmin.isEmpty()) {
        status = HttpStatus.NOT_FOUND;
        result.put("status", "404");
        result.put("message", "Get data success but no data.");
        result.put("data", listAllAdminDTO);
        return new ResponseEntity<Object>(result, status);
      }
      result.put("data", listAllAdminDTO);
      result.put("total", listAllAdminDTO.size());
      result.put("status", "200");
      result.put("message", "Read all data Admin success.");
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Object> updateAdmin(@PathVariable("id") long id, @RequestBody Admin body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Admin> adminData = adminRepository.findById(id);
      HttpStatus status;
      if (adminData.isPresent()) {
        Admin admin = adminData.get();
        admin.setAdminName(body.getAdminName());
        admin.setAddress(body.getAddress());
        admin.setPhone(body.getPhone());
        adminRepository.save(admin);
        result.put("status", "200");
        result.put("message", "Data updated successfully.");
        result.put("data", adminData);
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
  public ResponseEntity<Object> deleteadmin(@PathVariable("id") long id) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Admin> admin = adminRepository.findById(id);
      HttpStatus status;
      if (admin.isPresent()) {
        adminRepository.deleteById(id);
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
