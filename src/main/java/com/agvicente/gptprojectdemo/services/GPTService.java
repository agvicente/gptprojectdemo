package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.model.ConversationDTO;
import com.agvicente.gptprojectdemo.model.enums.InteractionTypeEnum;
import com.agvicente.gptprojectdemo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
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

    public ConversationDTO sendInitMessage(){
        Conversation conversation = conversationService.saveNewConversation();
        conversation = conversationService.saveConfigMessageOn(InteractionTypeEnum.INIT_CONVERSATION, conversation, config);
        Message message = getAnswerFrom(conversation.getMessages().stream().toList());
        conversation = conversationService.saveMessageOn(conversation, message);
        ConversationDTO conversationDTO = ConversationDTO.conversationToConversationDTO(conversation);
        return conversationDTO;
    }

    public ConversationDTO sendUserMessageAndGetAnswer(Long idConversation, InteractionTypeEnum interactionType, Collection<Message> messages) throws Exception {
        Conversation conversation = conversationService.getConversationById(idConversation);
        Message userMessage = getMessageByRole(messages, ChatMessageAdapter.ROLE_USER);
        String systemMessageContent = config.getConfigMessages().get(interactionType);
        Message systemMessage = MessageService.createMessage(ChatMessageAdapter.ROLE_SYSTEM, systemMessageContent);
        conversation = conversationService.saveMessagesOn(conversation, List.of(systemMessage, userMessage));
        Message answer = getAnswerFrom(conversation.getMessages().stream().toList());
        conversation = conversationService.saveMessageOn(conversation, answer);
        ConversationDTO conversationDTO = ConversationDTO.conversationToConversationDTO(conversation);
        return conversationDTO;
    }

    private Message getMessageByRole(Collection<Message> messages, String role){
        if (role == null) return null;
        return messages.stream().filter(m -> m.getRole().equals(role)).findFirst().orElse(null);
    }
}
