package nl.vandalist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Getter
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class GebruikerDto {

    @PrimaryKey
    private Long id;
    private String voornaam;
    private String achternaam;
    private Integer leeftijd;
    private String titel;
    private Boolean isAdmin;

}
