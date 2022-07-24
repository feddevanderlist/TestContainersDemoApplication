package nl.vandalist.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@Entity
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NonNull
    @Column
    private String name;
    @NonNull
    @Column
    private String capital;
}
