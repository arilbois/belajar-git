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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd79.librarydemosyahril.dtos.LoanDetailsDTO;
import com.pd79.librarydemosyahril.models.*;
import com.pd79.librarydemosyahril.repositories.BookRepository;
import com.pd79.librarydemosyahril.repositories.LoanDetailsRepository;
import com.pd79.librarydemosyahril.repositories.LoanRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/loanDetails")

public class LoanDetailsController {

  @Autowired
  private LoanDetailsRepository loanDetailsRepository;

  @Autowired
  private LoanRepository loanRepository;

  @Autowired
  private BookRepository bookRepository;

  ModelMapper mapper = new ModelMapper();

  @PostMapping("/create")
  public ResponseEntity<Object> createbook(@RequestBody LoanDetail body) {
    try {
      Map<String, Object> result = new HashMap<>();
      HttpStatus status = HttpStatus.NOT_FOUND;
      Optional<Loan> loan = loanRepository.findById(body.getLoan().getLoanId());
      Optional<Book> book = bookRepository.findById(body.getBook().getBookId());
      if (loan.isPresent() && book.isPresent()) {
        LoanDetail loandDetails = loanDetailsRepository.save(body);
        result.put("status", "201");
        result.put("message", "loan created successfully!");
        result.put("data", loandDetails);
      } else if (!(loan.isPresent()) && !(book.isPresent())) {
        result.put("status", "404");
        result.put("message", "Create loan fail, loan id and book id not found.");
      } else if (!(loan.isPresent())) {
        result.put("status", "404");
        result.put("message", "Create loan fail, loan id not found.");
      } else if (!(book.isPresent())) {
        result.put("status", "404");
        result.put("message", "Create book fail, book id not found.");
      }
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/read")
  public ResponseEntity<Object> getAllLoanDetails() {
    try {
      Map<String, Object> result = new HashMap<>();
      List<LoanDetail> listAllLoanDetails = loanDetailsRepository.findAll();
      HttpStatus status = HttpStatus.OK;
      List<LoanDetailsDTO> listLoanDetailsDTO = new ArrayList<>();
      for (LoanDetail loanDetailsEntity : listAllLoanDetails) {
        ModelMapper modelMapper = new ModelMapper();
        LoanDetailsDTO loanDetailsDTO = modelMapper.map(loanDetailsEntity, LoanDetailsDTO.class);
        listLoanDetailsDTO.add(loanDetailsDTO);
      }
      if (listAllLoanDetails.isEmpty()) {
        status = HttpStatus.NOT_FOUND;
        result.put("status", "404");
        result.put("message", "Get all Loan detail success but no data.");
        result.put("data", listLoanDetailsDTO);
        return new ResponseEntity<Object>(result, status);
      } else {
        status = HttpStatus.OK;
        result.put("status", "200");
        result.put("message", "Get all Loan detail success.");
        result.put("data", listLoanDetailsDTO);
        result.put("total", listLoanDetailsDTO.size());

        return new ResponseEntity<Object>(result, status);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
