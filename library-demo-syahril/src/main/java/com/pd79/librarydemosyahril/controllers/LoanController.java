package com.pd79.librarydemosyahril.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd79.librarydemosyahril.dtos.LoanDTO;
import com.pd79.librarydemosyahril.models.Book;
import com.pd79.librarydemosyahril.models.Loan;
import com.pd79.librarydemosyahril.models.LoanDetail;
import com.pd79.librarydemosyahril.models.LoanDetailKey;
import com.pd79.librarydemosyahril.repositories.BookRepository;
import com.pd79.librarydemosyahril.repositories.LoanDetailsRepository;
import com.pd79.librarydemosyahril.repositories.LoanRepository;
import com.pd79.librarydemosyahril.services.LoanService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/loan")
public class LoanController {
  @Autowired
  private LoanRepository loanRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private LoanDetailsRepository loanDetailsRepository;

  private ModelMapper modelMapper = new ModelMapper();

  // create
  @PostMapping("/create")
  public ResponseEntity<Object> createLoan(@Valid @RequestBody Loan body) {
    try {
      Map<String, Object> result = new HashMap<String, Object>();
      HttpStatus status = HttpStatus.OK;
      Loan loanEntity = new Loan();
      body.setActualReturnDate(body.getReturnDate());
      if ((body.getLoanDate().compareTo(body.getReturnDate()) < 0)
          && (body.getLoanDate().compareTo(body.getActualReturnDate()) < 0)) {
        for (LoanDetail loanDetail : body.getLoanDetails()) {
          Optional<Book> book = bookRepository.findById(loanDetail.getLoanKey().getBookId());
          if (book.isPresent()) {
            Integer loanDay = LoanService.getDiffrentDay(body.getReturnDate(), body.getLoanDate());
            status = HttpStatus.CREATED;
            result.put("status", "201");
            result.put("message", "Create Loan success.");
            result.put("borrow time", loanDay + " day");
          } else {
            status = HttpStatus.NOT_FOUND;
            result.put("status", "404");
            result.put("message", "Create Loan fail, book id " + loanDetail.getLoanKey().getBookId() + " not found.");
            return new ResponseEntity<Object>(result, status);
          }
          loanDetail.setBook(book.get());
        }
      } else if (body.getLoanDate().compareTo(body.getReturnDate()) > 0) {
        status = HttpStatus.BAD_REQUEST;
        result.put("status", "502");
        result.put("message", "Create Loan Faill, return date can't exceed loan date");
        return new ResponseEntity<Object>(result, status);
      } else {
        status = HttpStatus.BAD_REQUEST;
        result.put("status", "502");
        result.put("message", "Create Loan Faill, return date can't exceed loan date");
        return new ResponseEntity<Object>(result, status);
      }
      loanEntity = loanRepository.save(body);
      for (LoanDetail loanDetail : body.getLoanDetails()) {
        LoanDetailKey loanKey = new LoanDetailKey();
        loanKey.setBookId(loanDetail.getLoanKey().getBookId());
        loanKey.setLoanId(loanEntity.getLoanId());
        loanDetail.setLoanKey(loanKey);
        Integer qty = loanDetail.getQuantityBook();
        Integer dayUntilReturn = LoanService.getDiffrentDay(body.getReturnDate(),
            body.getLoanDate());
        loanDetail
            .setLoanPricePerDay(LoanService.calculateLoanPricePerDay(loanDetail.getBook().getLoanPriceBook(), qty,
                dayUntilReturn));
        Book book = bookRepository.findById(loanDetail.getBook().getBookId()).get();
        if (book.getQuantityBook() > qty) {
          book.setQuantityBook(LoanService.calculateStockBookLoan(book.getQuantityBook(), qty));
        } else {
          status = HttpStatus.BAD_REQUEST;
          result.put("status", "400");
          result.put("message", "Create Loan fail, stock is less than the quantity to be borrowed ");
          return new ResponseEntity<Object>(result, status);
        }
      }
      List<LoanDetail> listLoanDetails = loanDetailsRepository.saveAll(body.getLoanDetails());
      loanEntity.setLoanDetails(listLoanDetails);
      loanRepository.save(loanEntity);
      LoanDTO loanDto = modelMapper.map(loanEntity, LoanDTO.class);
      result.put("data", loanDto);
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("return/{id}")
  public ResponseEntity<Object> returnBook(@PathVariable("id") Long loanId, @Valid @RequestBody Loan body) {
    Map<String, Object> result = new HashMap<>();
    HttpStatus status = HttpStatus.NOT_FOUND;
    try {
      if (loanRepository.findById(loanId).isPresent()) {
        Loan loanEntity = loanRepository.findById(loanId).get();
        if (LoanService.isLateChargerDay(loanEntity.getReturnDate(), body.getActualReturnDate())) {
          loanEntity.setActualReturnDate(body.getActualReturnDate());
          Integer diffrentDay = LoanService.getDiffrentDay(body.getActualReturnDate(), loanEntity.getReturnDate());

          for (LoanDetail loanDetail : loanEntity.getLoanDetails()) {
            Book book = loanDetail.getBook();
            book.setQuantityBook(LoanService.calculateStockBookReturn(
                book.getQuantityBook(), loanDetail.getQuantityBook()));
            loanEntity.setLateChargerPrice(
                LoanService.calculateLateChargerPrice(loanEntity.getLoanDetails(), diffrentDay));
            bookRepository.save(book);
          }
          loanRepository.save(loanEntity);
          status = HttpStatus.OK;
          result.put("status", "200");
          result.put("message", "book returned successfully.");
          result.put("late return", diffrentDay + " day");
          result.put("borrow time",
              LoanService.getDiffrentDay(body.getActualReturnDate(), loanEntity.getLoanDate()) + " day");
          result.put("charge Loan", loanEntity.getLateChargerPrice());
        } else {
          status = HttpStatus.BAD_GATEWAY;
          result.put("status", "502");
          result.put("message", "Actual return date can only be returned after the loan date");
          return new ResponseEntity<Object>(result, status);
        }
      } else {
        result.put("status", "404");
        result.put("message", "Update data fail. Data not found.");
      }
    } catch (Exception e) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      result.put("status", "500");
      result.put("message", "Return loan Book failed.");
    }
    return new ResponseEntity<Object>(result, status);
  }

  @GetMapping("/read")
  public ResponseEntity<Object> readAllLoan() {
    try {
      Map<String, Object> result = new HashMap<String, Object>();
      HttpStatus status;
      List<Loan> listAllLoan = loanRepository.findAll();
      List<LoanDTO> listAllLoanDTO = new ArrayList<LoanDTO>();
      for (Loan loan : listAllLoan) {
        LoanDTO loanDTO = modelMapper.map(loan, LoanDTO.class);
        listAllLoanDTO.add(loanDTO);
      }
      if (listAllLoan.isEmpty()) {
        status = HttpStatus.NOT_FOUND;
        result.put("status", "404");
        result.put("message", "Read all loan success but no data.");
        result.put("data", listAllLoanDTO);
      } else {
        status = HttpStatus.OK;
        result.put("status", "200");
        result.put("message", "Read all loan success.");
        result.put("data", listAllLoanDTO);
      }
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
