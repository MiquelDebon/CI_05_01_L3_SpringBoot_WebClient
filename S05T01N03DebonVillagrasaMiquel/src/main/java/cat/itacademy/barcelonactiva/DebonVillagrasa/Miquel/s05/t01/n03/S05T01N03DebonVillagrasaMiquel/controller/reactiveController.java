package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class reactiveController {
    final String GET_ALL = "getAll";


    private WebClient webClient;

    public reactiveController(WebClient webClient){
        this.webClient = webClient;
    }

    //http://localhost:9001/flower/getAll
    public List<FlowerDTO> getAll(){
        return webClient.get().uri(GET_ALL)
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList() //Mono<List<Flower>>
                .block();   //To Synchronous  List<Flower>

    }

}
