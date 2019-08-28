package com.nirala.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nirala.model.Student;

@RestController
@RequestMapping("/student")
public class Swagger2RestController {

	List<Student> students = new ArrayList<Student>();
	{
		students.add(new Student("Rajani", "IV", "India"));
		students.add(new Student("Mohan", "V", "India"));
		students.add(new Student("Sugreev", "III", "USA"));
		students.add(new Student("Robin", "VI", "USA"));
	}

	@RequestMapping(value = "/getStudents", method = RequestMethod.GET)
	public List<Student> getStudents() {
		return students;
	}

	@RequestMapping(value = "/getStudent/{name}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable(value = "name") String name) {
		return students.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
	}

	@RequestMapping(value = "/getStudentByCountry/{country}", method = RequestMethod.GET)
	public List<Student> getStudentByCountry(@PathVariable(value = "country") String country) {
		System.out.println("Searching Student in country : " + country);
		List<Student> studentsByCountry = students.stream().filter(x -> x.getCountry().equalsIgnoreCase(country))
				.collect(Collectors.toList());
		System.out.println(studentsByCountry);
		return studentsByCountry;
	}

	@RequestMapping(value = "/getStudentByClass/{cls}", method = RequestMethod.GET)
	public List<Student> getStudentByClass(@PathVariable(value = "cls") String cls) {
		return students.stream().filter(x -> x.getCls().equalsIgnoreCase(cls)).collect(Collectors.toList());
	}

}
