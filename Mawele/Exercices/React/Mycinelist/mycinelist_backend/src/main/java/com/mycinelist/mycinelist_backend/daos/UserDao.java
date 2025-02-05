package com.mycinelist.mycinelist_backend.daos;

import com.mycinelist.mycinelist_backend.entities.User;
import com.mycinelist.mycinelist_backend.exceptions.RessourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, _) -> new User(
            rs.getInt("id"),
            rs.getString("mail"),
            rs.getString("password"),
            rs.getString("role")
    );

    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(sql, userRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur avec l'ID : " + id + " n'existe pas"));
    }

    public User findByMail(String mail) {
        String sql = "SELECT * FROM user WHERE mail = ?";
        return jdbcTemplate.query(sql, userRowMapper, mail)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur avec l'le mail : " + mail + " n'existe pas"));
    }


    public boolean save(User user) {
        String sql = "INSERT INTO `user` (email, password, role) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getMail(), user.getPassword(), user.getRole());
        return rowsAffected > 0;
    }

    public User update(int id, User user) {
        if (!userExists(id)) {
            throw new RessourceNotFoundException("L'utilisateur avec l'ID : " + id + " n'existe pas");
        }

        String sql = "UPDATE user SET mail = ?, password = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, user.getMail(), user.getPassword(), id);

        if (rowsAffected <= 0) {
            throw new RessourceNotFoundException("Échec de la mise à jour de l'utilisateur avec l'ID : " + id);
        }

        return this.findById(id);
    }

    // méthode utilitaire à mettre en bas du fichier
    private boolean userExists(int id) {
        String checkSql = "SELECT COUNT(*) FROM user WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

}
