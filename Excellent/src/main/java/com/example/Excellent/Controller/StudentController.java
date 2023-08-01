package com.example.Excellent.Controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Excellent.Model.Student;
import com.example.Excellent.Repository.StudentRepository;

@RestController
@RequestMapping("/api")

public class StudentController {
    
    @Autowired //Anotation is used to create a variable.
    StudentRepository studentRepository; //Object is created.

    @GetMapping("/show_all")

    public List<Student> getAllTutorials(){
        return(List<Student>) studentRepository.findAll();
    }



    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){ 
        Student studentrep = studentRepository.save(new Student(student.getFirstname(),student.getLastname(),student.getPassword()));  

        return new ResponseEntity<Student>(studentrep,HttpStatus.OK);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllStudents(){
        studentRepository.deleteAll();
        return new ResponseEntity<> (HttpStatus.NOT_FOUND);
    }


    @PutMapping("/insert/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,@RequestBody Student student){ //Tutorial object
        Optional <Student> studentrepo =  studentRepository.findById(id); //tutorialrepo obj is to get the table values and stores in the obj.
        if(studentrepo.isPresent()){
            Student _studentrepo = studentrepo.get();  
            _studentrepo.setFirstname(student.getFirstname());
            _studentrepo.setLastname(student.getLastname());
            _studentrepo.setPassword(student.getPassword());

            
            return new ResponseEntity<Student> (studentRepository.save(_studentrepo),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }



    
}
