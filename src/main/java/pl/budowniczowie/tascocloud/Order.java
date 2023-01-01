package pl.budowniczowie.tascocloud;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class Order {

    @NotBlank(message = "Nazwa jest wymagana")
    @Size(min = 4)
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message="State is required")
    //tag::allButValidation[]
    private String state;
    //end::allButValidation[]

    @NotBlank(message="Zip code is required")
    //tag::allButValidation[]
    private String zip;
    //end::allButValidation[]

    //tag::allButValidation[]
    private String ccNumber;
    //end::allButValidation[]

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    //tag::allButValidation[]
    private String ccExpiration;
    //end::allButValidation[]

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    //tag::allButValidation[]
    private String ccCVV;


}
