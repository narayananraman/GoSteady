package com.sideproject.Daos;

import com.sideproject.Entity.CredVault;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("loginDao")
public interface LoginDao extends MongoRepository<CredVault, Integer> {

    CredVault findByUserName(String userName);
}
