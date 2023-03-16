package com.swedbank.StudentApplication.student;


import com.swedbank.StudentApplication.student.exception.StudentNotFoundException;
import com.swedbank.StudentApplication.student.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @Slf4j
    @RestController
    @RequestMapping("students")
    @RequiredArgsConstructor
    public class StudentController {
        private final StudentRepository repository;

        @GetMapping()
        public List<Student> all(){
            return repository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Student> get(@PathVariable final long id){
            return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id)), HttpStatus.ACCEPTED);
        }

        @PutMapping("/{id}")
        public Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id){
            return repository.findById(id)
                    .map(student  -> {
                        student.setName(newStudent.getName());
                        student.setSurname(newStudent.getSurname());
                        return repository.save(student);
                    }).orElseGet(()->{newStudent.setId(id);
                        return repository.save(newStudent);
                    });
        }

        @DeleteMapping("/{id}")
        public void deleteStudent(@PathVariable final long id){
            repository.deleteById(id);
        }

        @GetMapping("/first")
        public Student getFirst() {
            return repository.getFirstStudent().get(0); }


        @GetMapping("/byname")
        public List<Student>  getByName(@RequestParam() String name) {
            return  repository.getByName(name);}


    }

