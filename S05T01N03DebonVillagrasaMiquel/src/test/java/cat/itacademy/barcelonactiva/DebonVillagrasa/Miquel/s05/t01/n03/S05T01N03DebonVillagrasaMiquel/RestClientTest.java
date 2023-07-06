package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller.reactiveController;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestClientTest {
    final String BASE_URL = "http://localhost:9001/flower/";

    private WebClient webClient = WebClient.create(BASE_URL);
    private reactiveController reactiveController = new reactiveController(webClient);

    @Test  //TODO: ERROR Test Junit5
    public void testGetAll(){
        List<FlowerDTO> list = reactiveController.getAll();
        assertTrue(list.size() > 0);
    }


}
