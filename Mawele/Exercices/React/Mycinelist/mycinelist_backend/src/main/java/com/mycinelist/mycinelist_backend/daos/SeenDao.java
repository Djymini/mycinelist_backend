package com.mycinelist.mycinelist_backend.daos;

import com.mycinelist.mycinelist_backend.entities.Favorite;
import com.mycinelist.mycinelist_backend.entities.Seen;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeenDao {
    private final JdbcTemplate jdbcTemplate;

    public SeenDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Seen> userRowMapper = (rs, _) -> new Seen(
            rs.getInt("user_id"),
            rs.getInt("movie_id"),
            rs.getString("like"),
            rs.getString("recommandation"),
            rs.getString("commentary")
    );

    public List<Seen> findByUserId(int userId) {
        String sql = "SELECT * FROM seen WHERE user_id = ?";
        return jdbcTemplate.query(sql, userRowMapper, userId);
    }

    public Seen save(Seen seen) {
        String sql = "INSERT INTO seen (user_id, movie_id, like, recommandation, commentary) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, seen.getUserId(), seen.getMovieId(), seen.getLike(), seen.getRecommendation(), seen.getCommentary());

        return seen;
    }

    private boolean seenExists(int userId, int movieId) {
        String conditionForCount = "user_id = " + userId + " and movie_id = " + movieId;
        String checkSql = "SELECT COUNT(*) FROM seen WHERE ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, conditionForCount);
        return count > 0;
    }

    public boolean delete(int userId, int movieId) {
        String conditionForDelete = "user_id = " + userId + " and movie_id = " + movieId;
        String sql = "DELETE FROM seen WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, conditionForDelete);
        return rowsAffected > 0;
    }

    public boolean deleteAllSeen(int userId) {
        String sql = "DELETE FROM seen WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId);
        return rowsAffected > 0;
    }
}
