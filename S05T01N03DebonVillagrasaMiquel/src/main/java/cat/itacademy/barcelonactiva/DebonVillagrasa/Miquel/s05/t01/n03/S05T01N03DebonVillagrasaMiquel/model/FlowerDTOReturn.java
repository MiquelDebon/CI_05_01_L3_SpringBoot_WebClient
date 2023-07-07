package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlowerDTOReturn {
    private String name;
    private String country;
    private String europe;

    public FlowerDTOReturn(String name, String country, String europe){
        this.name = name;
        this.country = country;
        this.europe = europe;
    }
}
