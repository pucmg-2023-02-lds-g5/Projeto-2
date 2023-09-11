package com.example.app.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Teste
 */
@RestController
public class Index {
	@GetMapping("/")
	public String teste() {
		return "Hello World!";
	}
}
