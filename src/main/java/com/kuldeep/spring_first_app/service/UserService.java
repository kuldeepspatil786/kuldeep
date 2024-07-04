package com.kuldeep.spring_first_app.service;

import com.kuldeep.spring_first_app.entity.User;
import com.kuldeep.spring_first_app.repository.UserEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserEntryRepo userRepo;

    public void saveEntry(User myUser){
        userRepo.save(myUser);
    }
    public List<User> getAll()
    {
        return userRepo.findAll();
    }
    public User findById(String id){
        return userRepo.findById(id).orElse(null);
    }

    public boolean deletebyId(String myid){
        userRepo.deleteById(myid);
        return true;
    }

    public User findByuserName(String username)
    {
        return userRepo.findByuserName(username);

    }
//    public String UpdateById(String myid,User myEntry)
//    {
//        Optional<User> myUser = userService.findById(myid);
//        if(myUser != null)
//        {
//            userService.save(myEntry);
//            return "Update Successfully";
//        }
//        else {
//            return "No Entries Found";
//        }
//
//    }
}


// Controller --> Service ---> repository