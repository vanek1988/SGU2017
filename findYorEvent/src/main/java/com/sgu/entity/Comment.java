package com.sgu.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * Created by admin on 15.06.2017.
 */
@Entity
@Table(name="Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "event_id", nullable = false)
    private Event commentEvent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User commentUser;

    @Column(name="text_id")
    private String comment;

    @Column(name="date_id")
    private Date commentDate;

    public Comment(String comment, Date commentDate){
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setCommentEvent(Event commentEvent) {
        this.commentEvent = commentEvent;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public Event getCommentEvent() {
        return commentEvent;
    }

    public User getCommentUser() {
        return commentUser;
    }
}
