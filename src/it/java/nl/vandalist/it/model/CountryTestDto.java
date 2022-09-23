package nl.vandalist.it.model;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryTestDto {

    private Long id;
    private String name;
    private String capital;
}
