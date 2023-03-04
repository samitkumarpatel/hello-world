package com.samitkumarpatel.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.InetAddress;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
}

@Controller
class WelcomeController {

	@GetMapping("/")
	public String main(Model model) throws Exception {
		var hostname = InetAddress.getLocalHost().getHostName();
		var ipAddr = InetAddress.getLocalHost().getHostAddress();
		model.addAttribute("hostname", hostname);
		model.addAttribute("ip", ipAddr);
		return "welcome";
	}

}