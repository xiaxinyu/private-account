package com.account.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.account.core.StringTool;
import com.account.domain.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by Summer.Xia on 9/22/2015.
 */
@Component
public class UserCollection {
    private List<User> users;

    public UserCollection() {
        users = new ArrayList<>();
        User user = new User();
        user.setUserName("summer");
        user.setName("summer");
        user.setPassword("summer");
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isNotEmpty() {
        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }

    public User getUser(String userName) {
        User result = null;
        if (users != null && users.size() > 0 && !StringTool.isNullOrEmpty(userName)) {
            for (User user : users) {
                if (userName.equals(user.getUserName())) {
                    result = user;
                    break;
                }
            }
        }
        return result;
    }
}
