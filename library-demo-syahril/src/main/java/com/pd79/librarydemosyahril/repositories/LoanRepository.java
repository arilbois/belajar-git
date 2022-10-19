package com.pd79.librarydemosyahril.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pd79.librarydemosyahril.models.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
