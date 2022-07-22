package nl.vandalist.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@Entity
@Table(name = "language")
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NonNull
    @Column
    String name;


}
