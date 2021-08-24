package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Override
    List<Student> findAll();

    @Query("select s from Student s where s.name = ?1")
    List<Student> findByName(String name);
}
