package com.mycinelist.mycinelist_backend.daos;

import com.mycinelist.mycinelist_backend.entities.Favorite;
import com.mycinelist.mycinelist_backend.entities.ItemMovieList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemMovieListDao {
    private final JdbcTemplate jdbcTemplate;

    public ItemMovieListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ItemMovieList> userRowMapper = (rs, _) -> new ItemMovieList(
            rs.getInt("list_id"),
            rs.getInt("movie_id")
    );

    public List<ItemMovieList> findByUserId(int listId) {
        String sql = "SELECT * FROM movie_list WHERE list_id = ?";
        return jdbcTemplate.query(sql, userRowMapper, listId);
    }

    public ItemMovieList save(ItemMovieList itemMovieList) {
        String sql = "INSERT INTO movie_list (list_id, movie_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, itemMovieList.getListId(), itemMovieList.getMovieId());

        return itemMovieList;
    }

    private boolean movieExists(int listId, int movieId) {
        String conditionForCount = "list_id = " + listId + " and movie_id = " + movieId;
        String checkSql = "SELECT COUNT(*) FROM movie_list WHERE ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, conditionForCount);
        return count > 0;
    }

    public boolean delete(int listId, int movieId) {
        String conditionForDelete = "list_id = " + listId + " and movie_id = " + movieId;
        String sql = "DELETE FROM favorite WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, conditionForDelete);
        return rowsAffected > 0;
    }
}
