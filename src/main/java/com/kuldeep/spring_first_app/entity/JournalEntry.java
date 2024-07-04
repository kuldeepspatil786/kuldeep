package com.kuldeep.spring_first_app.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="journal_entries1")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
//@ToString
//@Builder
//@Slf4j
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private String id;
    @NonNull
    private String title;
    private String content;
}
