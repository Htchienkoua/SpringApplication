package com.swedbank.StudentApplication.student.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(Long id){
        super("Could not find student id: " + id);
    }
}
