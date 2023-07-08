package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.controller;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.model.FlowerDTO;
import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.services.ServiceRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/flor")
public class ControllerRestClient {

    @Autowired
    private ServiceRestClient serviceRC;

    //http://localhost:9002/flor/clientFlorsAdd
    //http://localhost:9002/flor/clientFlorsUpdate
    //http://localhost:9002/flor/clientFlorsDelete/{id}
    //http://localhost:9002/flor/clientFlorsGetOne/{id}
    //http://localhost:9002/flor/clientFlorsAll


    @GetMapping("/clientFlorsAll")
    public ResponseEntity<?> getAll(){
        //http://localhost:9002/flor/clientFlorsAll
        //http://localhost:9001/flower/getAll
        List<FlowerDTO> list = serviceRC.getAll();
        System.err.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


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

    @PostMapping("/clientFlorsAdd")
    public ResponseEntity<?> add(@RequestBody FlowerDTO flowerDTO){
        try{
            return new ResponseEntity<>(serviceRC.add(flowerDTO), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/clientFlorsUpdate")
    public ResponseEntity<?> update(@RequestBody FlowerDTO flowerDTO){
        try {
            return new ResponseEntity<>(serviceRC.update(flowerDTO), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("clientFlorsDelete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            return new ResponseEntity<>(serviceRC.delete(id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
