package edu.mum.se.springbootcrud.eregistrar.controller;

import edu.mum.se.springbootcrud.eregistrar.model.Student;
import edu.mum.se.springbootcrud.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }



    @GetMapping(value={"/eregistrar/student/list"})
    public ModelAndView listStudent(@RequestParam(defaultValue = "0") int pageNo){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", studentService.getAllStudentsPaged(pageNo));
        System.out.println("after delted students list =="+ studentService.getAllStudentsPaged(pageNo));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("student/listStudent");
        return modelAndView;
    }
    @GetMapping(value = {"/eregistrar/student/add"})
    public String displayAddBookForm(Model model){
        model.addAttribute("student",new Student());
        return "student/newStudent";

    }
    @PostMapping(value = {"/eregistrar/student/add"})
    public String addNewStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "student/newStudent";
        }
        student = studentService.saveStudent(student);
        return "redirect:/eregistrar/student/list";
    }

    @GetMapping(value = {"/eregistrar/student/edit/{studentId}"})
    public String dsiplayEditForm(@PathVariable Long studentId, Model model){
        Student student =studentService.getStudentById(studentId);
        if(student != null){
            model.addAttribute(student);
            return "student/editStudent";
        }
        return "student/listStudent";
    }
    @PostMapping(value = {"/eregistrar/student/update"})
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("error",bindingResult.getAllErrors());
            return "student/editStudent";
        }
            student = studentService.saveStudent(student);
            return "redirect:/eregistrar/student/list";

    }
    @GetMapping(value = {"/eregistrar/student/delete/{studentId}"})
    public String deleteStudent(@PathVariable Long studentId, Model model){
        System.out.println("st id ======"+ studentId);
//        Student student =studentService.getStudentById(studentId);
            studentService.deleteStudentById(studentId);
        System.out.println(studentId + "deleted !!!!!!");
            return "redirect: /eregistrar/student/list";


    }
}
