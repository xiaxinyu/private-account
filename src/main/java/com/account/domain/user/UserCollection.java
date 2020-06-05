package com.account.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.account.core.tool.StringTool;
import com.account.persist.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by Summer.Xia on 9/22/2015.
 */
@Component
public class UserCollection {
    private List<User> users;

    public UserCollection() {
        users = new ArrayList<>();
        User user = User.builder().userName("summer").name("summer").password("summer").build();
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
