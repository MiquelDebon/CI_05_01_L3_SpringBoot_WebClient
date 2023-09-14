package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlowerDtoReturn {
    private String name;
    private String country;
    protected String europe;

    public FlowerDtoReturn(String name, String country, String europe){
        this.name = name;
        this.country = country;
        this.europe = europe;
    }
}
