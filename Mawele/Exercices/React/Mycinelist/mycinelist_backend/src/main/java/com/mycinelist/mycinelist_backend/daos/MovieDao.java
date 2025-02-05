package com.mycinelist.mycinelist_backend.daos;

import com.mycinelist.mycinelist_backend.entities.Movie;
import com.mycinelist.mycinelist_backend.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDao {
    private final JdbcTemplate jdbcTemplate;

    public MovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Movie> movieRowMapper = (rs, _) -> new Movie(
            rs.getInt("movie_id"),
            rs.getString("name"),
            rs.getInt("runtime"),
            rs.getString("release_date"),
            rs.getString("poster_path")
    );

    public List<Movie> findAll() {
        String sql = "SELECT * FROM movie";
        return jdbcTemplate.query(sql, movieRowMapper);
    }

    public Movie findById(int id) {
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        return jdbcTemplate.query(sql, movieRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ce film avec l'ID : " + id + " n'existe pas dans la base de données"));
    }

    public List<Movie> findBySearch(String search) {
        String searchPattern = "%" + search + "%";
        String sql = "SELECT * FROM movie WHERE name LIKE ?";
        return jdbcTemplate.query(sql, movieRowMapper, searchPattern);
    }

    public Movie save(Movie movie) {
        String sql = "INSERT INTO movie (movie_id, name, runtime, release_date, poster_path) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, movie.getId(), movie.getName(), movie.getRuntime(), movie.getReleaseDate(), movie.getPosterPath());
        return movie;
    }

    private boolean movieExists(int id) {
        String checkSql = "SELECT COUNT(*) FROM movie WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }

}
