package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IServiceAsynchronous {
    Flux<FlowerDTO> getAllReactive();
    Mono<FlowerDTO> getOneReactive(Integer id);
    Mono<FlowerDTOReturn> addReactive(FlowerDTO flowerDTO);
    Mono<FlowerDTOReturn> updateReactive (FlowerDTO flowerDTO);
    Mono<FlowerDTOReturn> deleteReactive (Integer id);

}
