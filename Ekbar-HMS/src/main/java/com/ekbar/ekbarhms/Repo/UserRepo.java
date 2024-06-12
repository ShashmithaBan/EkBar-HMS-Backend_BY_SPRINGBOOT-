package com.ekbar.ekbarhms.Repo;

import com.ekbar.ekbarhms.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    public User findByEmail(String username);
}
