package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPTService {

    @Autowired
    private Configuration config;

        public Message getAnswerFrom (List<Message> messages) {
           return ChatMessageAdapter.getAnswerFrom(config,
                    ChatMessageAdapter.getChatCompletionRequest(config, messages));
    }
}
