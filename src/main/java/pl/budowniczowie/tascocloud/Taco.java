package pl.budowniczowie.tascocloud;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Nazwa powinna składaćsięz minimóm pieńciu znaków")
    private String name;

    @Size(min = 1, message = "Musisz wybrać jeden dodatek")
    private List<String> ingredients;

}
