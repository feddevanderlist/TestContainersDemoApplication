package nl.vandalist.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Getter
@Setter
@Builder
@Table("gebruiker")
@NoArgsConstructor
@AllArgsConstructor
public class GebruikerDto {

    @PrimaryKey
    @Id
    private Long id;
    @NonNull
    private String voornaam;
    @NonNull
    private String achternaam;
    @NonNull
    private Integer leeftijd;
    @NonNull
    private String titel;
    @Column("is_admin")
    private boolean isAdmin;

}
