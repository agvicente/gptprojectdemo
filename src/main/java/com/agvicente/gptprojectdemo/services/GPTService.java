package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.entities.interfaces.IChatObject;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.Arrays;

public class GPTService {
    public IChatObject getResponse (IChatObject iInputChat, OpenAiService service) {

        // OpenAiService service = new OpenAiService("key", Duration.ofSeconds(60));
        ChatMessage message = new ChatMessage(ChatMessageRole.USER.value(), "Gere uma progressão harmônica de 10 acordes baseado na escala menor melódica.");
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(message))
                .n(1)
                .stream(false)
                .build();
        ChatCompletionChoice choice = service.createChatCompletion(request).getChoices().get(0);
        System.out.println(choice.getMessage().getContent());
        return iInputChat;
    }
}
