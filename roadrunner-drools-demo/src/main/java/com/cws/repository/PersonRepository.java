package com.cws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cws.model.Person;

/**
 * Specifies methods used to obtain and modify person related information
 * which is stored in the database.
 * @author 
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
