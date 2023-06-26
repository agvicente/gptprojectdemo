package com.agvicente.gptprojectdemo.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_CONVERSATION")
public class Conversation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "conversation")
    private Collection<Message> messages;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    public Conversation() {}

    public Conversation(Long id, Collection<Message> messages, User user) {
        this.id = id;
        this.messages = messages;
        this.user = user;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
