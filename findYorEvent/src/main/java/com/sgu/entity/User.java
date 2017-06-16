package com.sgu.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
/**
 * Created by admin on 15.06.2017.
 */
@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "city_id", nullable = false)
    private City userCity;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="birthday")
    private Date birthday;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "senderUser")
    private Set<Message> senderMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiverUser")
    private Set<Message> receiverMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photoUser")
    private Set<Photo> photos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commentUser")
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gradesUser")
    private Set<Grades> grades;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Event> events;

    public User (String login,String password, String name, String surname, Date birthday){
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    public void setReceiverMessages(Set<Message> receiverMessages) {
        this.receiverMessages = receiverMessages;
    }

    public void setSenderMessages(Set<Message> senderMessages) {
        this.senderMessages = senderMessages;
    }

    public Set<Message> getReceiverMessages() {
        return receiverMessages;
    }

    public Set<Message> getSenderMessages() {
        return senderMessages;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Grades> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grades> grades) {
        this.grades = grades;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setUserCity(City userCity) {
        this.userCity = userCity;
    }

    public City getUserCity() {
        return userCity;
    }
}

