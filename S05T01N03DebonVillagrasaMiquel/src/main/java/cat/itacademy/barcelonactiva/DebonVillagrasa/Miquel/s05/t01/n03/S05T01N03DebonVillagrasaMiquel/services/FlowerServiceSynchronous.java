package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class FlowerServiceSynchronous implements  IServiceSynchronous{

    private WebClient webClient;

    @Autowired
    public FlowerServiceSynchronous(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Traditional
     */
    @Override
    public List<FlowerDTO> getAll(){
        //http://localhost:9002/flor/clientFlorsAll
        //http://localhost:9001/flower/getAll

        List<FlowerDTO> list = webClient
                .get().uri(PATH.GET_ALL)
                .retrieve()
                .bodyToFlux(FlowerDTO.class)
                .collectList() //Mono<List<Flower>>
                .block();   //To Synchronous  List<Flower>
        System.out.println(list);
        return list;
    }

    @Override
    public FlowerDTO getOne(Integer flowerID){
        //http://localhost:9002/flor/clientFlorsGetOne/18
        //http://localhost:9001/flower/getOne/{id}
        try{
            return webClient.get().uri(PATH.GET_ONE, flowerID)
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

    @Override
    public FlowerDTOReturn add(FlowerDTO flowerDTO){
        //http://localhost:9001/flower/clientFlorsAdd
        try{
            return webClient.post()
                    .uri(PATH.POST)
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

    @Override
    public FlowerDTOReturn update(FlowerDTO flowerDTO){
        //http://localhost:9001/flower/update

        try{
            return webClient.put()
                    .uri(PATH.PUT)
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

    @Override
    public FlowerDTOReturn delete(Integer id){
        try{
            FlowerDTOReturn deletedElement =
                    webClient.delete()
                            .uri(PATH.DELETE, id)
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
