package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
@Slf4j

public class reactiveController {
    final String GET_ALL = "getAll";
    final String GET_ONE = "getOne/{id}";
    final String POST = "add";


    private WebClient webClient;

    public reactiveController(WebClient webClient){
        this.webClient = webClient;
    }

    public List<FlowerDTO> getAll(){
        //http://localhost:9001/flower/getAll
        return webClient.get().uri(GET_ALL)
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList() //Mono<List<Flower>>
                .block();   //To Synchronous  List<Flower>

    }


    public FlowerDTO getOne(int flowerID){
        //http://localhost:9001/flower/getOne/17
        //http://localhost:9001/flower/getOne/{id}
        try{
            return webClient.get().uri(GET_ONE, flowerID)
                    .retrieve()
                    .bodyToMono(FlowerDTO.class)
                    .block();

        }catch (WebClientResponseException wcre){
            log.error("Error status '{}'",wcre.getStatusText());
            throw wcre;
        }catch (Exception ex){
            log.error("Error thrown", ex);
            throw ex;
        }

    }

    public FlowerDTOReturn postOne(FlowerDTO flowerDTO){
        //http://localhost:9001/flower/add
        try{
            return webClient.post()
                    .uri(POST)
                    .syncBody(flowerDTO)
                    .retrieve()
                    .bodyToMono(FlowerDTOReturn.class)
                    .block();
        }catch (WebClientResponseException wcre){
            log.error("Error status '{}'",wcre.getStatusText());
            throw wcre;
        }catch (Exception ex){
            log.error("Error thrown", ex);
            throw ex;
        }

    }



}
