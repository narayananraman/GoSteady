package com.sideproject.Daos;

import com.mongodb.client.result.UpdateResult;
import com.sideproject.Entity.UserProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository("userProfilesCustomDao")
public class UserProfilesCustomDao{

    @Autowired
    MongoTemplate mongoTemplate;

    public void updateUser(UserProfiles newUser, UserProfiles oldUser) {

        Query query = new Query();
            query.addCriteria(Criteria.where("userName").is(newUser.getUserName()));

        Update update = new Update();
            update.set("userDob", newUser.getUserDob());

        UpdateResult results = mongoTemplate.updateFirst(query, update, UserProfiles.class);

    }

}
