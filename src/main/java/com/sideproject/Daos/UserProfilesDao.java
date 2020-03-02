package com.sideproject.Daos;

import com.sideproject.Entity.UserProfiles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfilesDao extends MongoRepository<UserProfiles, Integer> {

    UserProfiles findByUserId(String userId);

    UserProfiles findByUserName(String userName);

}
