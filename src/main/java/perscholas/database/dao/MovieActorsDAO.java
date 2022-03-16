package perscholas.database.dao;

import perscholas.database.entity.Actor;
import perscholas.database.entity.MovieActors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieActorsDAO {

    private static final String PERSISTENCE_UNIT_NAME = "moviesdb";

    private EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public MovieActors save(MovieActors movieActors) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        em.persist(movieActors);

        em.getTransaction().commit();
        em.clear();

        return movieActors;
    }

    public MovieActors findById(Integer id){
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT ma FROM MovieActors ma WHERE ma.id = :movieActorId";
        TypedQuery<MovieActors> query = em.createQuery(sql, MovieActors.class);
        query.setParameter("movieActorId", id);

        try{
            return query.getSingleResult();
        } catch ( Exception e ){
            return null;
        }

    }

    public List<MovieActors> findByMovieId(Integer id){
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT ma FROM MovieActors ma WHERE movie_id = :movieId";
        TypedQuery<MovieActors> query = em.createQuery(sql, MovieActors.class);
        query.setParameter("movieId", id);
        List<MovieActors> result = query.getResultList();

        return result;
    }

    public List<MovieActors> findByActorId(Integer id){
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT ma FROM MovieActors ma WHERE actor_id = :actorId";
        TypedQuery<MovieActors> query = em.createQuery(sql, MovieActors.class);
        query.setParameter("actorId", id);
        List<MovieActors> result = query.getResultList();

        return result;
    }

}
