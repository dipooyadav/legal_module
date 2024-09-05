package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    public User findByEmail(String email);
}
