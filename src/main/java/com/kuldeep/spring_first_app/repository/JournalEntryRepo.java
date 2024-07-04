package com.kuldeep.spring_first_app.repository;

import com.kuldeep.spring_first_app.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry,String> {

}

