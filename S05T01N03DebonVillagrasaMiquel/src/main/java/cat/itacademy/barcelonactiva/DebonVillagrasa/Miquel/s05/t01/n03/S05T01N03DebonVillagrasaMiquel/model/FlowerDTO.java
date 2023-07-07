package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowerDTO {
    private Integer id;
    private String name;
    private String country;
    private String europe;

    public FlowerDTO(String name, String country){
        this.name = name;
        this.country = country;
        this.europe = UECountries.UEcountriesList.stream()
                .map(String::toLowerCase)
                .anyMatch(country.toLowerCase()::contains) ? "UE" : "Fora UE";
    }
    public FlowerDTO(Integer id, String name, String country){
        this.id = id;
        this.name = name;
        this.country = country;
        this.europe = UECountries.UEcountriesList.stream()
                .map(String::toLowerCase)
                .anyMatch(country.toLowerCase()::contains) ? "UE" : "Fora UE";
    }
}
