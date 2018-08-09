package com.mfuhrmann.tests.controller;

import com.mfuhrmann.tests.domain.Quote;
import com.mfuhrmann.tests.repository.QuoteMongoBlockingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class QuoteBlockingController {

    private static final int DELAY_PER_ITEM_MS = 100_000;

    private QuoteMongoBlockingRepository quoteMongoBlockingRepository;

    public QuoteBlockingController(final QuoteMongoBlockingRepository quoteMongoBlockingRepository) {
        this.quoteMongoBlockingRepository = quoteMongoBlockingRepository;
    }

    @GetMapping("/quotes-blocking-mapping")
    public Iterable<Quote> getQuotesBlocking() throws Exception {
        return quoteMongoBlockingRepository.findAll().stream()
                .map(Quote::getBook)
                .map(s -> s + s)
                .map(s -> new Quote(s, s + s, s + s + s))
                .collect(Collectors.toList());
    }

    @GetMapping("/quotes-blocking-findAll")
    public Iterable<Quote> getQuotesBlockingDb() throws Exception {
        return quoteMongoBlockingRepository.findAll();
    }

}
