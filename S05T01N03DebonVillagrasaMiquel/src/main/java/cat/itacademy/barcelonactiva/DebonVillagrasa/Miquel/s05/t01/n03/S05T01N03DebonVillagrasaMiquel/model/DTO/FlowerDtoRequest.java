package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDtoRequest {
    @NotEmpty(message = "Name is mandatory")
    @NotBlank
    @Schema(defaultValue = "Rose", description = "Flower's Name")
    private String name;

    @NotEmpty(message = "Country is mandatory")
    @NotBlank
    @Schema(defaultValue = "Spain", description = "Flower's Country")
    private String country;
}
