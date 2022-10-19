package com.pd79.librarydemosyahril.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pd79.librarydemosyahril.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
