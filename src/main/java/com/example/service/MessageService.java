package com.example.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private  MessageRepository messageRepository;
    @Autowired
    private  AccountRepository accountRepository;

   
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    
    public Message createMessage(String messageText, Integer postedBy, Long timePostedEpoch) {
        // If messageText does not fulfill user story requirements ---> return null  (greater than 0, less than 255)
        // For redundancy make sure it isn't empty of null) 
        if(messageText == null || messageText.isBlank() || messageText.length() < 0 || messageText.length() > 255) {
            return null;
        }
        // Validation logic to see if user exists located by FK posted_by/PK account_id (same value)
        boolean accountExists = accountRepository.existsById(postedBy);
        
        // If posted_by/account_id doesn't exist in Account table return null;
        if(!accountExists) {
            return null;
        }
        
        // Create and save the message
        Message message = new Message();
        // Set valid values to new Message object
        message.setMessage_text(messageText);
        message.setPosted_by(postedBy);
        message.setTime_posted_epoch(timePostedEpoch);

        // Return valid message creation request with Message object that was persisted
        // Including DB generated message_id
        return messageRepository.save(message);  
    }

    public List<Message> getAllMessages() {
        // Return all messages in database
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        // Return given message from messageId given
        Optional<Message> messageToReturn = messageRepository.findById(messageId);
        if(messageToReturn.isPresent()) {
            return messageToReturn.get();
        } else {
            return null;
        } 
    }

    public int deleteMessage(int messageId) {
        Optional<Message> messageToDelete = messageRepository.findById(messageId);
        if(messageToDelete.isPresent()) {
            messageRepository.deleteById(messageId);
            return 1;
        }
        return 0;   
    }

    public int updateMessageById(int messageId, Message message) {
        // Validate message exists
        Optional<Message> messageToUpdate = messageRepository.findById(messageId);
        
        String messageText = message.getMessage_text();
        // If message exists
        if(messageToUpdate.isPresent() && messageText != "" && messageText.length() < 255) {
            Message updatedMessage = messageToUpdate.get();
            updatedMessage.setMessage_text(messageText);
            messageRepository.save(updatedMessage);
            return 1; // Return 1 to indicate the number of rows updated which will be 1
        // If message does not exist
        } else {
            return 0; // If message does not exist return 0 to indicate failure
        }
    }

    public List<Message> getAccountMessages(int account_id) {
        List<Message> accountMessages = messageRepository.findByPostedBy(account_id);

        return accountMessages;
    }
}

