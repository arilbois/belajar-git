package com.pd79.librarydemosyahril.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pd79.librarydemosyahril.models.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
