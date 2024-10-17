package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Method for registering account
    public Account registerAccount(Account newAccount) {
        // Step 1 --> Check if account w/ same username already exists;
        Account usernameExists = accountRepository.findByUsername(newAccount.getUsername());
        String givenPassword = newAccount.getPassword();
        String givenUsername = newAccount.getUsername();

        // Step 2 --> If username already exists, return an appropriate response
        if(usernameExists != null || givenPassword.length() < 4 || (givenUsername.length() == 0)) {
            // IN PROGRESS --> Can return error response or throw exception here
            // IN PROGRESS --> For now, null is returned to indicate registration failure
            return null;
        }

        // Step 3 --> Create and save the new Account object
        Account savedAccount = accountRepository.save(newAccount);

        // Step 4 --> Return created Account object in response
        return savedAccount;
    }

    // Add method for user login
    public Account loginUser(String username, String password) {
        // Check if account with provided username and password exists
        return accountRepository.findByUsernameAndPassword(username, password);
    }
}

//
