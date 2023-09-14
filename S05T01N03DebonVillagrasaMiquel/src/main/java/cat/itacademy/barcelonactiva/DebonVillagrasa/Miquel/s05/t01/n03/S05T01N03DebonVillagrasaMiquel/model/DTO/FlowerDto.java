package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.UECountries;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowerDto {
    @NotBlank
    private Integer id;
    @NotBlank
    private String name;
    private String country;
    private String europe;


    public FlowerDto(String name, String country){
        this.name = name;
        this.country = country;
        this.europe = UECountries.UEcountriesList.stream()
                .map(String::toLowerCase)
                .anyMatch(country.toLowerCase()::contains) ? "UE" : "Fora UE";
    }
    public FlowerDto(Integer id, String name, String country){
        this.id = id;
        this.name = name;
        this.country = country;
        this.europe = UECountries.UEcountriesList.stream()
                .map(String::toLowerCase)
                .anyMatch(country.toLowerCase()::contains) ? "UE" : "Fora UE";
    }

}
