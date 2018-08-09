package com.mfuhrmann.tests.controller;

import com.mfuhrmann.tests.domain.Quote;
import com.mfuhrmann.tests.repository.QuoteMongoReactiveRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class QuoteReactiveController {

    private static final int DELAY_PER_ITEM_MS = 100;

    private QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    public QuoteReactiveController(final QuoteMongoReactiveRepository quoteMongoReactiveRepository) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }





    @GetMapping("/quotes-reactive-mapping")
    public Flux<Quote> getQuoteFlux() {
        // If you want to use a shorter version of the Flux, use take(100) at the end of the statement below
        return quoteMongoReactiveRepository.findAll()
                .map(Quote::getBook)
                .map(s -> s + s)
                .map(s -> new Quote(s, s + s, s + s + s));


    }

    @GetMapping("/quotes-reactive-findAll")
    public Flux<Quote> getQuoteFluxDb() {
        // If you want to use a shorter version of the Flux, use take(100) at the end of the statement below
        return quoteMongoReactiveRepository.findAll();

    }

//    @GetMapping("/quotes-reactive-paged")
//    public Flux<Quote> getQuoteFlux(final @RequestParam(name = "page") int page,
//                                    final @RequestParam(name = "size") int size) {
//        return quoteMongoReactiveRepository.retrieveAllQuotesPaged(PageRequest.of(page, size))
//                .delayElements(Duration.ofMillis(100));
//    }

}
