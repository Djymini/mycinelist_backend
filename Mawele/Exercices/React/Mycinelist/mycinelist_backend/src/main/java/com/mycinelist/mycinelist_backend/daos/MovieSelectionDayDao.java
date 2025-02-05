package com.mycinelist.mycinelist_backend.daos;

import com.mycinelist.mycinelist_backend.entities.MovieSelectionDay;
import com.mycinelist.mycinelist_backend.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieSelectionDayDao {
    private final JdbcTemplate jdbcTemplate;

    public MovieSelectionDayDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<MovieSelectionDay> userRowMapper = (rs, _) -> new MovieSelectionDay(
            rs.getInt("id"),
            rs.getInt("movie_id"),
            rs.getString("date")
    );

    public List<MovieSelectionDay> findAll() {
        String sql = "SELECT * FROM movie_selection_day";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public MovieSelectionDay findById(int id) {
        String sql = "SELECT * FROM movie_selection_day WHERE id = ?";
        return jdbcTemplate.query(sql, userRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("le film avec l'ID : " + id + " n'existe pas"));
    }

    public MovieSelectionDay update(int id, MovieSelectionDay movieSelectionDay) {
        if (!movieExists(id)) {
            throw new RuntimeException("Le film avec l'ID : " + id + " n'existe pas");
        }

        String sql = "UPDATE movie_selection_day SET movie_id = ?, date = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, movieSelectionDay.getMovieId(), movieSelectionDay.getDate(), id);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour du film avec l'ID : " + id);
        }

        return this.findById(id);
    }

    // méthode utilitaire à mettre en bas du fichier
    private boolean movieExists(int id) {
        String checkSql = "SELECT COUNT(*) FROM movie_selection_day WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
}
