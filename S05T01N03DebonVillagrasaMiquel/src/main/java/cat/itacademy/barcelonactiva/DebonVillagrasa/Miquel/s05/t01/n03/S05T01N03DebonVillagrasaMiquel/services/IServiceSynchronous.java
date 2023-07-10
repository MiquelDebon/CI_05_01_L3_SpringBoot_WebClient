package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTOReturn;

import java.util.List;

public interface IServiceSynchronous {
    List<FlowerDTO> getAll();
    FlowerDTO getOne(Integer id);
    FlowerDTOReturn add(FlowerDTO flowerDTO);
    FlowerDTOReturn update (FlowerDTO flowerDTO);
    FlowerDTOReturn delete(Integer id);

}
