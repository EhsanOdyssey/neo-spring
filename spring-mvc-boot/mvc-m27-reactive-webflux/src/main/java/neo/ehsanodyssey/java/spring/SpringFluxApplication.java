package neo.ehsanodyssey.java.spring;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import neo.ehsanodyssey.java.spring.handler.ProjectHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class SpringFluxApplication {

	// Use WebFlux's RouterFunction to wrap request the handler function in our project
	@Bean
	RouterFunction<?> routes(ProjectHandler handler) {
		return RouterFunctions.route(GET("/router/hello"),
				serverRequest -> ServerResponse.ok().body(fromObject("Hello World")))
				.andRoute(GET("/router/project/{name}"), handler::findProject);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringFluxApplication.class, args);
	}

}
