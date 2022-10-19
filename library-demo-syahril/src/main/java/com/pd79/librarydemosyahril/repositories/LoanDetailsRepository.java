package com.pd79.librarydemosyahril.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pd79.librarydemosyahril.models.LoanDetail;
import com.pd79.librarydemosyahril.models.LoanDetailKey;

@Repository
public interface LoanDetailsRepository extends JpaRepository<LoanDetail, LoanDetailKey> {
}
