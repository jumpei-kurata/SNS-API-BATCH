package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogbackTestController {
	
	@GetMapping(value = "/logbackTest")
	public int logbackTest() {
		int i = 1;
		int a = 0;
		int test = i/a;
		System.out.println(test);
		return i;
	}

}
