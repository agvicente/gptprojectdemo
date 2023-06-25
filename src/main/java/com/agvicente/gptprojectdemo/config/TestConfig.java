package com.agvicente.gptprojectdemo.config;

import com.agvicente.gptprojectdemo.entities.User;
import com.agvicente.gptprojectdemo.repositories.UserRepository;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@Profile("test")
@Service
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria", "maria@gmail.com");
        User u2 = new User(null, "Alex", "alex@gmail.com");
        userRepository.saveAll(Arrays.asList(u1, u2));
    }
//    @Override
//    public void run(String... args) throws Exception {
//        String messageChat = formatChatMessage(questionGpt);

//        OpenAiService service = new OpenAiService("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", Duration.ofSeconds(60));
//        ChatMessage message = new ChatMessage(ChatMessageRole.USER.value(), "Gere uma progressão de 5 acordes na escala menor natural contendo tensões e resoluções, 1 empréstimo modal da escala menor melódica, e 2 acordes invertidos");
//        ChatCompletionRequest request = ChatCompletionRequest.builder()
//                .model("gpt-3.5-turbo")
//                .messages(Arrays.asList(message))
//                .n(1)
//                .stream(false)
//                .build();
//        ChatCompletionChoice choice = service.createChatCompletion(request).getChoices().get(0);
//        System.out.println(choice.getMessage().getContent());
    }

//    private String formatChatMessage(String message) throws JsonProcessingException {
//        ObjectMapper obj = new ObjectMapper();
//
//        Movie m1 = new Movie();
//        String jsonMovie = obj.writeValueAsString(m1);
//        Object[] args = {Movie.class.getSimpleName(), jsonMovie};
//        MessageFormat _message = new MessageFormat(message);
//        String formatedMessage = _message.format(args);
//        System.out.println(formatedMessage);
//        return formatedMessage;
//    }
