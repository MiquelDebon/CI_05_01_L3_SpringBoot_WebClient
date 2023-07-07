package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller.reactiveController;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestClientTest {
    final String BASE_URL = "http://localhost:9001/flower/";

    private WebClient webClient = WebClient.create(BASE_URL);
    private reactiveController reactiveController = new reactiveController(webClient);


    //TODO: ERROR Test Junit5
    @Test
    @DisplayName("Check there are elements in the List")
    void testGetAll(){
        List<FlowerDTO> list = reactiveController.getAll();
        System.out.println(list);
        assertTrue(list.size() > 0);
    }

    @Test
    @DisplayName("Check the API retrieve a valid element")
    void testGetOneById(){
        //There should be existing elements
        int id = reactiveController.getAll().get(1).getId();
        FlowerDTO flowerDTO = reactiveController.getOne(id);
        Assertions.assertInstanceOf(FlowerDTO.class, flowerDTO);
    }

    @Test
    @DisplayName("Check Invalid ID in GetOne/{id} -> Exception")
    void testGetOneById_InvalidID(){
        Assertions.assertThrows(
                WebClientResponseException.class,
                () -> reactiveController.getOne(-1) );
    }


    @Test
    @DisplayName("Check if a new element is post correctly")
    void testPostOne(){
        FlowerDTO flowerDTO = new FlowerDTO("Paris", "Francia");
        FlowerDTOReturn dtoReturn = reactiveController.add(flowerDTO);
        assertEquals(dtoReturn.getEurope(), "UE");
        Assertions.assertInstanceOf(FlowerDTOReturn.class, dtoReturn);
    }

    @Test
    @DisplayName("Assert error 400 - Wrong post input")
    void testWrongPost(){
        FlowerDTO flowerDTO = new FlowerDTO();
        Assertions.assertThrows(
                WebClientResponseException.class,
                () -> reactiveController.add(flowerDTO));

    }


    @Test
    @DisplayName("Check the element has modified")
    void testUpdate(){

        int id = reactiveController.getAll().get(1).getId();
        FlowerDTO expected = new FlowerDTO(id, "TestUpdate", "Italia");
        FlowerDTOReturn returned = reactiveController.update(expected);

        System.out.println(expected);
        System.out.println(returned);
        assertEquals(expected.getName(), returned.getName());
        assertEquals(expected.getCountry(), returned.getCountry());

    }



}
