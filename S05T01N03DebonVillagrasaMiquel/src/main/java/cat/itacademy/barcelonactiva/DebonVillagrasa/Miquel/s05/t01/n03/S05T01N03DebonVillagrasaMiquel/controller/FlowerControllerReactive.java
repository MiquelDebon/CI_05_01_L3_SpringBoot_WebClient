package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception.NoSuchElement;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.FlowerServiceAsynchronous;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/flor")
public class FlowerControllerReactive {
    private FlowerServiceAsynchronous flowerServiceRC;

    @Autowired
    public FlowerControllerReactive(FlowerServiceAsynchronous serviceRC_Reactive) {
        this.flowerServiceRC = serviceRC_Reactive;
    }

    //http://localhost:9002/swagger-ui/index.html

    //http://localhost:9002/flor/clientFlorsAll/reactive

    //This reactive method fails in Swagger but still works
    @Operation(
            tags = "Asynchronous Reactive API",
            summary = "Get ALL Flower DTO",
            description = "This method retrieve all the Flower Collection, but " +
                    "YOU CAN STOP WHENEVER YOU WANT THE EXECUTION",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDTOReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content
                    )
            }
    )
    @GetMapping(value = "/clientFlorsAll/reactive", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<FlowerDTO>> getAllReactive(){
        try{
            Flux<FlowerDTO> allFlowers =  flowerServiceRC.getAllReactive();

            return new ResponseEntity<>(allFlowers, HttpStatus.OK);
        }catch (NoSuchElement e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getOne/{id}")
    public ResponseEntity<Mono<FlowerDTO>> getOneReactive(@PathVariable int id){
        try{
            return new ResponseEntity<>(flowerServiceRC.getOneReactive(id), HttpStatus.OK);
        }catch (NoSuchElement e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



}
