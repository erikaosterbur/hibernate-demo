package perscholas;
import perscholas.database.dao.ActorDAO;
import perscholas.database.dao.MovieDAO;
import perscholas.database.entity.Actor;
import perscholas.database.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.StringJoiner;

public class HibernateDemo {

//    private static final String PERSISTENCE_UNIT_NAME = "moviesdb";
//
//    private static EntityManagerFactory emFactoryObj =
//            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    private ActorDAO actorDAO = new ActorDAO();
    private MovieDAO movieDAO = new MovieDAO();

    public void work(){
//        read();
//        create();
//        deleteWithEntityManager();
//        deleteWithQuery();
//        update();
//        findByFirstNameAndLastName();
//        printMoviesAndActors();
        addActorToMovie();
    }

    private void addActorToMovie(){

        Movie movie = movieDAO.findById(2);

        Actor actor = actorDAO.findById(1);
        actor.getMovies().add(movie);
        actorDAO.update(actor);

        Actor actor2 = actorDAO.findById(3);
        actor2.getMovies().add(movie);
        actorDAO.update(actor2);

        movie.getActors().add(actor);
        movie.getActors().add(actor2);

        movieDAO.update(movie);
    }

    private void printMoviesAndActors(){
        Movie movie = movieDAO.findById(1);
        for (Actor actor : movie.getActors() ) {
            System.out.println(actor);
        }

        Actor actor = actorDAO.findById(11);
        for(Movie movie2 : actor.getMovies()){
            System.out.println(movie2);
        }


    }

    private void findByFirstNameAndLastName(){
        List<Actor> actors = actorDAO.findByFirstNameAndLastName("Mark", "Ruffalo");
        if(actors.isEmpty()){
            System.out.println("No actor found with that first name");
        } else{
            for ( Actor a : actors ) {
                System.out.println("Find by firstName: " + a);
            }
        }
    }

    private void update(){
        Actor actor = actorDAO.findById(5);

        System.out.println("Before update: " + actor);

        actor.setFirstName("Chadwick");
        actor.setLastName("Boseman");

        actorDAO.update(actor);

        System.out.println("After update: " + actor);
    }

    private void deleteWithQuery(){
        actorDAO.deleteById(8);
    }

    private void deleteWithEntityManager(){
        Actor actor = actorDAO.findById(10);

        actorDAO.delete(actor);
    }

    private void create(){
        Actor actor = new Actor();

        actor.setFirstName("Harrison");
        actor.setLastName("Ford");

        System.out.println("Before save: " + actor);

        actorDAO.save(actor);

        System.out.println("After save: " + actor);
    }

    private void read(){
        int actorId = 10;
        Actor actor = actorDAO.findById(actorId);
        if(actor == null){
            System.out.println("Unable to find actor by id");
        } else {
            System.out.println(actor);
        }

        List<Actor> actors = actorDAO.findByFirstName("Mark");
        if(actors.isEmpty()){
            System.out.println("No actor found with that first name");
        } else{
            for ( Actor a : actors ) {
                System.out.println("Find by firstName: " + a);
            }
        }

        List<Actor> actors2 = actorDAO.findByLastName("Smith");
        if(actors2.isEmpty()){
            System.out.println("No actor found with that last name");
        } else {
            for ( Actor a : actors2 ) {
                System.out.println("Find by lastName: " + a);
            }
        }
    }

    public static void main(String[] args) {

        new HibernateDemo().work();

    }
}
