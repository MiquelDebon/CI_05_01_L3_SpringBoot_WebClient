package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDto;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoRequest;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoReturn;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFlowerServiceAsynchronous {
    Flux<FlowerDto> getAllReactive();
    Mono<FlowerDto> getOneReactive(Integer id);
    Mono<FlowerDtoReturn> addReactive(FlowerDtoRequest flowerDTO);
    Mono<FlowerDtoReturn> updateReactive (FlowerDto flowerDTO);
    Mono<FlowerDtoReturn> deleteReactive (Integer id);

}
