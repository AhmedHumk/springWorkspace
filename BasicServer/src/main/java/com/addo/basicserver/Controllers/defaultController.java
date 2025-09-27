package com.addo.basicserver.Controllers;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addo.basicserver.Services.GPTService;

@RestController
public class defaultController {
	
	@Autowired
	GPTService mygtpservice;
	// Port:8080
	// chat?message=hello
	@GetMapping("/chat")
	public CompletableFuture<Map<String, Object>> chat(@RequestParam String message) {
		System.out.println(mygtpservice.getAiconfig().getUrl() + "\n" + message);
		return mygtpservice.AskGPT(message);
	}
	
	

}
