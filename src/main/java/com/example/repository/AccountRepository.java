package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

@Repository 
public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    // Add method to find an account by username and password; return Account object if found
    Account findByUsernameAndPassword(String username, String password); 
    Account findByUsername(String username);

}


