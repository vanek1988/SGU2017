package com.sgu.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * Created by admin on 15.06.2017.
 */
@Entity
@Table(name="Messages")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_sender_id", nullable = false)
    private User senderUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_receiver_id", nullable = false)
    private User receiverUser;

    @Column(name="text")
    private String message;

    @Column(name="date")
    private Date messageDate;

    public Message(String message, Date messageDate){
        this.message = message;
        this. messageDate = messageDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public String getMessage() {
        return message;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public User getSenderUser() {
        return senderUser;
    }
}
