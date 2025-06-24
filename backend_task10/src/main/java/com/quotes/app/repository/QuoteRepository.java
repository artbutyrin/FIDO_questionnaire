package com.quotes.app.repository;

import com.quotes.app.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Page<Quote> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
}