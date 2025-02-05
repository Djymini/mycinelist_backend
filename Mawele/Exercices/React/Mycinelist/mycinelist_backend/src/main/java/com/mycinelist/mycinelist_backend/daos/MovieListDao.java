package com.mycinelist.mycinelist_backend.daos;

import com.mycinelist.mycinelist_backend.entities.Favorite;
import com.mycinelist.mycinelist_backend.entities.MovieList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieListDao {
    private final JdbcTemplate jdbcTemplate;

    public MovieListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<MovieList> userRowMapper = (rs, _) -> new MovieList(
            rs.getInt("user_id"),
            rs.getInt("id"),
            rs.getString("name")
    );

    public List<MovieList> findByUserId(int userId) {
        String sql = "SELECT * FROM list WHERE user_id = ?";
        return jdbcTemplate.query(sql, userRowMapper, userId);
    }

    public MovieList save(MovieList movieList) {
        String sql = "INSERT INTO list (user_id, id, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, movieList.getUserId(), movieList.getId(), movieList.getName());

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        int id = jdbcTemplate.queryForObject(sqlGetId, int.class);

        movieList.setId(id);
        return movieList;
    }

    private boolean movieListExists(int userId, int id) {
        String conditionForCount = "user_id = " + userId + " and id = " + id;
        String checkSql = "SELECT COUNT(*) FROM list WHERE ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, conditionForCount);
        return count > 0;
    }

    public boolean delete(int userId, int id) {
        String conditionForDelete = "user_id = " + userId + " and id = " + id;
        String sql = "DELETE FROM favorite WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, conditionForDelete);
        return rowsAffected > 0;
    }
}
