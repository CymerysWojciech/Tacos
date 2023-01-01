package pl.budowniczowie.tascocloud.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import pl.budowniczowie.tascocloud.Ingredient;
import pl.budowniczowie.tascocloud.Ingredient.Type;
import pl.budowniczowie.tascocloud.Taco;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacosController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "pszenna", Ingredient.Type.WRAP),
                new Ingredient("COTO", "kukurydziana", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "mielonej wołowiny", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "kawałków mięsa", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "pomidory pokrojone w kostkię", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "sałata", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "pikantny sos pomidorowy", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "śmietana", Ingredient.Type.SAUCE)
        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid  Taco design, Errors errors) {
        log.info("ala"+ String.valueOf(errors));
        if (errors.hasErrors()){
            return "design";
        }
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }
    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
