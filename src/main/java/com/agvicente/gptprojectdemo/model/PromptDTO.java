package com.agvicente.gptprojectdemo.model;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;

import java.util.Collection;

public class PromptDTO {

    private Long idConversation;

    private String prompt;

    public static PromptDTO getPromptDTO(Conversation conversation, String prompt) {
        PromptDTO promptDTO = new PromptDTO();
        promptDTO.setIdConversation(conversation.getId());
        promptDTO.setPrompt(prompt);
        return promptDTO;
    }

    public Long getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(Long idConversation) {
        this.idConversation = idConversation;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
