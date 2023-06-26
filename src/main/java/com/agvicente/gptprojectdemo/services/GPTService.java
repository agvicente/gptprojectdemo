package com.agvicente.gptprojectdemo.services;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class GPTService {

        public ChatMessage getAnswerFrom (List<ChatMessage> messages) {

        OpenAiService service = new OpenAiService("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", Duration.ofSeconds(60));
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .stream(false)
                .build();
        ChatCompletionChoice choice = service.createChatCompletion(request).getChoices().get(0);
        return choice.getMessage();
    }
}
