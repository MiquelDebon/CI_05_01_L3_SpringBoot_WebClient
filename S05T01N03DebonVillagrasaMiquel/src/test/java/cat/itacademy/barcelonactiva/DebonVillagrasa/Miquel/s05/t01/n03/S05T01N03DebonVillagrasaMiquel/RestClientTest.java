package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.PATH;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.FlowerServiceSynchronous;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestClientTest {

    private WebClient webClient = WebClient.create(PATH.BASE_URL);
    private FlowerServiceSynchronous apiService = new FlowerServiceSynchronous(webClient);

    @Test
    public void testMono(){
        Mono<String> stringMono = Mono.just("HolaString").log();
        System.out.println(stringMono);
        System.out.println(stringMono.subscribe());
        System.out.println(stringMono.block());
        stringMono.subscribe(System.out::println); //The right way
    }




    @Test
    @DisplayName("Check there are elements in the List")
    void testGetAll(){
        List<FlowerDTO> list = apiService.getAll();
        System.out.println(list);
        assertTrue(list.size() > 0);
        assertNotNull(list);
    }


    @Test
    @DisplayName("Check the API retrieve a valid element")
    void testGetOneById(){
        //There should be existing elements
        int id = apiService.getAll().get(1).getId();
        FlowerDTO flowerDTO = apiService.getOne(id);
        System.err.println(flowerDTO);
        Assertions.assertInstanceOf(FlowerDTO.class, flowerDTO);
    }

    @Test
    @DisplayName("Check Invalid ID in GetOne/{id} -> Exception")
    void testGetOneById_InvalidID(){
        Assertions.assertThrows(
                WebClientResponseException.class,
                () -> apiService.getOne(-1) );
    }


    @Test
    @DisplayName("Check if a new element is post correctly")
    void testPostOne(){
        FlowerDTO flowerDTO = new FlowerDTO("Paris", "Francia");
        FlowerDTOReturn dtoReturn = apiService.add(flowerDTO);
        assertEquals(dtoReturn.getEurope(), "UE");
        Assertions.assertInstanceOf(FlowerDTOReturn.class, dtoReturn);
    }

    @Test
    @DisplayName("Assert error 400 - Wrong post input")
    void testWrongPost(){
        FlowerDTO flowerDTO = new FlowerDTO();
        Assertions.assertThrows(
                WebClientResponseException.class,
                () -> apiService.add(flowerDTO));

    }


    @Test
    @DisplayName("Check the element has modified")
    void testUpdate(){

        int id = apiService.getAll().get(1).getId();
        FlowerDTO expected = new FlowerDTO(id, "TestUpdate", "Italia");
        FlowerDTOReturn returned = apiService.update(expected);

        System.out.println(expected);
        System.out.println(returned);
        assertEquals(expected.getName(), returned.getName());
        assertEquals(expected.getCountry(), returned.getCountry());

    }

    @Test
    @DisplayName("Check to delete an element")
    void testDelete(){
        FlowerDTO newflowerDTO = new FlowerDTO(null, "DeleteTest", "Country");
        apiService.add(newflowerDTO);

        int id = apiService.getAll().size();
            System.err.println("Size list before: " + id);
        int deleteID = apiService.getAll().get(--id).getId();
        apiService.delete(deleteID);
            System.err.println("Size list after: " + apiService.getAll().size());

        Assertions.assertThrows(
                WebClientResponseException.class,
                () -> apiService.getOne(deleteID));
    }



}
