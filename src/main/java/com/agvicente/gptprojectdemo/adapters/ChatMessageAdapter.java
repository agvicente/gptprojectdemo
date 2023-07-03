package com.agvicente.gptprojectdemo.adapters;

import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Message;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

public class ChatMessageAdapter {

    public static final String ROLE_USER = "user";
    public static final String ROLE_SYSTEM = "system";
    public static final String ROLE_ASSISTANT = "assistant";

    private static OpenAiService getOpenAiService(Configuration config){
        return new OpenAiService(config.getOpenAiKey(), Duration.ofSeconds(config.getOpenAiTimeout()));
    }

    public static ChatCompletionRequest getChatCompletionRequest(Configuration config, List<Message> messages){
        List<ChatMessage> _messages = messages.stream().map(m -> messageToChatMessage(m)).toList();
        return ChatCompletionRequest.builder()
                .model(config.getOpenAiModel())
                .messages(_messages)
                .n(1)
                .stream(false)
                .build();
    }

    public static Message getAnswerFrom(Configuration config, ChatCompletionRequest request){
        OpenAiService service = getOpenAiService(config);
        ChatCompletionChoice choice = service.createChatCompletion(request).getChoices().get(0);
        return chatMessageToMessage(choice.getMessage());
    }

    private static ChatMessage messageToChatMessage(Message message){
        ChatMessage msg = new ChatMessage();
        msg.setRole(message.getRole());
        msg.setContent(message.getContent());
        return msg;
    }

    private static Message chatMessageToMessage(ChatMessage chatMessage){
        Message msg = new Message();
        msg.setRole(chatMessage.getRole());
        msg.setContent(chatMessage.getContent());
        return msg;
    }
}
