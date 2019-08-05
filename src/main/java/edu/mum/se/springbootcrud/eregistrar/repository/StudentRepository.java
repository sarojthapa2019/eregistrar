package edu.mum.se.springbootcrud.eregistrar.repository;

import edu.mum.se.springbootcrud.eregistrar.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
