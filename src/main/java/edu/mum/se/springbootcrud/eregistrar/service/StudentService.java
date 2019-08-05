package edu.mum.se.springbootcrud.eregistrar.service;

import edu.mum.se.springbootcrud.eregistrar.model.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    public abstract Iterable<Student> getAllStudents();
    public abstract Page<Student> getAllStudentsPaged(int pageNo);
    public abstract Student saveStudent(Student s);
    public abstract Student getStudentById(Long id);
    public abstract void deleteStudentById(Long id);
}
