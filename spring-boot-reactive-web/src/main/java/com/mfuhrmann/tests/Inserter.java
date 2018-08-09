package com.mfuhrmann.tests;

import com.mfuhrmann.tests.domain.Quote;
import com.mfuhrmann.tests.repository.QuoteMongoReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Inserter {


    public static final int DOCUMENTS_COUNT = 100_000;
    @Autowired
    private QuoteMongoReactiveRepository repository;

    @PostConstruct
    public void init() {

        repository.deleteAll();


        List<Quote> collect = IntStream.range(0, DOCUMENTS_COUNT)
                .mapToObj(x -> new Quote(String.valueOf(x), String.valueOf(x + x + x), String.valueOf(x + x + x + x + x)))
                .collect(Collectors.toList());

        repository.saveAll(collect);

    }
}
