package com.agvicente.gptprojectdemo.resources;

import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.model.ConversationDTO;
import com.agvicente.gptprojectdemo.model.PromptDTO;
import com.agvicente.gptprojectdemo.model.enums.InteractionTypeEnum;
import com.agvicente.gptprojectdemo.services.ConversationService;
import com.agvicente.gptprojectdemo.services.GPTService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
@RequestMapping(value = "/api")
public class ConversationResources {

    @Autowired
    private Configuration config;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private GPTService gptService;

    @PostMapping(value = "/chat")
    public ResponseEntity<ConversationDTO> initChatConversation(){
        ConversationDTO conversationDTO = gptService.sendInitMessage();
        return ResponseEntity.ok().body(conversationDTO);
    }
    @PostMapping(value = "/chat/{idConversation}")
    public ResponseEntity<ConversationDTO> getAnswerFromChat(@NotNull @PathVariable Long idConversation, @NotNull @RequestBody ConversationDTO conversationDTO){

        ConversationDTO _conversationDTO = null;
        try {
            _conversationDTO = gptService.sendUserMessageAndGetAnswer(idConversation,
                            InteractionTypeEnum.getByDescription(conversationDTO.getInteractionType()),
                            conversationDTO.getMessages());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(_conversationDTO);
    }

    @PostMapping(value = "/chat/prompt/{idConversation}")
    public ResponseEntity<PromptDTO> generatePrompt(@NotNull @PathVariable Long idConversation){

        PromptDTO promptDTO = null;
        try {
            promptDTO = gptService.getPromptFromConversation(idConversation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(promptDTO);
    }
}
