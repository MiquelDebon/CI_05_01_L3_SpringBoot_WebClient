package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.router;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller.FlowerControllerTraditional;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDto;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoRequest;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoReturn;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.FlowerServiceAsynchronousImpl;
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
    private final FlowerServiceAsynchronousImpl flowerService;

    @Autowired
    private final FlowerControllerTraditional controllerRestClient;

    public FlowerHandler(FlowerServiceAsynchronousImpl flowerService, FlowerControllerTraditional controllerRestClient) {
        this.flowerService = flowerService;
        this.controllerRestClient = controllerRestClient;
    }


    public Mono<ServerResponse> getAll(ServerRequest request){
        Flux<FlowerDto> list = flowerService.getAllReactive();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list, FlowerDto.class);
    }

//    public Mono<ServerResponse> getAllFlowerController(ServerRequest request) {
//        return ServerResponse.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(controllerRestClient.getAllReactive(), FlowerDTO.class);
//    }

    public Mono<ServerResponse> getOne(ServerRequest request){
        int id = Integer.valueOf(request.pathVariable("id"));
        Mono<FlowerDto> flowerDTOMono = flowerService.getOneReactive(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowerDTOMono, FlowerDto.class);
    }

    public Mono<ServerResponse> add(ServerRequest request){
        Mono<FlowerDtoRequest> dtoMono = request.bodyToMono(FlowerDtoRequest.class);
        return dtoMono
                .flatMap(p -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowerService.addReactive(p), FlowerDto.class));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Mono<FlowerDto> dtoMono = request.bodyToMono(FlowerDto.class);
        return dtoMono
                .flatMap(p -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowerService.updateReactive(p), FlowerDto.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        int id = Integer.valueOf(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowerService.deleteReactive(id), FlowerDtoReturn.class);
    }

}
