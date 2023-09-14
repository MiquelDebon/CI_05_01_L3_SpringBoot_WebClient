package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDto;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoRequest;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO.FlowerDtoReturn;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.FlowerServiceAsynchronousImpl;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.IFlowerServiceAsynchronous;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/flor/reactive/")
public class FlowerControllerReactive {
    private IFlowerServiceAsynchronous flowerServiceRC;

    @Autowired
    public FlowerControllerReactive(FlowerServiceAsynchronousImpl serviceRC_Reactive) {
        this.flowerServiceRC = serviceRC_Reactive;
    }

    //http://localhost:9002/swagger-ui/index.html

    //http://localhost:9002/flor/reactive/getAll

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
                            content = @Content(schema = @Schema(implementation = FlowerDtoReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content
                    )
            }
    )
    @GetMapping(value = "/getAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<FlowerDto>> getAllReactive(){
        Flux<FlowerDto> allFlowers =  flowerServiceRC.getAllReactive();
        return new ResponseEntity<>(allFlowers, HttpStatus.OK);

    }


    @Operation(
            tags = "Asynchronous Reactive API",
            summary = "Get one Flower DTO",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDtoReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content
                    )
            }
    )
    @GetMapping("/getOne/{id}")
    public ResponseEntity<Mono<FlowerDto>> getOneReactive(@PathVariable int id){
        return new ResponseEntity<>(flowerServiceRC.getOneReactive(id), HttpStatus.OK);
    }


    @Operation(
            tags = "Asynchronous Reactive API",
            summary = "Add one Flower DTO",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDtoReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content
                    )
            }
    )
    @PostMapping("/add")
    public ResponseEntity<Mono<FlowerDtoReturn>> addFlowerReactive(@RequestBody @Valid FlowerDtoRequest flowerDtoRequest){
        Mono<FlowerDtoReturn> returnDTO = flowerServiceRC.addReactive(flowerDtoRequest);
        return new ResponseEntity<>(returnDTO, HttpStatus.OK);
    }


    @Operation(
            tags = "Asynchronous Reactive API",
            summary = "Update one Flower DTO",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDtoReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content
                    )
            }
    )
    @PutMapping("/update")
    public ResponseEntity<?> updateReactive(@RequestBody FlowerDto flowerDTO){
        Mono<FlowerDtoReturn> updatedFlower = flowerServiceRC.updateReactive(flowerDTO);
        return ResponseEntity.ok(updatedFlower);
    }

    @Operation(
            tags = "Asynchronous Reactive API",
            summary = "Delete one Flower DTO",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful updated",
                            content = @Content(schema = @Schema(implementation = FlowerDtoReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReactive(@PathVariable int id){
        Mono<FlowerDtoReturn> deletedElement = flowerServiceRC.deleteReactive(id);
        return ResponseEntity.ok(deletedElement);
    }


}
