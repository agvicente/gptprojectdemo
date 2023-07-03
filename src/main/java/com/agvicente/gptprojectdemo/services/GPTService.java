package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

@Service
public class GPTService {

    @Autowired
    private Configuration config;

    @Autowired
    private ConversationService conversationService;

    private Message getAnswerFrom (List<Message> messages) {
       return ChatMessageAdapter.getAnswerFrom(config,
                ChatMessageAdapter.getChatCompletionRequest(config, messages));
    }

    public Conversation sendConfigMessage(){
        Conversation conversation = conversationService.saveNewConversation();
        conversation = conversationService.saveConfigMessage(conversation, config);
        Message message = getAnswerFrom(conversation.getMessages().stream().toList());
        conversation = conversationService.saveMessageOn(conversation, message);
        return conversation;
    }

}
