package ru.tagiev.springtest.daoimpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.tagiev.springtest.dao.UserDAO;
import ru.tagiev.springtest.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class UserDAOTemp implements UserDAO {
    private static int USERS_COUNT;
    private final List<User> users;
    private final List<Integer> friends_id;
    private final List<Integer> friends_connections;

    {
        users = new ArrayList<>();
        friends_id = new ArrayList<>();
        friends_connections = new ArrayList<>();

        users.add(new User(++USERS_COUNT,"Bob", "Moscow", "15 Jule", "Vladikavkaz"));
        users.add(new User(++USERS_COUNT,"Tom", "Vladikavkaz", "29 Jule", "Moscow"));
        users.add(new User(++USERS_COUNT,"John", "Moscow", null, null));
        users.add(new User(++USERS_COUNT,"Jimmy", "Vladikavkaz", "2 Jule", "Moscow"));
        users.add(new User(++USERS_COUNT,"Mike", "Vladikavkaz", "5 Jule", "Moscow"));

        friends_id.add(1); friends_connections.add(3);
        friends_id.add(1); friends_connections.add(4);
        friends_id.add(2); friends_connections.add(3);
        friends_id.add(2); friends_connections.add(4);
        friends_id.add(3); friends_connections.add(1);
        friends_id.add(3); friends_connections.add(2);
        friends_id.add(3); friends_connections.add(4);
        friends_id.add(4); friends_connections.add(1);
        friends_id.add(4); friends_connections.add(2);
        friends_id.add(4); friends_connections.add(3);

    }

    public List<User> showFriends(int id) {

        List<User> friends = new ArrayList<>();

        for (int i = 0; i<friends_connections.size(); i++){
            int idd = i;
            if ( friends_id.get(i)==id )
                friends.add(users.stream()
                        .filter(user -> user.getId() == friends_connections.get(idd))
                        .findAny().orElse(null));
        }

        return friends;

    }

    public User show(int id){

        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);

    }

    public void deleteFriend(int id, int friend) {

        for (int i = 0; i < friends_connections.size(); i++){
            if (friends_id.get(i) == id && friends_connections.get(i)==friend){
                friends_connections.remove(i);
                friends_id.remove(i);
            }
        }
        for (int i = 0; i < friends_connections.size(); i++){
            if (friends_id.get(i) == friend && friends_connections.get(i)==id){
                friends_connections.remove(i);
                friends_id.remove(i);
            }
        }

    }

    public void addFriend (int id, int friendId){

        if ((users.stream().filter(user -> user
                .getId() == friendId)
                .findAny()
                .orElse(null)!=null)&&
                (!friendsCheck(id,friendId))) {
            friends_id.add(id); friends_connections.add(friendId);
            friends_id.add(friendId); friends_connections.add(id);
        }

    }

    public void update (int id, User updateUser){

        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updateUser.getName());
        userToBeUpdated.setLocation(updateUser.getLocation());
        userToBeUpdated.setMoveDate(updateUser.getMoveDate());
        userToBeUpdated.setMovePoint(updateUser.getMovePoint());

    }
    private boolean friendsCheck (int id, int friendId){

        for (int i = 0; i < friends_id.size(); i++){
            if ((friends_id.get(i)==id)&&(friends_connections.get(i)==friendId)) return true;
        }
        return false;
    }

}
