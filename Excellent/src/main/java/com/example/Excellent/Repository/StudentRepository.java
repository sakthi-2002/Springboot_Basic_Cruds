package com.example.Excellent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Excellent.Model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
