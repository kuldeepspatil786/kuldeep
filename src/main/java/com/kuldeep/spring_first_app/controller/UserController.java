package com.kuldeep.spring_first_app.controller;

import com.kuldeep.spring_first_app.entity.JournalEntry;
import com.kuldeep.spring_first_app.entity.User;
import com.kuldeep.spring_first_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController
    {
        @Autowired
        private UserService userService;

        @GetMapping
        public List<User> getALlUsers()
        {
            return userService.getAll();
        }
        @PostMapping
        public void creatUser(@RequestBody User myuser)
        {
            userService.saveEntry(myuser);

        }
        @PutMapping("/{userName}")
        public ResponseEntity<?> updateUser(@RequestBody User myuser, @PathVariable String userName)
        {
            User userInDB = userService.findByuserName(userName);
            if(userInDB != null)
            {
                userInDB.setUserName(myuser.getUserName());
                userInDB.setPassWord(myuser.getPassWord());
                userService.saveEntry(userInDB);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }

    }