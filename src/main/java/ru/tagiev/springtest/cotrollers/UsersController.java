package ru.tagiev.springtest.cotrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tagiev.springtest.dao.UserDAO;
import ru.tagiev.springtest.models.User;

@Controller
@RequestMapping("/user")
public class UsersController {


    private final UserDAO userDAO;
    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}/friends")
    public String showFriends(@PathVariable("id") int id, Model model) {
        model.addAttribute("friends", userDAO.showFriends(id));
        model.addAttribute("myId",id);
        return "users/show_friends";
    }

    @GetMapping("/{id}/friends/{id2}")
    public String showFriend(@PathVariable("id") int id, @PathVariable("id2") int id2, Model model) {
        model.addAttribute("friend",userDAO.show(id2));
        model.addAttribute("myId",id);
        return "/users/show_friend";
    }

    @DeleteMapping("/{id}/friends/{id2}")
    public String deleteFriend(@PathVariable("id") int id, @PathVariable("id2") int id2) {
        userDAO.deleteFriend(id,id2);
        return "redirect:/user/{id}/friends";
    }

    @GetMapping("/{id}/friends/edit")
    public String editFriends(@PathVariable("id") int id, Model model) {
        model.addAttribute("friends", userDAO.showFriends(id));
        model.addAttribute("myId",id);
        return "users/edit_friends";
    }
    @PatchMapping ("/{id}/friends/{id2}")
    public String deleteFriends(@PathVariable("id") int id, @PathVariable("id2") int id2) {
        userDAO.deleteFriend(id,id2);
        return "redirect:/user/{id}/friends/edit";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "/users/show_user";
    }

    @PatchMapping("/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDAO.update(id, user);
        return "redirect:/user/{id}";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "/users/edit_user";
    }

    @GetMapping("/{id}/add")
    public String addFriendForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        User friend = new User();
        model.addAttribute("friend", friend);
        return"/users/add_friend";
    }

    @PatchMapping("/{id}/add-patch")
    public String addFriend(@ModelAttribute("friend") User friend, @PathVariable("id") int myId){
        userDAO.addFriend(myId, friend.getId());
        return "redirect:/user/{id}/friends";
    }

}

