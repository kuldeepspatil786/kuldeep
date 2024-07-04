package com.kuldeep.spring_first_app.repository;

import com.kuldeep.spring_first_app.entity.JournalEntry;
import com.kuldeep.spring_first_app.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepo extends MongoRepository<User,String> {

    User findByuserName(String userName);


}

