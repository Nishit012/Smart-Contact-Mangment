package com.example.smartcontactmanager.Repositories;

import com.example.smartcontactmanager.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
