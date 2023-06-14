package com.refacorjava.crud.Student;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
	
	@Autowired
    public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	public void addNewStudent(Student student) {

		Optional<Student> studenOptionel= studentRepository.findStudentByemail(student.getEmail());
		if(studenOptionel.isPresent()){
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}
	public void deleteStudent(Long studentId) {
		
			boolean exists =studentRepository.existsById(studentId);
			if(!exists) {
				throw new IllegalStateException("student with id "+ studentId +" does not exists ");
			}
			studentRepository.deleteById(studentId);
		}
	@Transactional	
	public void updateStudent(Long studentId, String name, String email) {
		Student student=studentRepository.findById(studentId)
		.orElseThrow(()-> new IllegalThreadStateException(studentId +" does not exists"));

		if(name !=null && name.length()>0 && !Objects.equals(student.getName(),name)){
			student.setName(name);
		}

		if (email !=null && !Objects.equals(student.getEmail(),email)){
			Optional<Student> studentOptional=studentRepository.findStudentByemail(email);
			if(studentOptional.isPresent()){
				throw new IllegalStateException("email taken");
			}student.setEmail(email);
		}
	}
	
}