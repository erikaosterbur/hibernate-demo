package perscholas;
import perscholas.database.dao.ActorDAO;
import perscholas.database.dao.MovieActorsDAO;
import perscholas.database.dao.MovieDAO;
import perscholas.database.entity.Actor;
import perscholas.database.entity.Movie;
import perscholas.database.entity.MovieActors;

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
    private MovieActorsDAO movieActorsDAO = new MovieActorsDAO();

    public void work(){
//        read();
//        create();
//        deleteWithEntityManager();
//        deleteWithQuery();
//        update();
//        findByFirstNameAndLastName();
//        printMoviesAndActors();
//        addActorToMovie();
//        queryOneToMany();
        findByActorId();
    }

    private void findByActorId(){
        List<MovieActors> movieActors = movieActorsDAO.findByActorId(11);

        for(MovieActors ma : movieActors){
            String firstName = ma.getActor().getFirstName();
            String lastName = ma.getActor().getLastName();
            String movieTitle = ma.getMovie().getTitle();
            String characterName = ma.getCharacterName();
            System.out.printf("%s %s plays the character %s in the movie %s\n", firstName, lastName, characterName, movieTitle);
        }
    }

    private void queryOneToMany(){

        List<MovieActors> movieActors1 = movieActorsDAO.findByMovieId(1);
        for(MovieActors ma : movieActors1){
            String movieTitle = ma.getMovie().getTitle();
            String firstName = ma.getActor().getFirstName();
            String lastName = ma.getActor().getLastName();
            String characterName = ma.getCharacterName();
            System.out.printf("In %s, %s %s plays the character %s.\n", movieTitle, firstName, lastName, characterName);
        }

    }

    private void addActorToMovie(){

        Movie movie = movieDAO.findById(1);

        Actor actor = actorDAO.findById(12);

        MovieActors movieActors = new MovieActors();
        movieActors.setMovie(movie);
        movieActors.setActor(actor);
        movieActors.setCharacterName("Luke Skywalker");

        System.out.println("Before save: " + movieActors);

        movieActorsDAO.save(movieActors);

        System.out.println("After save: " + movieActors);


    }

//    private void printMoviesAndActors(){
//        Movie movie = movieDAO.findById(1);
//        for (Actor actor : movie.getActors() ) {
//            System.out.println(actor);
//        }
//
//        Actor actor = actorDAO.findById(11);
//        for(Movie movie2 : actor.getMovies()){
//            System.out.println(movie2);
//        }
//    }

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
