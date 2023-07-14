package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOSchemaUpdate;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.FlowerServiceSynchronous;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flor")
public class FlowerControllerTraditional {

    @Autowired
    private FlowerServiceSynchronous serviceRC;

    //http://localhost:9002/swagger-ui/index.html

    //http://localhost:9002/flor/clientFlorsAdd
    //http://localhost:9002/flor/clientFlorsUpdate
    //http://localhost:9002/flor/clientFlorsDelete/{id}
    //http://localhost:9002/flor/clientFlorsGetOne/{id}
    //http://localhost:9002/flor/clientFlorsAll

    @Operation(
            tags = "Traditional Synchronous API",
            summary = "Get ALL Flower DTO",
            description = "This method retrieve all the Flower Collection, but " +
                    "YOU CAN NOT STOP THE EXERCUTIION OF QUERY",
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
    @GetMapping("/clientFlorsAll")
    public ResponseEntity<?> getAll(){
        //http://localhost:9002/flor/clientFlorsAll
        //http://localhost:9001/flower/getAll
        List<FlowerDTO> list = serviceRC.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @Operation(
            tags = "Traditional Synchronous API",
            summary = "Get one Flower DTO",
            description = "Description: Use the repository to extract the Flower entity " +
                    "and then became it to DTO",
            parameters = @Parameter(name = "id", required = true, example = "1"),
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
    @GetMapping("/clientFlorsGetOne/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        //http://localhost:9002/flor/clientFlorsGetOne/18
        //http://localhost:9001/flower/getOne/{id}
        try{
            return new ResponseEntity<>(serviceRC.getOne(id), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Traditional Synchronous API",
            summary = "SAVE one flower",
            description = "Description: This method save a new flower in the database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Expected a Flower JSON",
                    content = @Content(schema = @Schema(implementation = FlowerDTO.class))),

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful response",
                            content = @Content(schema = @Schema(implementation = FlowerDTOReturn.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request buddy",
                            content = @Content
                    )
            }
    )
    @PostMapping("/clientFlorsAdd")
    public ResponseEntity<?> add(@RequestBody FlowerDTO flowerDTO){
        try{
            return new ResponseEntity<>(serviceRC.add(flowerDTO), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(
            tags = "Traditional Synchronous API",
            summary = "Update one existing flower",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Expected a Flower JSON",
                    content = @Content(schema = @Schema(implementation = FlowerDTOSchemaUpdate.class))
            ),
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
    @PutMapping("/clientFlorsUpdate")
    public ResponseEntity<?> update(@RequestBody FlowerDTO flowerDTO){
        try {
            return new ResponseEntity<>(serviceRC.update(flowerDTO), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            tags = "Traditional Synchronous API",
            summary = "Delete one object by NºId",
            description = "This method delete the object from the database",
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
    @DeleteMapping("clientFlorsDelete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            return new ResponseEntity<>(serviceRC.delete(id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
