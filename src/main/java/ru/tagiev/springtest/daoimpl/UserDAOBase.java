package ru.tagiev.springtest.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.tagiev.springtest.dao.UserDAO;
import ru.tagiev.springtest.models.User;

import java.util.List;


@Component
public class UserDAOBase implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAOBase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> showFriends(int id) {
        return null;
    }

    @Override
    public User show(int id) {
        return jdbcTemplate.query("SELECT * FROM user WHERE id=?",
                new Object[]{id}, new RowMapperResultSetExtractor<>());
    }

    @Override
    public void deleteFriend(int id, int friend) {

    }

    @Override
    public void addFriend(int id, int friendId) {

    }

    @Override
    public void update(int id, User updateUser) {

    }
}
