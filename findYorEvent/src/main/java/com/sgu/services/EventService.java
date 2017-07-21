package com.sgu.services;

import com.sgu.entity.City;
import com.sgu.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by admin on 21.07.2017.
 */
public class EventService {
    public EntityManager entityManager = Persistence.createEntityManagerFactory("cityService").createEntityManager();

    private static EventService singleton;

    public static EventService getInstance(){
        if (singleton == null){
            singleton = new EventService();
        }
        return singleton;
    }

    public void addEvent(Event event) {
        entityManager.getTransaction().begin();
        entityManager.merge(event);
        entityManager.getTransaction().commit();
    }

    public void deleteEvent(Event event){
        entityManager.getTransaction().begin();
        entityManager.remove(event);
        entityManager.getTransaction().commit();
    }

    public List<Event> getAllEvents(String name){
        TypedQuery<Event> query = entityManager.createQuery("SELECT c FROM Event c WHERE c.eventName LIKE :name",Event.class);
        query.setParameter("name",  "%" + name + "%" );
        return query.getResultList();
    }
    public List<Event> getAllEvents(){
        TypedQuery<Event> query = entityManager.createQuery("SELECT c FROM Event c",Event.class);
        return query.getResultList();
    }
    public List<Event> getAllEventsFromCity(City city, String event){
        TypedQuery<Event> query = entityManager.createQuery("SELECT c FROM Event c WHERE c.eventName LIKE :name and c.eventCity = :city",Event.class);
        query.setParameter("name",  "%" + event + "%" );
        query.setParameter("city", city);
        return query.getResultList();
    }
    public List<Event> getAllEventsFromCity(City city){
        TypedQuery<Event> query = entityManager.createQuery("SELECT c FROM Event c where c.eventCity = :city",Event.class);
        query.setParameter("city", city);
        return query.getResultList();
    }


}
