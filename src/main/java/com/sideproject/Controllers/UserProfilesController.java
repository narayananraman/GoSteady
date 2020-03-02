package com.sideproject.Controllers;

import com.sideproject.Entity.UserProfiles;
import com.sideproject.Services.UserProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserProfilesController {

    @Autowired
    UserProfilesService userProfilesService;

    @GetMapping("/User")
    public ResponseEntity<List<UserProfiles>> retrieveAllUsers() {
        List<UserProfiles> users = userProfilesService.retrieveAllUsers();
        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/User/{userId}")
    public ResponseEntity<UserProfiles> retrieveUser(@PathVariable("userId") String userId) {
        UserProfiles user = userProfilesService.retrieveUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/User", produces = "application/json")
    public ResponseEntity<?> addNewUser(@RequestBody UserProfiles userProfile) {
        Map<String, UserProfiles> response = new HashMap<>();
        UserProfiles user = userProfilesService.retrieveUserByName(userProfile.getUserName());
        if (user == null) {
            List<UserProfiles> users = userProfilesService.retrieveAllUsers();
            userProfile.setUserId(users.get(users.size()-1).getUserId() + 1);
            userProfilesService.addNewUser(userProfile);
            user = userProfilesService.retrieveUserByName(userProfile.getUserName());
            response.put("New User Created Successfully!",user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("UserName Already Exists!",user);
            return new ResponseEntity<> (response, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @DeleteMapping(value = "/User", produces = "application/json")
    public ResponseEntity<?> deleteUser(@RequestParam("userName") String userName) {
        Map<String, UserProfiles> response = new HashMap<>();
        UserProfiles user = userProfilesService.retrieveUserByName(userName);
        if (user != null) {
            userProfilesService.deleteUser(user);
            user = userProfilesService.retrieveUserByName(userName);
            if(user == null) {
                response.put("Account Deleted Successfully!", user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("Exception While Deleting User!",user);
                return new ResponseEntity<> (response, HttpStatus.NOT_IMPLEMENTED);
            }
        } else {
            response.put("UserName Does not Exists!",user);
            return new ResponseEntity<> (response, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PutMapping(value = "/User", produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody UserProfiles userProfile) {
        Map<String, UserProfiles> response = new HashMap<>();
        UserProfiles user = userProfilesService.retrieveUserByName(userProfile.getUserName());
        userProfile.setUserId(user.getUserId());
        if (user != null) {
            userProfilesService.updateUser(userProfile, user);
            user = userProfilesService.retrieveUserByName(userProfile.getUserName());
            response.put("Account Updated Successfully!", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("UserName Does not Exists!", userProfile);
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
