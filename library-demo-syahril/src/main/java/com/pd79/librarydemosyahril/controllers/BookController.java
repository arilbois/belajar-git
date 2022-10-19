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

import com.pd79.librarydemosyahril.dtos.BookDTO;
import com.pd79.librarydemosyahril.models.Author;
import com.pd79.librarydemosyahril.models.Book;
import com.pd79.librarydemosyahril.models.Publisher;
import com.pd79.librarydemosyahril.repositories.AuthorRepository;
import com.pd79.librarydemosyahril.repositories.BookRepository;
import com.pd79.librarydemosyahril.repositories.PublisherRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/book")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private PublisherRepository publisherRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @PostMapping("/create")
  public ResponseEntity<Object> createbook(@RequestBody Book body) {
    try {
      Map<String, Object> result = new HashMap<>();
      HttpStatus status = HttpStatus.NOT_FOUND;
      Optional<Author> author = authorRepository.findById(body.getAuthor().getAuthorId());
      Optional<Publisher> publisher = publisherRepository.findById(body.getPublisher().getPublisherId());
      if (author.isPresent() && publisher.isPresent()) {
        Book book = bookRepository.save(body);
        result.put("status", "201");
        result.put("message", "Book created successfully!");
        result.put("data", book);
      } else if (!(author.isPresent()) && !(publisher.isPresent())) {
        result.put("status", "404");
        result.put("message", "Create Book fail, Author id and publisher id not found.");
      } else if (!(author.isPresent())) {
        result.put("status", "404");
        result.put("message", "Create Book fail, Author id not found.");
      } else if (!(publisher.isPresent())) {
        result.put("status", "404");
        result.put("message", "Create Book fail, publisher id not found.");
      }
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/read")
  public ResponseEntity<Object> getAllbook() {
    try {
      Map<String, Object> result = new HashMap<String, Object>();
      List<Book> listAllbook = bookRepository.findAll();
      HttpStatus status = HttpStatus.OK;
      List<BookDTO> listAllbookDTO = new ArrayList<>();
      for (Book bookEntity : listAllbook) {
        ModelMapper modelMapper = new ModelMapper();
        BookDTO bookDTO = modelMapper.map(bookEntity, BookDTO.class);
        listAllbookDTO.add(bookDTO);
      }
      if (listAllbook.isEmpty()) {
        status = HttpStatus.NOT_FOUND;
        result.put("status", "404");
        result.put("message", "Get data success but no data.");
        result.put("data", listAllbookDTO);
        return new ResponseEntity<Object>(result, status);
      }
      result.put("data", listAllbookDTO);
      result.put("total", listAllbookDTO.size());
      result.put("status", "200");
      result.put("message", "Read all data book success.");
      return new ResponseEntity<Object>(result, status);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Object> updatebook(@PathVariable("id") long id, @RequestBody Book body) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Book> bookData = bookRepository.findById(id);
      HttpStatus status;
      if (bookData.isPresent()) {
        Book book = bookData.get();
        book.setBookTitle(body.getBookTitle());
        book.setPublisher(body.getPublisher());
        book.setAuthor(body.getAuthor());
        book.setReleaseDate(body.getReleaseDate());
        book.setQuantityBook(body.getQuantityBook());
        book.setLoanPriceBook(body.getLoanPriceBook());

        bookRepository.save(book);
        result.put("status", "200");
        result.put("message", "Data updated successfully.");
        result.put("data", bookData);
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
  public ResponseEntity<Object> deletebook(@PathVariable("id") long id) {
    try {
      Map<String, Object> result = new HashMap<>();
      Optional<Book> book = bookRepository.findById(id);
      HttpStatus status;
      if (book.isPresent()) {
        bookRepository.deleteById(id);
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
