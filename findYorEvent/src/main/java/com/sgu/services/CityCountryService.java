package com.sgu.services;
import com.sgu.entity.Country;
import com.sgu.entity.City;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 17.06.2017.
 */

public class CityCountryService {

    public EntityManager entityManager = Persistence.createEntityManagerFactory("cityService").createEntityManager();

    private static CityCountryService singleton;

    public static CityCountryService getInstance() {
        if (singleton == null) {
            singleton = new CityCountryService();
        }
        return singleton;
    }
    public Country addCountry(Country country){


        entityManager.getTransaction().begin();
        Country countryDB = entityManager.merge(country);
        entityManager.getTransaction().commit();
        return countryDB;

    }

    public City addCity(City city, Country country){
        entityManager.getTransaction().begin();
        city.setCountryCity(country);
        City CityDB = entityManager.merge(city);
        entityManager.getTransaction().commit();
        return CityDB;

    }


    public void deleteCountry(Country country){


        entityManager.getTransaction().begin();
        entityManager.remove(country);
        entityManager.getTransaction().commit();

    }

    public void deleteCity(City city){
        entityManager.getTransaction().begin();
        entityManager.remove(city);
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
    public List<Country> getAllCountyes(String name){
        TypedQuery<Country> query = entityManager.createQuery("SELECT c FROM Country c WHERE c.countryName LIKE :name",Country.class);
        query.setParameter("name",  "%" + name + "%" );
        return query.getResultList();
    }

    public List<City> getAllCityes(){
        TypedQuery<City> query = entityManager.createNamedQuery("City.getAll", City.class);
        return query.getResultList();
    }

    public List<City> getAllCityes(String name){
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c WHERE c.cityName LIKE :name",City.class);
        query.setParameter("name",  "%" + name + "%" );
        return query.getResultList();
    }

    public Set<City> getListOffAllCityesInCountry(Country country){

        return country.getCountryCity();
    }
}
