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

import com.pd79.librarydemosyahril.dtos.MemberDTO;
import com.pd79.librarydemosyahril.models.Member;
import com.pd79.librarydemosyahril.repositories.MemberRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/member")
public class MemberController {

  @Autowired
  private MemberRepository memberRepository;

  @PostMapping("/create")
  public ResponseEntity<Object> createMember(@RequestBody Member body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Member member = memberRepository.save(body);
      HttpStatus status = HttpStatus.CREATED;
      result.put("status", "201");
      result.put("message", "Member created successfully!");
      result.put("data", member);
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/read")
  public ResponseEntity<Object> getAllMember() {
    try {
      Map<String, Object> result = new HashMap<String, Object>();
      List<Member> listAllMember = memberRepository.findAll();
      HttpStatus status = HttpStatus.OK;
      List<MemberDTO> listAllmemberDTO = new ArrayList<>();
      for (Member memberEntity : listAllMember) {
        ModelMapper modelMapper = new ModelMapper();
        MemberDTO memberDTO = modelMapper.map(memberEntity, MemberDTO.class);
        listAllmemberDTO.add(memberDTO);
      }
      if (listAllMember.isEmpty()) {
        status = HttpStatus.NOT_FOUND;
        result.put("status", "404");
        result.put("message", "Get data success but no data.");
        result.put("data", listAllmemberDTO);
        return new ResponseEntity<Object>(result, status);
      }
      result.put("data", listAllmemberDTO);
      result.put("total", listAllmemberDTO.size());
      result.put("status", "200");
      result.put("message", "Read all data member success.");
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Object> updateMember(@PathVariable("id") long id, @RequestBody Member body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Member> memberData = memberRepository.findById(id);
      HttpStatus status;
      if (memberData.isPresent()) {
        Member member = memberData.get();
        member.setMemberName(body.getMemberName());
        member.setAddress(body.getAddress());
        member.setPhone(body.getPhone());
        memberRepository.save(member);
        result.put("status", "200");
        result.put("message", "Data updated successfully.");
        result.put("data", memberData);
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
  public ResponseEntity<Object> deleteMember(@PathVariable("id") long id) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Member> member = memberRepository.findById(id);
      HttpStatus status;
      if (member.isPresent()) {
        memberRepository.deleteById(id);
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
