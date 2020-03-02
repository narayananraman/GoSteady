package com.sideproject.Services;

import com.sideproject.Daos.UserProfilesCustomDao;
import com.sideproject.Daos.UserProfilesDao;
import com.sideproject.Entity.UserProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfilesService {

    @Autowired
    private UserProfilesDao userProfilesDao;

    @Autowired
    private UserProfilesCustomDao userProfilesCustomDao;

    public List<UserProfiles> retrieveAllUsers() {
        return userProfilesDao.findAll();
    }

    public UserProfiles retrieveUserById(String userId) {
        return userProfilesDao.findByUserId(userId);
    }

    public UserProfiles retrieveUserByName(String userName) {
        return userProfilesDao.findByUserName(userName);
    }

    public void addNewUser(UserProfiles user) {
        userProfilesDao.insert(user);
    }

    public void deleteUser(UserProfiles user) {
        userProfilesDao.delete(user);
    }

    public void updateUser(UserProfiles newUser, UserProfiles oldUser) {
        userProfilesCustomDao.updateUser(newUser, oldUser);
    }
}
