package perscholas.database.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "movie_actors",
//            joinColumns = {
//                    @JoinColumn(name = "actor_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "movie_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)})
//    private Set<Movie> movies = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
    private Set<MovieActors> movieActors;

}
