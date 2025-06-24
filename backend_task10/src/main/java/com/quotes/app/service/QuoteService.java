package com.quotes.app.service;

import com.quotes.app.entity.Quote;
import com.quotes.app.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote not found with id: " + id));
    }

    public Page<Quote> getAllQuotes(Pageable pageable) {
        return quoteRepository.findAll(pageable);
    }

    public Page<Quote> getQuotesByAuthor(String author, Pageable pageable) {
        return quoteRepository.findByAuthorContainingIgnoreCase(author, pageable);
    }

    public Quote updateQuote(Long id, Quote updatedQuote) {
        Quote existingQuote = getQuoteById(id);
        existingQuote.setAuthor(updatedQuote.getAuthor());
        existingQuote.setText(updatedQuote.getText());
        return quoteRepository.save(existingQuote);
    }

    public void deleteQuote(Long id) {
        if (!quoteRepository.existsById(id)) {
            throw new RuntimeException("Quote not found with id: " + id);
        }
        quoteRepository.deleteById(id);
    }
}
