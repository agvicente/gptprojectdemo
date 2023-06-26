package com.agvicente.gptprojectdemo.entities;

import com.theokanning.openai.completion.chat.ChatMessage;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TB_MESSAGE")
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "CONTENT", length = 500)
    private String content;

    @Column(name = "DATE")
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "ID_USER")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "ID_CONVERSATION")
    private Conversation conversation;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Message() {}

    public Message(Long id, String role, String content, User sender, Conversation conversation) {
        this.id = id;
        this.role = role;
        this.content = content;
        this.sender = sender;
        this.conversation = conversation;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
