package com.kuldeep.spring_first_app.service;

import com.kuldeep.spring_first_app.entity.JournalEntry;
import com.kuldeep.spring_first_app.entity.User;
import com.kuldeep.spring_first_app.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry myjournalEntry, String userName){
        User user = userService.findByuserName(userName);
        JournalEntry saved = journalEntryRepo.save(myjournalEntry);
        user.getUser_journalEntry().add(saved);
        userService.saveEntry(user);
    }
    public List<JournalEntry> getsEntryServiceAll()
    {
        return journalEntryRepo.findAll();
    }
    public Optional<JournalEntry> findById(String id){
        return journalEntryRepo.findById(id);
    }

    public boolean deletebyId(String myid, String userName){
        User user = userService.findByuserName(userName);
        user.getUser_journalEntry().removeIf(x->x.getId().equals(myid));
        userService.saveEntry(user);
        journalEntryRepo.deleteById(myid);
        return true;
    }

    public String UpdateById(String myid, JournalEntry myEntry, String userName)
    {
        Optional<JournalEntry> myjournalEntry = journalEntryRepo.findById(myid);
        if(myjournalEntry != null)
        {
            journalEntryRepo.save(myEntry);
            return "Update Successfully";
        }
        else {
            return "No Entries Found";
        }

    }

    public void UpdateById(JournalEntry myEntry)
    {
        journalEntryRepo.save(myEntry);
    }
}


// Controller --> Service ---> repository