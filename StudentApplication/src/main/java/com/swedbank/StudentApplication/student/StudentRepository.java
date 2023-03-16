package com.swedbank.StudentApplication.student;

import com.swedbank.StudentApplication.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value= "SELECT * FROM STUDENT s WHERE s.id =1", nativeQuery = true)
    List<Student> getFirstStudent();



    @org.springframework.data.jdbc.repository.query.Query("SELECT * FROM STUDENT s WHERE s.name= :name")
    List<Student> getByName(@Param("name") String name);

}
