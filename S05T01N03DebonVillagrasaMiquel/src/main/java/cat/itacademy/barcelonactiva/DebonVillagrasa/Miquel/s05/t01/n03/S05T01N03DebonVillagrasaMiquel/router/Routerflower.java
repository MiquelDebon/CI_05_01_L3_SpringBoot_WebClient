//package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.router;
//
//import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.router.FlowerHandler;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import org.springframework.web.servlet.function.HandlerFunction;
//
//import static org.springframework.web.servlet.function.RequestPredicates.GET;
//import static org.springframework.web.servlet.function.RequestPredicates.accept;
//
//
//@Configuration
//public class Routerflower {
//
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
//
////    @Bean
////    public org.springframework.web.servlet.function.RouterFunction<ServerResponse> flowerRouterMethod (FlowerHandler flowerHandler){
////
////        return org.springframework.web.servlet.function.RouterFunctions
////                .route(GET("/api/functional/flower/").and(accept(MediaType.APPLICATION_JSON)), flowerHandler::getAll);
//////                .andRoute(GET("/api/functional/flower/{id}").and(accept(MediaType.APPLICATION_JSON)),flowerHandler::findById)
////                //.andRoute(POST("/functional/flower/"),flowerHandler::saveFlower)
////                //.andRoute(PUT("/functional/flower/{id}"),flowerHandler::update)
//////                .andRoute(DELETE("/api/functional/flower/{id}").and(accept(MediaType.APPLICATION_JSON)),flowerHandler::delete);
////    }
//
//
//
////    @Bean
////    RouterFunction<ServerResponse> getFruitsAll() {
////        return route(GET("/employees/{id}"),
////                req -> ok().body(
////                        employeeRepository().findEmployeeById(req.pathVariable("id")), Employee.class));
////    }
//
//}
