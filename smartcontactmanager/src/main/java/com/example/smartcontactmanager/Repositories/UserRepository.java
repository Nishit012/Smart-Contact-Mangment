package com.example.smartcontactmanager.Repositories;

import com.example.smartcontactmanager.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
