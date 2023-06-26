package com.agvicente.gptprojectdemo.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "TB_USER")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "sender")
    private Collection<Message> messages;

    @OneToMany(mappedBy = "user")
    private Collection<Conversation> conversations;

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public Collection<Conversation> getConversations() {
        return conversations;
    }
}
