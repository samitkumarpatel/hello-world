package dev.samitkumar.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
	private List<byte[]> listOfArrays;

	@Value("${spring.application.version}")
	private String version;

	@Scheduled(fixedDelay = 40000)
	public void gc() {
		log.info("*********GC called*********");
		System.gc();
	}
	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions
				.route()
				.GET("/", request -> {
					listOfArrays = new ArrayList<>();

					while(listOfArrays.size() < 500) {
						listOfArrays.add(new byte[512 * 256]);
					}

					// Sleep for a short period to slow down the process
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					var response = Map.of("message", "Hello World", "version", version);
					return ServerResponse.ok().bodyValue(response);
				})
				.after((request, response) -> {
					log.info("{} {} {}", request.method(), request.path(), response.statusCode().value());
					return response;
				})
				.build();
	}

}
