package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(hidden = true)
public class FlowerDTOSchemaUpdate {

    //This class is use as the Scheme for the UPDATE method

    @Schema
    private int id;
    @Schema(defaultValue = "Rosa")
    private String name;
    @Schema(defaultValue = "Spain")
    private String country;

}
