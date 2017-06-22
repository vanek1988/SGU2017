package com.sgu.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by admin on 16.06.2017.
 */
@Entity
@Table(name="Tags")
public class Tag {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String tagName;

    @ManyToMany
    @JoinTable(name="Tags_events",
            joinColumns = @JoinColumn(name="tag_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="event_id", referencedColumnName="id")
    )
    private Set<Event> events;

    public Tag(String tagName){
        this.tagName = tagName;
    }

    public Tag(){};

    @Override
    public String toString() {
        return "Tag - " + tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }


    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Event> getEvents() {
        return events;
    }

}
