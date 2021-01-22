package com.zrainc.cloud.service;

import com.zrainc.cloud.domain.User;

import java.util.List;

public interface UserService {
    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getUserByName(String userName);

    List<User> getUsersByIds(List<Long> ids);
}
