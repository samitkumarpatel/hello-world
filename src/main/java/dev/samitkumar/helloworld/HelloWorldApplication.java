package dev.samitkumar.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

@SpringBootApplication
@Slf4j
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
	private static Integer count = 0;

	@Value("${spring.application.version}")
	private String version;

	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions
				.route()
				.GET("/count", request -> ServerResponse.ok().bodyValue(Map.of("count", count)))
				.GET("/", request -> {
					++count;
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
