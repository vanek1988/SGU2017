package com.sgu.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * Created by admin on 16.06.2017.
 */
@Entity
@Table(name="Grades")
public class Grades {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "event_id", nullable = false)
    private Event gradesEvent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User gradesUser;

    @Column(name="date")
    private Date gradeDate;

    @Column(name="grade")
    private int grade;

    public Grades(int grade, Date gradeDate){
        this.grade = grade;
        this.gradeDate = gradeDate;
    }
    public Grades(){};

    @Override
    public String toString() {
        return "Grade - " + grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    public Date getGradeDate() {
        return gradeDate;
    }

    public int getGrade() {
        return grade;
    }

    public void setGradesEvent(Event gradesEvent) {
        this.gradesEvent = gradesEvent;
    }

    public void setGradesUser(User gradesUser) {
        this.gradesUser = gradesUser;
    }

    public Event getGradesEvent() {
        return gradesEvent;
    }

    public User getGradesUser() {
        return gradesUser;
    }

}
