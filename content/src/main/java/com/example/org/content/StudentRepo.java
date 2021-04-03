package com.example.org.content;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

	Optional<Student> findByRollNo(long rollno);

	void deleteByRollNo(long rollno);

}
