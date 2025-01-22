package com.in28minutes.learn_spring_security.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityPlayResource {
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World V1";
	}

}
