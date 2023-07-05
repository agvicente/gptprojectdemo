package com.agvicente.gptprojectdemo.repositories;

import com.agvicente.gptprojectdemo.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
