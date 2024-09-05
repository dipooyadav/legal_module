package com.nscs.SBMaster.service;

import com.nscs.SBMaster.Entity.User;

public interface UserService {

    public User saveUser(User user);
    public void removeSessionMessage();

}
