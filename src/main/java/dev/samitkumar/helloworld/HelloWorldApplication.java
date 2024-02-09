package dev.samitkumar.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
	private static Integer count = 0;
	private static List<int[]> listOfArrays = new ArrayList<>();


	@Value("${spring.application.version}")
	private String version;

	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions
				.route()
				.GET("/count", request -> ServerResponse.ok().bodyValue(Map.of("count", count)))
				.GET("/", request -> {
					++count;

					int[] array = new int[1000000]; // Creating an array of 1 million integers
					listOfArrays.add(array); // Adding the array to the list

					// Sleep for a short period to slow down the process
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					var reply = Map.of("message", "Hello World", "version", version);
					return ServerResponse.ok().bodyValue(reply);
				})
				.after((request, response) -> {
					log.info("{} {} {}", request.method(), request.path(), response.statusCode().value());
					return response;
				})
				.build();
	}

}
