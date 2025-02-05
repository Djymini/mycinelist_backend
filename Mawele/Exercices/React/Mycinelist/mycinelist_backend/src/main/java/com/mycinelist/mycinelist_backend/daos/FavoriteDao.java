package com.mycinelist.mycinelist_backend.daos;

import com.mycinelist.mycinelist_backend.entities.Favorite;
import com.mycinelist.mycinelist_backend.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteDao {
    private final JdbcTemplate jdbcTemplate;

    public FavoriteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Favorite> userRowMapper = (rs, _) -> new Favorite(
            rs.getInt("user_id"),
            rs.getInt("movie_id")
    );

    public List<Favorite> findByUserId(int userId) {
        String sql = "SELECT * FROM favorite WHERE user_id = ?";
        return jdbcTemplate.query(sql, userRowMapper, userId);
    }

    public Favorite save(Favorite favorite) {
        String sql = "INSERT INTO favorite (user_id, movie_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, favorite.getUserId(), favorite.getMovieId());

        return favorite;
    }

    private boolean favoriteExists(int userId, int movieId) {
        String conditionForCount = "user_id = " + userId + " and movie_id = " + movieId;
        String checkSql = "SELECT COUNT(*) FROM favorite WHERE ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, conditionForCount);
        return count > 0;
    }

    public boolean delete(int userId, int movieId) {
        String conditionForDelete = "user_id = " + userId + " and movie_id = " + movieId;
        String sql = "DELETE FROM favorite WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, conditionForDelete);
        return rowsAffected > 0;
    }

    public boolean deleteAllFavorite(int userId) {
        String sql = "DELETE FROM favorite WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId);
        return rowsAffected > 0;
    }
}
