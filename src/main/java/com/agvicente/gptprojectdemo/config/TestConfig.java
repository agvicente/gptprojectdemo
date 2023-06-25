package com.agvicente.gptprojectdemo.config;

import com.agvicente.gptprojectdemo.entities.Movie;
import com.agvicente.gptprojectdemo.entities.User;
import com.agvicente.gptprojectdemo.repositories.MovieRepository;
import com.agvicente.gptprojectdemo.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Arrays;

@Configuration
@Profile("test")
@Service
public class TestConfig implements CommandLineRunner {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private MovieRepository movieRepository;

//    @Value("${question.gpt}")
//    private String questionGpt;

    //    @Override
//    public void run(String... args) throws Exception {
//        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
//        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
//        userRepository.saveAll(Arrays.asList(u1, u2));
//
//        Movie m1 = new Movie("Malcolm Mcdowell", "Adrienne Corri", "Stanley Cubrick", "Drama", "Auto Generated");
//        movieRepository.save(m1);
//    }
    @Override
    public void run(String... args) throws Exception {
//        String messageChat = formatChatMessage(questionGpt);

        OpenAiService service = new OpenAiService("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", Duration.ofSeconds(60));
        ChatMessage message = new ChatMessage(ChatMessageRole.USER.value(), "Gere uma progressão de 5 acordes na escala menor natural contendo tensões e resoluções, 1 empréstimo modal da escala menor melódica, e 2 acordes invertidos");
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(message))
                .n(1)
                .stream(false)
                .build();
        ChatCompletionChoice choice = service.createChatCompletion(request).getChoices().get(0);
        System.out.println(choice.getMessage().getContent());
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
}
