package com.agvicente.gptprojectdemo.config;

import com.agvicente.gptprojectdemo.model.enums.InteractionTypeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
@Profile("test")
@Service
public class Configuration implements CommandLineRunner {

    @Value("${OPENAI_KEY}")
    private String openAiKey;

    @Value(value = "${OPENAI_MODEL}")
    private String openAiModel;

    @Value(value = "${OPENAI_TIMEOUT}")
    private int openAiTimeout;

    @Value(value = "${chat.message.config.initconversation}")
    private String chatMessageInitConversation;

    @Value(value = "${chat.message.config.t-shirt}")
    private String chatMessageTShirt;

    @Value(value = "${chat.message.config.pants}")
    private String chatMessagePants;

    @Value(value = "${chat.message.config.shoes}")
    private String chatMessageShoes;

    @Value(value = "${chat.message.config.measures}")
    private String chatMessageMeasures;

    @Value(value = "${chat.message.config.style}")
    private String chatMessageStyle;

    @Value(value = "${chat.message.config.feedback}")
    private String chatMessageFeedback;

    private Map<InteractionTypeEnum, String> configMessages;

    public String getOpenAiKey() {
        return openAiKey;
    }

    public String getOpenAiModel() {
        return openAiModel;
    }

    public int getOpenAiTimeout() {
        return openAiTimeout;
    }

    public Map<InteractionTypeEnum, String> getConfigMessages() {
        return configMessages;
    }

    @Override
    public void run(String... args) throws Exception {
        configMessages = defaultMessagesConfiguration(new HashMap<>());
    }

    private Map<InteractionTypeEnum, String> defaultMessagesConfiguration(Map<InteractionTypeEnum, String> configMessages){
        configMessages.put(InteractionTypeEnum.INIT_CONVERSATION, chatMessageInitConversation);
        configMessages.put(InteractionTypeEnum.T_SHIRT, chatMessageTShirt);
        configMessages.put(InteractionTypeEnum.PANTS, chatMessagePants);
        configMessages.put(InteractionTypeEnum.SHOES, chatMessageShoes);
        configMessages.put(InteractionTypeEnum.MEASURES,chatMessageMeasures);
        configMessages.put(InteractionTypeEnum.STYLE, chatMessageStyle);
        configMessages.put(InteractionTypeEnum.FEEDBACK, chatMessageFeedback);
        return Collections.unmodifiableMap(configMessages);
    }
}