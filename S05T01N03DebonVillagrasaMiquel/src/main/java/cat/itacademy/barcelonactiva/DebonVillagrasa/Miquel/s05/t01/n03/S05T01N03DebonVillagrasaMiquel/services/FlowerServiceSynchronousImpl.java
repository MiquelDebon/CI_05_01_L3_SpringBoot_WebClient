package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDto;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoRequest;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Slf4j
public class FlowerServiceSynchronousImpl implements IFlowerServiceSynchronous {

    private WebClient webClient;

    @Autowired
    public FlowerServiceSynchronousImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Traditional
     */
    @Override
    public List<FlowerDto> getAll(){
        //http://localhost:9002/flor/clientFlorsAll
        //http://localhost:9001/flower/getAll

        List<FlowerDto> list = webClient
                .get().uri(PATH.GET_ALL)
                .retrieve()
                .bodyToFlux(FlowerDto.class)
                .collectList() //Mono<List<Flower>>
                .block();   //To Synchronous  List<Flower>
        System.out.println(list);
        return list;
    }

    @Override
    public FlowerDto getOne(Integer flowerID){
        //http://localhost:9002/flor/clientFlorsGetOne/18
        //http://localhost:9001/flower/getOne/{id}
        try{
            return webClient.get().uri(PATH.GET_ONE, flowerID)
                    .retrieve()
                    .bodyToMono(FlowerDto.class)
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
    public FlowerDtoReturn add(FlowerDtoRequest flowerDTO){
        //http://localhost:9001/flower/clientFlorsAdd
        try{
            return webClient.post()
                    .uri(PATH.POST)
                    .syncBody(flowerDTO)
                    .retrieve()
                    .bodyToMono(FlowerDtoReturn.class)
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
    public FlowerDtoReturn update(FlowerDto flowerDTO){
        //http://localhost:9001/flower/update

        try{
            return webClient.put()
                    .uri(PATH.PUT)
                    .syncBody(flowerDTO)
                    .retrieve()
                    .bodyToMono(FlowerDtoReturn.class)
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
    public FlowerDtoReturn delete(Integer id){
        try{
            FlowerDtoReturn deletedElement =
                    webClient.delete()
                            .uri(PATH.DELETE, id)
                            .retrieve()
                            .bodyToMono(FlowerDtoReturn.class)
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
