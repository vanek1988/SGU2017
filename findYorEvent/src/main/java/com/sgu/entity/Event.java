package com.sgu.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by admin on 16.06.2017.
 */
@Entity
@Table(name="Events")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "city_id", nullable = false)
    private City eventCity;

    @Column(name="date")
    private Date eventDate;

    @Column(name="name")
    private String eventName;

    @Column(name="description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commentEvent")
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photoEvent")
    private Set<Photo> photos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gradesEvent")
    private Set<Grades> grades;

    public Event(String eventName, String description, Date eventDate){
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "Event -" + eventName + " Description - " + description;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getDescription() {
        return description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setGrades(Set<Grades> grades) {
        this.grades = grades;
    }

    public Set<Grades> getGrades() {
        return grades;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setEventCity(City eventCity) {
        this.eventCity = eventCity;
    }

    public City getEventCity() {
        return eventCity;
    }
}
