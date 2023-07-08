package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/flor")
public class reactiveController {
    final String GET_ALL = "getAll";
    final String GET_ONE = "getOne/{id}";
    final String POST = "add";
    final String PUT = "update";
    final String DELETE = "delete/{id}";

    private WebClient webClient;

    public reactiveController(WebClient webClient){
        this.webClient = webClient;
    }

//     - http://localhost:9002/flor/clientFlorsAdd
//     - http://localhost:9002/flor/clientFlorsUpdate
//     - http://localhost:9002/flor/clientFlorsDelete/{id}
//     - http://localhost:9002/flor/clientFlorsGetOne/{id}
//     - http://localhost:9002/flor/clientFlorsAll

    @GetMapping("/clientFlorsAll")
    public List<FlowerDTO> getAll(){
        //http://localhost:9002/flor/clientFlorsAll
        //http://localhost:9001/flower/getAll
        return webClient.get().uri(GET_ALL)
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList() //Mono<List<Flower>>
                .block();   //To Synchronous  List<Flower>

    }

    @GetMapping("/clientFlorsAll2")
    public ResponseEntity<?> getAll2(){
        //http://localhost:9002/flor/clientFlorsAll2
        //http://localhost:9001/flower/getAll
        List<FlowerDTO> list = webClient.get().uri(GET_ALL)
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList() //Mono<List<Flower>>
                .block();   //To Synchronous  List<Flower>

        System.err.println(list);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/clientFlorsGetOne/{id}")
    public FlowerDTO getOne(Integer flowerID){
        //http://localhost:9002/flor/clientFlorsGetOne/18
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

    @PostMapping("/Add")
    public FlowerDTOReturn add(FlowerDTO flowerDTO){
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


    @PutMapping("/Update")
    public FlowerDTOReturn update(FlowerDTO flowerDTO){
        //http://localhost:9001/flower/update

        try{
            return webClient.put()
                    .uri(PUT)
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

    public FlowerDTOReturn delete(Integer id){
        try{
            FlowerDTOReturn deletedElement =
            webClient.delete()
                    .uri(DELETE, id)
                    .retrieve()
                    .bodyToMono(FlowerDTOReturn.class)
                    .block();

            return deletedElement;
        }catch (WebClientResponseException wcre){
            log.error("Error status '{}'",wcre.getStatusText());
            throw wcre;
        }catch (Exception ex){
            log.error("Error thrown", ex);
            throw ex;
        }
    }



}
