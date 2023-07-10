package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.router;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.handler.FlowerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FlowerRouter {
    private static final String PATH_BASE = "flowers";
    @Bean
    RouterFunction<ServerResponse> router (FlowerHandler handler){
        return RouterFunctions.route()
                .GET(PATH_BASE, handler::getAll)
                .GET(PATH_BASE + "/{id}", handler::getOne)
                .POST(PATH_BASE, handler::add)
                .PUT(PATH_BASE, handler::update)
                .DELETE(PATH_BASE, handler::delete)
                .build();
    }
}
