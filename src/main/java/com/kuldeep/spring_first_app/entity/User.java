package com.kuldeep.spring_first_app.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="users")
@Data
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String passWord;
    @DBRef
    private List<JournalEntry> user_journalEntry = new ArrayList();

}