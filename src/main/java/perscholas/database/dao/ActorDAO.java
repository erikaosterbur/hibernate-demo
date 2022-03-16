package perscholas.database.dao;

import perscholas.database.entity.Actor;

import javax.persistence.*;
import java.util.List;

public class ActorDAO {

    private static final String PERSISTENCE_UNIT_NAME = "moviesdb";

    private EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public Actor findById(Integer id){
        EntityManager em = emFactoryObj.createEntityManager();

        //Actor refers to the entity object Actor - Actor.java
        //Actor.java refers to the actors table in SQL
        String sql = "SELECT a FROM Actor a WHERE a.id = :actorId";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);
        query.setParameter("actorId", id);

        try{
            return query.getSingleResult();
        } catch ( Exception e ){
            return null;
        }

    }

    public List<Actor> findByFirstName(String firstName){
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT a FROM Actor a WHERE a.firstName = :firstName";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);
        query.setParameter("firstName", firstName);

        List<Actor> result = query.getResultList();

        return result;
    }

    public List<Actor> findByLastName(String lastName){
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT a FROM Actor a WHERE a.lastName = :lastName";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);
        query.setParameter("lastName", lastName);

        List<Actor> result = query.getResultList();

        return result;
    }

    public Actor save(Actor actor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        em.persist(actor);

        em.getTransaction().commit();
        em.clear();

        return actor;
    }

    public void delete(Actor actor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        em.remove(em.contains(actor) ? actor : em.merge(actor));

        em.getTransaction().commit();
        em.clear();

    }

    public int deleteById(Integer id){
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        String sql = "DELETE FROM Actor a WHERE a.id = :actorId";
        Query query = em.createQuery(sql);
        query.setParameter("actorId", id);

        int count = query.executeUpdate();

        em.getTransaction().commit();

        return count;
    }

    public Actor update(Actor actor){
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        em.merge(actor);

        em.getTransaction().commit();
        em.clear();

        return actor;
    }

    public List<Actor> findByFirstNameAndLastName(String firstName, String lastName){
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT a FROM Actor a WHERE a.firstName = :firstName AND a.lastName = :lastName";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);
        query.setParameter("firstName", firstName).setParameter("lastName", lastName);

        List<Actor> result = query.getResultList();

        return result;
    }


}
