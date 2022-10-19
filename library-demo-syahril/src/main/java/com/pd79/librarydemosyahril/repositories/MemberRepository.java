package com.pd79.librarydemosyahril.repositories;

import org.springframework.stereotype.Repository;

import com.pd79.librarydemosyahril.models.Member;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
