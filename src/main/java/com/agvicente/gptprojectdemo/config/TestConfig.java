package com.agvicente.gptprojectdemo.config;

import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.entities.User;
import com.agvicente.gptprojectdemo.repositories.ConversationRepository;
import com.agvicente.gptprojectdemo.repositories.MessageRepository;
import com.agvicente.gptprojectdemo.repositories.UserRepository;
import com.agvicente.gptprojectdemo.services.ChatMessageService;
import com.agvicente.gptprojectdemo.services.GPTService;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
@Profile("test")
@Service
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private GPTService gptService;

    @Autowired
    ChatMessageService chatMessageService;

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);

        //Inicia conversa com usuario de teste
        User u1 = new User(null, "User", "user@gmail.com");
        userRepository.save(u1);
        Conversation c = new Conversation(null, new ArrayList<>(), u1);
        conversationRepository.save(c);

        //Iniciando interação com chat
        Message configMessage = new Message();
        configMessage.setRole(ChatMessageRole.SYSTEM.value());
        configMessage.setContent("Voce é um assistente de loja virtual de roupas, todas as suas respostas devem ter no máximo 500 caracteres.");
        configMessage.setConversation(c);
        configMessage.setSender(u1);
        messageRepository.save(configMessage);

        ChatMessage _configMessage = chatMessageService.messageToChatMessage(configMessage);


        Message message1 = new Message();
        message1.setRole(ChatMessageRole.USER.value());
        message1.setContent("Inicie o atendimento do cliente com 2 frases.");
        message1.setConversation(c);
        message1.setSender(u1);
        messageRepository.save(message1);

        ChatMessage _message1 = chatMessageService.messageToChatMessage(message1);

        //Caso o chat retorne corretamente salvar, se não tratar exceção (tentar repetir ou outra coisa)
        ChatMessage next = gptService.getAnswerFrom(Arrays.asList(_configMessage, _message1));

        //Salvando retorno do Chat
        Message answer1 = new Message();
        answer1.setRole(next.getRole());
        answer1.setContent(next.getContent());
        answer1.setConversation(c);
        answer1.setSender(u1);
        messageRepository.save(answer1);
        System.out.println(answer1.getContent()); //mostrando resposta do chat na tela

        for (int i=0; i<10; i++){
            Conversation conversation = conversationRepository.findById(c.getId()).orElseThrow();
            List<Message> messages = new ArrayList<>(conversation.getMessages());
            System.out.println("Pergunta: ");
            String _msg = sc.nextLine();

            Message recMsg = new Message();
            recMsg.setRole(ChatMessageRole.USER.value());
            recMsg.setContent(_msg);
            recMsg.setConversation(c);
            recMsg.setSender(u1);
            messages.add(messageRepository.save(recMsg));

            ChatMessage _recMsg = chatMessageService.messageToChatMessage(recMsg);
            List<ChatMessage> chatMessages = messages.stream()
                    .map(m -> chatMessageService.messageToChatMessage(m))
                    .collect(Collectors.toList());

            ChatMessage recNext = gptService.getAnswerFrom(chatMessages);

            Message recAanswer = new Message();
            recAanswer.setRole(recNext.getRole());
            recAanswer.setContent(recNext.getContent());
            recAanswer.setConversation(c);
            recAanswer.setSender(u1);
            messageRepository.save(recAanswer);
            System.out.println(recAanswer.getContent());
        }

        sc.close();
    }
//    @Override
//    public void run(String... args) throws Exception {
//        User u1 = new User(null, "Maria", "maria@gmail.com");
//        userRepository.save(u1);
//
//        Conversation c = new Conversation(null, null, u1);
//
//        conversationRepository.save(c);
//
//        Message m1 = new Message(null, "assistant", "Teste u1 123", u1, c);
//        Message m2 = new Message(null, "user", "Teste u2 456", u1, c);
//        Message m3 = new Message(null, "assistant", "Teste u1 789", u1, c);
//        Message m4 = new Message(null, "user", "Teste u2 10 11 12", u1, c);
//        messageRepository.saveAll(Arrays.asList(m1, m2, m3, m4));
//
//
//    }
}