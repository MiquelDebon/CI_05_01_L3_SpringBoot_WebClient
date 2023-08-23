package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception.NoSuchElement;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FlowerServiceAsynchronous implements  IServiceAsynchronous{

    private WebClient webClient;

    /**
     * Asynchronous and non Blocking - Reactive method
     */

    //I added a sleep delay to make sure it works as it should be
    @Override
    public Flux<FlowerDTO> getAllReactive(){
        return webClient
            .get().uri(PATH.GET_ALL)
            .retrieve()
            .bodyToFlux(FlowerDTO.class)
            .delayElements(Duration.ofSeconds(1))
            .doOnError((throwable) -> {
                throw new NoSuchElement("Error");
            });

  }

    @Override
    public Mono<FlowerDTO> getOneReactive(Integer id) {
        return webClient.get().uri(PATH.GET_ONE, id)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .doOnError((throwable) -> {
                    throw new NoSuchElement("Any element with this ID");
                });

    }

    @Override
    public Mono<FlowerDTOReturn> addReactive(FlowerDTO flowerDTO) {
        try{
            return webClient.post()
                    .uri(PATH.POST)
                    .syncBody(flowerDTO)
                    .retrieve()
                    .bodyToMono(FlowerDTOReturn.class);
        }catch (WebClientResponseException wcre){
            log.error("Error status '{}'",wcre.getStatusText());
            throw wcre;
        }catch (Exception ex){
            log.error("Error thrown", ex);
            throw ex;
        }
    }


    @Override
    public Mono<FlowerDTOReturn> updateReactive(FlowerDTO flowerDTO) {
        try{
            return webClient.put()
                    .uri(PATH.PUT)
                    .syncBody(flowerDTO)
                    .retrieve()
                    .bodyToMono(FlowerDTOReturn.class);
        }catch (WebClientResponseException wcre){
            log.error("Error status '{}'",wcre.getStatusText());
            throw wcre;
        }catch (Exception ex){
            log.error("Error thrown", ex);
            throw ex;
        }
    }

    @Override
    public Mono<FlowerDTOReturn> deleteReactive(Integer id) {
        try{
            Mono<FlowerDTOReturn> deletedElement =
                    webClient.delete()
                            .uri(PATH.DELETE, id)
                            .retrieve()
                            .bodyToMono(FlowerDTOReturn.class);

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
