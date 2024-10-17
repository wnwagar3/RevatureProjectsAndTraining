package com.example.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.MessageService;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping
public class SocialMediaController {
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final MessageService messageService;


    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }
    // Endpoint and ReturnEntity logic for registering user
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        // Call the registration logic in AccountService
        Account registeredAccount = accountService.registerAccount(account);

        if (registeredAccount != null) {
            // Return ResponseEntity with registered account and 200 status
            return new ResponseEntity<>(registeredAccount, HttpStatus.OK);
        } else {
            // Return a 400 status code (BAD REQUEST) if regisration fails
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    // Endpoint and ReturnEntity logic for user login
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        // Call login logic in AccountService
        Account loggedInAccount = accountService.loginUser(account.getUsername(), account.getPassword());

        if (loggedInAccount != null) {
            //Return a ResponseEntity w/ logged-in account and 200 status
            return new ResponseEntity<>(loggedInAccount, HttpStatus.OK);
        } else {
            // Return 401 UNAUTHORIZED if login fails
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    // Endpoint and ReturnEntity logic for creating message
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message ) {
        // Extract components of Message object
        String messageText = message.getMessage_text();
        Integer postedBy = message.getPosted_by();
        Long timePostedEpoch = message.getTime_posted_epoch();
        
        Message createdMessage = messageService.createMessage(messageText, postedBy, timePostedEpoch);

        if(createdMessage != null) {
            return new ResponseEntity<>(createdMessage, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint mapping and ReturnEntity logic for returning all messages in DB (Ex: Social Media Newsfeed)
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        // Create list with all message objects in DB
        List<Message> messagesList = messageService.getAllMessages();

        // Return the list of messages objects with a 200 OK
        return ResponseEntity.status(200).body(messagesList);
    }

    // Endpoing mapping and ReturnEntity logic for returning a specific message given specific message_id
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("message_id") Integer messageId) {
        // COMMMENT HERE
        Message message = messageService.getMessageById(messageId);

        if(message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.ok().build();
        }
    }
    // Endpoing mapping and ResponseEntity logic for deleting specific message given specific message_id
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable("message_id") int messageId) {
        int deleted = messageService.deleteMessage(messageId);

        if (deleted == 1) {
            // Message deleted successfully, return 1 in the response body with status 200
            return ResponseEntity.ok(1);
        } else {
            // Message not found, return an empty response body with status 200
            return ResponseEntity.ok(0);
        }
    }
    // Endpoing mapping and ResponseEntity logic for updating a message given specific message_id
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable("message_id")int messageId, @RequestBody Message message) {
        
        int rowsUpdated = messageService.updateMessageById(messageId, message);
        if(rowsUpdated > 0) {
            return ResponseEntity.status(200).body(rowsUpdated); // Comment
        } else {
            return ResponseEntity.status(400).body(0);
        }   
    }
    // Endpoing mapping and ResponseEntity logic for getting all messages from specific account given specific account_id (Ex: Social Media Profile Page)
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAccountMessages(@PathVariable("account_id") int accountId) {
        List<Message> accountMessages = messageService.getAccountMessages(accountId);

        return ResponseEntity.status(200).body(accountMessages);  
    }
}

