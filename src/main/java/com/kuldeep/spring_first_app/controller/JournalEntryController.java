package com.kuldeep.spring_first_app.controller;

import com.kuldeep.spring_first_app.entity.JournalEntry;
import com.kuldeep.spring_first_app.entity.User;
import com.kuldeep.spring_first_app.service.JournalEntryService;
import com.kuldeep.spring_first_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController
    {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    // Read ALL
   @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesForUser(@PathVariable String userName)
   {
       User user = userService.findByuserName(userName);
       List<JournalEntry> all = user.getUser_journalEntry();
       if(all != null && !all.isEmpty())
       {
           return new ResponseEntity<>(all,HttpStatus.OK);
       }
       return new ResponseEntity<>(all,HttpStatus.NO_CONTENT);
   }

   // Create Journal
    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName)
    {
        try{
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }
    }

    //Get Journal By ID
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable String myId)
    {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent() && journalEntry !=null)
        {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(journalEntry.get(), HttpStatus.NOT_FOUND);
    }

    //Delete
    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable String myId,@PathVariable String userName)
    {
        journalEntryService.deletebyId(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateJournal
            (@PathVariable String id
            ,@RequestBody JournalEntry newEntry
            ,@PathVariable String userName)
    {
        try {
            JournalEntry old = journalEntryService.findById(id).orElse(null);
            if(old != null){
                old.setTitle(newEntry.getTitle() != null && ! newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && ! newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.UpdateById(old);
                return new ResponseEntity<>(newEntry,HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(newEntry,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newEntry,HttpStatus.NOT_FOUND);
    }
}