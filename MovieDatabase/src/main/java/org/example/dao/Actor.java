package org.example.dao;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long actorId;
    @Column(name = "actor_name")
    private String name;
    @Column(name = "actor_surname")
    private String surname;
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;
    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + actorId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
