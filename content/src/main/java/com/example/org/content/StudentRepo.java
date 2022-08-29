package com.example.org.content;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends JpaRepository<Student, Long>,JpaSpecificationExecutor<Student>  {

	Optional<Student> findByRollNo(long rollno);

	void deleteByRollNo(long rollno);

}
