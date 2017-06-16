package com.sgu.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * Created by admin on 15.06.2017.
 */
@Entity
@Table(name="Photos")
public class Photo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "event_id", nullable = false)
    private Event photoEvent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User photoUser;


    @Column(name="date")
    private Date photoDate;

    @Column(name="photo_path")
    private String photoPath;

    public Photo( String photoPath, Date photoDate){
        this.photoDate = photoDate;
        this.photoPath = photoPath;
    }


    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Date getPhotoDate() {
        return photoDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setUser(User photoUser) {
        this.photoUser = photoUser;
    }

    public User getUser() {
        return photoUser;
    }

    public void setPhotoEvent(Event photoEvent) {
        this.photoEvent = photoEvent;
    }

    public void setPhotoUser(User photoUser) {
        this.photoUser = photoUser;
    }

    public Event getPhotoEvent() {
        return photoEvent;
    }

    public User getPhotoUser() {
        return photoUser;
    }
}
