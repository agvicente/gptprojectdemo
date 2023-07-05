package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.model.ConversationDTO;
import com.agvicente.gptprojectdemo.model.PromptDTO;
import com.agvicente.gptprojectdemo.model.enums.InteractionTypeEnum;
import com.agvicente.gptprojectdemo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
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
        /**
         * Camada de resiliência para se der time out ele refazer a pergunta para o chat
         */
       return ChatMessageAdapter.getAnswerFrom(config,
                ChatMessageAdapter.getChatCompletionRequest(config, messages));
    }

    public ConversationDTO sendInitMessage(){
        Conversation conversation = conversationService.saveNewConversation();
        conversation = conversationService.saveConfigMessageOn(InteractionTypeEnum.INIT_CONVERSATION, conversation, config);
        Message message = getAnswerFrom(conversation.getMessages().stream().toList());
        conversation = conversationService.saveMessageOn(conversation, message);
        ConversationDTO conversationDTO = ConversationDTO.conversationToConversationDTO(conversation,
                InteractionTypeEnum.getByCode(InteractionTypeEnum.INIT_CONVERSATION.getCode() + 1).getDescription());
        return conversationDTO;
    }

    public ConversationDTO sendUserMessageAndGetAnswer(Long idConversation, InteractionTypeEnum interactionType, Collection<Message> messages) throws Exception {
        /**
         * Não permitir que salve a mensagem e mande o próximo interaction type caso o usuário não retorne o que
         * o chat precisa
         */

        Conversation conversation = conversationService.getConversationById(idConversation);
        Message userMessage = getMessageByRole(messages, ChatMessageAdapter.ROLE_USER);
        String systemMessageContent = config.getConfigMessages().get(interactionType);
        Message systemMessage = MessageService.createMessage(ChatMessageAdapter.ROLE_SYSTEM, systemMessageContent);
        conversation = conversationService.saveMessagesOn(conversation, List.of(systemMessage, userMessage));
        Message answer = getAnswerFrom(conversation.getMessages().stream().toList());
        conversation = conversationService.saveMessageOn(conversation, answer);
        ConversationDTO conversationDTO = ConversationDTO.conversationToConversationDTO(conversation,
                InteractionTypeEnum.getByCode(interactionType.getCode() + 1).getDescription());
        return conversationDTO;
    }

    public PromptDTO getPromptFromConversation(Long idConversation) throws Exception {
        Conversation conversation = conversationService.getConversationById(idConversation);
        List<Message> messages = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(config.getConfigMessages().get(InteractionTypeEnum.GENERATE_PROMPT))
                .append(" ")
                .append(conversation.toString());
        Message requestPrompt =  MessageService.createMessage(ChatMessageAdapter.ROLE_SYSTEM, sb.toString());
        Message answer = getAnswerFrom(List.of(requestPrompt));
        conversation = conversationService.saveMessageOn(conversation, answer);
        PromptDTO promptDTO = PromptDTO.getPromptDTO(conversation, answer.getContent());
        return promptDTO;
    }

    private Message getMessageByRole(Collection<Message> messages, String role){
        if (role == null) return null;
        return messages.stream().filter(m -> m.getRole().equals(role)).findFirst().orElse(null);
    }
}
