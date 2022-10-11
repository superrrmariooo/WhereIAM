package ru.tagiev.springtest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tagiev.springtest.models.User;

import java.util.List;

public interface UserDAO {

    public List<User> showFriends(int id);
    public User show(int id);
    public void deleteFriend(int id, int friend);
    public void addFriend (int id, int friendId);
    public void update (int id, User updateUser);

}
