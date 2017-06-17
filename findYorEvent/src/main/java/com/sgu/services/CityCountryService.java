package com.sgu.services;
import com.sgu.entity.Country;
import com.sgu.entity.City;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by admin on 17.06.2017.
 */

public class CityCountryService {

    public EntityManager entityManager = Persistence.createEntityManagerFactory("Manager").createEntityManager();

    public Country addCountry(Country country){
        entityManager.getTransaction().begin();
        Country countryDB = entityManager.merge(country);
        entityManager.getTransaction().commit();
        return countryDB;

    }

    public City addCity(City city){
        entityManager.getTransaction().begin();
        City CityDB = entityManager.merge(city);
        entityManager.getTransaction().commit();
        return CityDB;

    }

    public Country getCountry(String countryName){
        return entityManager.find(Country.class, countryName);
    }

    public City getCity(String cityName){
        return entityManager.find(City.class, cityName);
    }

    public void deleteCountry(String countryName){
        entityManager.getTransaction().begin();
        entityManager.remove(getCountry(countryName));
        entityManager.getTransaction().commit();
    }

    public void deleteCity(String cityName){
        entityManager.getTransaction().begin();
        entityManager.remove(getCity(cityName));
        entityManager.getTransaction().commit();
    }

    public void updateCountry(Country country){
        entityManager.getTransaction().begin();
        entityManager.merge(country);
        entityManager.getTransaction().commit();
    }

    public void updateCity(City city){
        entityManager.getTransaction().begin();
        entityManager.merge(city);
        entityManager.getTransaction().commit();
    }

    public List<Country> getAllCountyes(){
        TypedQuery<Country> query = entityManager.createNamedQuery("Country.getAll", Country.class);
        return query.getResultList();
    }

    public List<City> getAllCityes(){
        TypedQuery<City> query = entityManager.createNamedQuery("City.getAll", City.class);
        return query.getResultList();
    }
}
