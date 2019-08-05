package edu.mum.se.springbootcrud.eregistrar.service.implementation;

import edu.mum.se.springbootcrud.eregistrar.model.Student;
import edu.mum.se.springbootcrud.eregistrar.repository.StudentRepository;
import edu.mum.se.springbootcrud.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll(Sort.by("firstName"));
    }

    @Override
    public Page<Student> getAllStudentsPaged(int pageNo) {
        return studentRepository.findAll(PageRequest.of(pageNo, 3,Sort.by("firstName","middleName")));
    }

    @Override
    public Student saveStudent(Student s) {
        return studentRepository.save(s);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudentById(Long id) {
         studentRepository.deleteById(id);
    }
}
