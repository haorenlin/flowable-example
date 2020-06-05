package com.dy.glcx.demo.flowable.boot.repository;

import com.dy.glcx.demo.flowable.boot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
}