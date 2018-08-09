package com.mfuhrmann.tests.repository;

import com.mfuhrmann.tests.domain.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteMongoBlockingRepository extends MongoRepository<Quote, String> {
}
