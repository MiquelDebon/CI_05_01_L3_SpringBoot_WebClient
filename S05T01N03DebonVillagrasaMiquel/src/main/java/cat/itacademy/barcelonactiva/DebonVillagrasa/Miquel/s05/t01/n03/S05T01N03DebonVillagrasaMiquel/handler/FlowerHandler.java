package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.handler;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller.FlowerControllerTraditional;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.FlowerServiceAsynchronous;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class FlowerHandler {

    @Autowired
    private final FlowerServiceAsynchronous flowerService;

    @Autowired
    private final FlowerControllerTraditional controllerRestClient;

    public FlowerHandler(FlowerServiceAsynchronous flowerService, FlowerControllerTraditional controllerRestClient) {
        this.flowerService = flowerService;
        this.controllerRestClient = controllerRestClient;
    }


    public Mono<ServerResponse> getAll(ServerRequest request){
        Flux<FlowerDTO> list = flowerService.getAllReactive();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list, FlowerDTO.class);
    }

//    public Mono<ServerResponse> getAllFlowerController(ServerRequest request) {
//        return ServerResponse.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(controllerRestClient.getAllReactive(), FlowerDTO.class);
//    }

    public Mono<ServerResponse> getOne(ServerRequest request){
        int id = Integer.valueOf(request.pathVariable("id"));
        Mono<FlowerDTO> flowerDTOMono = flowerService.getOneReactive(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowerDTOMono, FlowerDTO.class);
    }

    public Mono<ServerResponse> add(ServerRequest request){
        Mono<FlowerDTO> dtoMono = request.bodyToMono(FlowerDTO.class);
        return dtoMono
                .flatMap(p -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowerService.addReactive(p), FlowerDTO.class));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Mono<FlowerDTO> dtoMono = request.bodyToMono(FlowerDTO.class);
        return dtoMono
                .flatMap(p -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowerService.updateReactive(p), FlowerDTO.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        int id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowerService.deleteReactive(id), FlowerDTOReturn.class);
    }

}
