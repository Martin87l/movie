package org.example.dao;

import jakarta.persistence.*;
import lombok.Cleanup;

import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;
    @Column(name = "movie_title")
    private String title;
    @Column(name = "year_of_release")
    private Integer yearOfRelease;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "actors_to_movies", joinColumns = {
            @JoinColumn(name = "movie", referencedColumnName = "movie_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "actor", referencedColumnName = "actor_id")
    })
    private List<Actor> actors;

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", genre=" + genre +
                ", actors=" + actors +
                '}';
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
