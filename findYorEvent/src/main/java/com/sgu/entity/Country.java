package com.sgu.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by admin on 16.06.2017.
 */
@Entity
@Table(name="Countries")
public class Country {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String countryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commentEvent")
    private Set<City> countryCity;

    public Country(String countryName){
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Coutry - " + countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryCity(Set<City> countryCity) {
        this.countryCity = countryCity;
    }

    public Set<City> getCountryCity() {
        return countryCity;
    }
}
