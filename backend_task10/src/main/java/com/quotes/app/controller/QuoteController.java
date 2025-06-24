package com.quotes.app.controller;

import com.quotes.app.entity.Quote;
import com.quotes.app.service.QuoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping
    public ResponseEntity<?> createQuote(@Valid @RequestBody Quote quote) {
        try {
            Quote savedQuote = quoteService.createQuote(quote);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("id", savedQuote.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuoteById(@PathVariable Long id) {
        try {
            Quote quote = quoteService.getQuoteById(id);
            return ResponseEntity.ok(quote);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Quote>> getQuotes(
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Quote> quotes;
        
        if (author != null && !author.trim().isEmpty()) {
            quotes = quoteService.getQuotesByAuthor(author, pageable);
        } else {
            quotes = quoteService.getAllQuotes(pageable);
        }
        
        return ResponseEntity.ok(quotes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuote(@PathVariable Long id, @Valid @RequestBody Quote quote) {
        try {
            Quote updatedQuote = quoteService.updateQuote(id, quote);
            return ResponseEntity.ok(updatedQuote);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuote(@PathVariable Long id) {
        try {
            quoteService.deleteQuote(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}