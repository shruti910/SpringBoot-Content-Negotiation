package com.example.org.content;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	
	@Autowired
	private StudentRepo studentRepository;

	@RequestMapping(
			  value = "/students", 
			  produces = { MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE }, 
			  method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Student> retrieveAllStudents() {
		return studentRepository.findAll();
	}

	
	@RequestMapping(
			  value ="/student/{rollno}", 
			  produces = { "application/json", "application/xml" }, 
			  method = RequestMethod.GET)
	public @ResponseBody Student retrieveStudent(@PathVariable long rollno) throws Exception {
		Optional<Student> student = studentRepository.findByRollNo(rollno);

		if (!student.isPresent())
			throw new Exception();

		return student.get();
	}
	
	@RequestMapping(
			  value ="/student", 
			  produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, 
			  method = RequestMethod.POST)
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		
		try {
			studentRepository.save(student);
			return ResponseEntity.status(HttpStatus.CREATED).body("STUDENT CREATED");
		
		} catch(Exception e){

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	
	@RequestMapping(
			  value ="/student/{rollno}", 
			  produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" }, 
			  method = RequestMethod.PUT)
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long rollno) {

		Optional<Student> studentOptional = studentRepository.findByRollNo(rollno);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();
		else {
			
			Student st = studentOptional.get();
			st.setName(student.getName());
			st.setDepartment(student.getDepartment());
			st.setAttendance(student.getAttendance());
			studentRepository.save(st);
			return ResponseEntity.ok().build();
	}
		
	}
	
	@RequestMapping(
			  value ="/student/{rollno}", 
			  consumes = { "application/json", "application/xml" }, 
			  method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable long rollno) {
		studentRepository.deleteByRollNo(rollno);
	}
	
	@RequestMapping(
			  value ="/search/{searchItem}", 
			  produces = { "application/json", "application/xml" }, 
			  method = RequestMethod.GET)
	public ResponseEntity<?> searchStudent(@PathVariable String searchItem) throws Exception {
		
		Specification<Student> rollNoLike = 
			      (root, query, criteriaBuilder) -> 
			         criteriaBuilder.like(root.get("rollNo").as(String.class), "%"+searchItem+"%");
		Optional<List<Student>> studentList = Optional.ofNullable(studentRepository.findAll(rollNoLike));

		if (!studentList.isPresent())
			throw new Exception();

		return new ResponseEntity<>(studentList.get(), HttpStatus.OK);
	}
	
}
