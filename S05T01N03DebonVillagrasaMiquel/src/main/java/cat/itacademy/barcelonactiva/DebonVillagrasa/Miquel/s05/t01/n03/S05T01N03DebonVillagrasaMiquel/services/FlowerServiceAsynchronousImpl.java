package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception.customExceptions.EmptyDatabaseException;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception.customExceptions.NoSuchElementException;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDto;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoRequest;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoReturn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Slf4j
@AllArgsConstructor
public class FlowerServiceAsynchronousImpl implements IFlowerServiceAsynchronous {

    private WebClient webClient;

    /**
     * Asynchronous and non Blocking - Reactive method
     */

    //I added a sleep delay to make sure it works as it should be
    @Override
    public Flux<FlowerDto> getAllReactive(){
        return webClient
            .get().uri(PATH.GET_ALL)
            .retrieve()
            .bodyToFlux(FlowerDto.class)
            .delayElements(Duration.ofSeconds(1))
                .doOnError((throwable) -> {
                    throw new EmptyDatabaseException();
                });
  }

    @Override
    public Mono<FlowerDto> getOneReactive(Integer id) {
        return webClient.get().uri(PATH.GET_ONE, id)
                .retrieve()
                .bodyToMono(FlowerDto.class)
                    .doOnError((throwable) -> {
                        throw new NoSuchElementException();
                    });
    }

    @Override
    public Mono<FlowerDtoReturn> addReactive(FlowerDtoRequest flowerDtoRequest) {
        return webClient.post()
                .uri(PATH.POST)
                .syncBody(flowerDtoRequest)
                .retrieve()
                .bodyToMono(FlowerDtoReturn.class);
    }


    @Override
    public Mono<FlowerDtoReturn> updateReactive(FlowerDto flowerDTO) {
        return webClient.put()
                .uri(PATH.PUT)
                .syncBody(flowerDTO)
                .retrieve()
                .bodyToMono(FlowerDtoReturn.class);
    }

    @Override
    public Mono<FlowerDtoReturn> deleteReactive(Integer id) {
        return webClient.delete()
                        .uri(PATH.DELETE, id)
                        .retrieve()
                        .bodyToMono(FlowerDtoReturn.class)
                .doOnError((throwable) -> {
                    throw new NoSuchElementException();
                });
    }


//    public Mono<FlowerDTOReturn> getError(){
//        return webClient.get()
//                .uri(PATH.ERROR)
//                .retrieve()
//                .onStatus(
//                        HttpStatusCode::is4xxClientError,
//                        response -> response.bodyToMono(String.class).map(Exception::new));
//
//    }

}
