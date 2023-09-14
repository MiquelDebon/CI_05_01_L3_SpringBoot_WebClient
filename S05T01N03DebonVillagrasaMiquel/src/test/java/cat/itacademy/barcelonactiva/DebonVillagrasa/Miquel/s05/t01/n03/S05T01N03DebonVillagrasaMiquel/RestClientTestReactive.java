package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDto;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.FlowerServiceAsynchronousImpl;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.PATH;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

public class RestClientTestReactive {

    private WebClient webClient = WebClient.create(PATH.BASE_URL);
    private FlowerServiceAsynchronousImpl apiService = new FlowerServiceAsynchronousImpl(webClient);


    @Test
    @DisplayName("Check there are elements in the List")
    void testGetAll(){
        Flux<FlowerDto> list = apiService.getAllReactive();
        System.out.println(list);
        assertNotNull(list);
    }



    @Test
    @DisplayName("Check the API retrieve a valid element")
    void testGetOneById(){
        //There should be existing elements
        int id = apiService.getAllReactive().blockFirst().getId();
        Mono<FlowerDto> flowerDTO = apiService.getOneReactive(id);
        System.err.println(flowerDTO.block());
        assertNotNull(flowerDTO);
    }


}
