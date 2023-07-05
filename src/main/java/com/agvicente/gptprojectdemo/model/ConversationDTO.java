package com.agvicente.gptprojectdemo.model;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.model.enums.InteractionTypeEnum;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ConversationDTO {

    private Long id;

    private String interactionType;

    private Collection<Message> messages;

    public static ConversationDTO conversationToConversationDTO(Conversation conversation, String interactionType){
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setId(conversation.getId());
        conversationDTO.setInteractionType(interactionType);
        conversationDTO.setMessages(conversation.getMessages()
                .stream()
                .filter(m -> !m.getRole().equals(ChatMessageAdapter.ROLE_SYSTEM))
                .distinct()
                .toList());
        return conversationDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }
}
