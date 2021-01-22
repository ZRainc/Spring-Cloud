package com.zrainc.cloud.service.impl;

import com.zrainc.cloud.domain.User;
import com.zrainc.cloud.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yuchen Zhang
 * Date: 2021/1/21
 * Time: 10:11
 * Description: No Description
 */

@Service
public class UserServiceImpl implements UserService {
    private List<User> userList;

    @Override
    public void create(User user) {
        userList.add(user);
    }

    @Override
    public User getUser(Long id) {
        List<User> users = userList.stream().filter(userItem -> userItem.getId().equals(id)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void update(User user) {
        userList.stream().filter(userItem -> userItem.getId().equals(user.getId())).forEach(userItem -> {
            userItem.setUsername(user.getUsername());
            userItem.setPassword(user.getPassword());
        });
    }

    @Override
    public void delete(Long id) {
        User user = getUser(id);
        if(user != null) {
            userList.remove(user);
        }
    }

    @Override
    public User getUserByName(String userName) {
        List<User> users = userList.stream().filter(userItem -> userItem.getUsername().equals(userName)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(users)){
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> getUsersByIds(List<Long> ids) {
        return userList.stream().filter(userItem -> ids.contains(userItem.getId())).collect(Collectors.toList());
    }

    @PostConstruct
    public void initData() {
        userList = new ArrayList<>();
        userList.add(new User(1L, "zrainc", "123456"));
        userList.add(new User(2L, "ziwei", "123456"));
        userList.add(new User(3L, "yuchen", "123456"));
    }
}
