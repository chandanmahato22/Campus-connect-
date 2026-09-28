package in.chandan.CampusConnect.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {
    private String name;

    private String start;

    private String end;

    private int seats;
}
