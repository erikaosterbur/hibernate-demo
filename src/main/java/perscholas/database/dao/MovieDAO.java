package perscholas.database.dao;

import perscholas.database.entity.Actor;
import perscholas.database.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class MovieDAO {
    private static final String PERSISTENCE_UNIT_NAME = "moviesdb";

    private EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public Movie findById(Integer id){
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT m FROM Movie m WHERE m.id = :movieId";
        TypedQuery<Movie> query = em.createQuery(sql, Movie.class);
        query.setParameter("movieId", id);

        try{
            return query.getSingleResult();
        } catch ( Exception e ){
            return null;
        }

    }

    public Movie update(Movie movie){
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        em.merge(movie);

        em.getTransaction().commit();
        em.clear();

        return movie;
    }

    public Movie save(Movie movie) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        em.persist(movie);

        em.getTransaction().commit();
        em.clear();

        return movie;
    }

}
