package com.sgu.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by admin on 16.06.2017.
 */
@Entity
@Table(name="Cities")
@NamedQuery(name = "City.getAll", query = "SELECT c from City c")
public class City {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "country_id", nullable = false)
    private Country countryCity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventCity")
    private Set<Event> events;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userCity")
    private Set<User> users;

    @Column(name="name")
    private String cityName;

    public City(String cityName){
        this.cityName = cityName;
    }

    public City(){};

    @Override
    public String toString() {
        return   cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCountryCity(Country countryCity) {
        this.countryCity = countryCity;
    }

    public Country getCountryCity() {
        return countryCity;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public Set<User> getUsers() {
        return users;
    }
}
