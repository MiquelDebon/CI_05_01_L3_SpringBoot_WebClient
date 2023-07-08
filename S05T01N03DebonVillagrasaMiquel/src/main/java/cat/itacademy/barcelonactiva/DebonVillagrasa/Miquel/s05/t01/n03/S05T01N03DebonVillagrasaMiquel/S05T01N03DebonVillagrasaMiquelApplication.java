package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "RestClient Swagger"),
		tags = @Tag(name = "IT-Academy", description = "Main methods Documentation")
)
public class S05T01N03DebonVillagrasaMiquelApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N03DebonVillagrasaMiquelApplication.class, args);
	}

}
