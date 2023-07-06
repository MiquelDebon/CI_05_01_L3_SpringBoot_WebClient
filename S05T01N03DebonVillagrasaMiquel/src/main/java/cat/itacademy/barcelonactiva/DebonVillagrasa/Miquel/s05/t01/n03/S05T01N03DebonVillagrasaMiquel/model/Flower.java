package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flower {
    private int pk_FlorID;
    private String nomFlor;
    private String paisFlor;


    public Flower(String name, String country){
        this.nomFlor = name;
        this.paisFlor = country;
    }
}
