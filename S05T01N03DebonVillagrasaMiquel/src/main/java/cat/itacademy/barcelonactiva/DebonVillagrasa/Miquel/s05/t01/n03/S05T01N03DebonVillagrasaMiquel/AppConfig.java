package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.handler.FlowerHandler;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.PATH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.client.HttpClient;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


@Configuration
public class AppConfig {
    /**
     *  WebClient: Non-blocking, reactive client to perform HTTP requests, exposing a fluent, reactive API over
     *      underlying HTTP client libraries such as Reactor Netty.
     */

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create();
        return WebClient.builder().baseUrl(PATH.BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

//    private static final String PATH_BASE = "flowers";
//    @Bean
//    RouterFunction<ServerResponse> router (FlowerHandler flowerHandler){
//        return RouterFunctions.route()
//                .GET(PATH_BASE, flowerHandler::getAll)
////                .GET(PATH_BASE + "/{id}", flowerHandler::getOne)
////                .POST(PATH_BASE, flowerHandler::add)
////                .PUT(PATH_BASE, flowerHandler::update)
////                .DELETE(PATH_BASE, flowerHandler::delete)
//                .build();
//    }

    RouterFunction<ServerResponse> route = RouterFunctions.route()
            .GET("/hello-world", accept(MediaType.TEXT_PLAIN),
                    request -> ServerResponse.ok().bodyValue("Hello World")).build();


}

