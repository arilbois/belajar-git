package com.pd79.librarydemosyahril.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pd79.librarydemosyahril.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
