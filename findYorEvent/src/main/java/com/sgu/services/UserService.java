package com.sgu.services;

import com.sgu.entity.City;
import com.sgu.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Created by admin on 21.07.2017.
 */
public class UserService {
    public EntityManager entityManager = Persistence.createEntityManagerFactory("cityService").createEntityManager();

    private static UserService singleton;

    public static UserService getInstance(){
        if (singleton == null){
            singleton = new UserService();
        }
        return singleton;
    }

    public void addUser(User user){
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public void deleteUser(User user){
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
    public boolean checkAvalibleLogin(String login) {
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT c FROM User c WHERE c.login = :name", User.class);
            query.setParameter("name", login);
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;

        }
    }
    public User getUser (String login){
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT c FROM User c WHERE c.login = :name", User.class);
            query.setParameter("name", login);
            query.getSingleResult();
            return  query.getSingleResult();
        } catch (NoResultException e) {
            return  new User();

        }

    }

}
