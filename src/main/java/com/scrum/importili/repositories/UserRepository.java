package com.scrum.importili.repositories;

import com.scrum.importili.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsByEmailAndPassword(String email, String pwd);
    public boolean existsByEmail(String email);
    public User findByEmail(String email);
}
