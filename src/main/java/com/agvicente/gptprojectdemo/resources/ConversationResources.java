package com.agvicente.gptprojectdemo.resources;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.repositories.ConversationRepository;
import com.agvicente.gptprojectdemo.repositories.MessageRepository;
import com.agvicente.gptprojectdemo.services.GPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/conversation")
public class ConversationResources {

  @Autowired
    private GPTService gptService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private Configuration config;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message){

        Conversation conversation = conversationRepository.findById(config.getC().getId()).orElseThrow();
        List<Message> messages = new ArrayList<>(conversation.getMessages());

        if(messages.isEmpty()){
            Message configMessage = new Message();
            configMessage.setRole(ChatMessageAdapter.ROLE_SYSTEM);
            configMessage.setContent(config.getChatMessageConfig1());
            configMessage.setConversation(config.getC());
            configMessage.setSender(config.getU1());
            messageRepository.save(configMessage);

            Message next = gptService.getAnswerFrom(Arrays.asList(configMessage));

            //Salvando retorno do Chat
            next.setConversation(config.getC());
            next.setSender(config.getU1());
            messageRepository.save(next);

            return ResponseEntity.ok().body(next);
        }

        Message userMessage = new Message();
        userMessage.setRole(message.getRole());
        userMessage.setContent(message.getContent());
        userMessage.setConversation(config.getC());
        userMessage.setSender(config.getU1());
        messageRepository.save(userMessage);

        messages.add(userMessage);

        Message next = gptService.getAnswerFrom(messages);

        Message answer = new Message();
        answer.setRole(next.getRole());
        answer.setContent(next.getContent());
        answer.setConversation(config.getC());
        answer.setSender(config.getU1());
        messageRepository.save(answer);

        return ResponseEntity.ok().body(next);
    }
}
