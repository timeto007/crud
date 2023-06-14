package com.refacorjava.crud.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student nesru=new Student(      
	    	"nesru",
		    "sv@gmail.com",
		    LocalDate.of(2001,Month.DECEMBER,5)
	    	);
            Student john=new Student(      
                "john",
                "qq@gmail.com",
                LocalDate.of(2011,Month.APRIL,3)
                );

                repository.saveAll(List.of(nesru,john));
           };
    }
    
}
