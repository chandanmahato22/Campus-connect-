package in.chandan.CampusConnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {
    @NotBlank(message =  "The name can't be empty of null")
    private String name;

    @NotBlank(message =  "The starting date must be less than end")
    private String start;

    @NotEmpty(message =  "The starting date must be less than end")
    private String end;

    @NotNull(message = "The size must be greater than equal to 10")
    @Size(min = 10)
    private Integer seats;
}
