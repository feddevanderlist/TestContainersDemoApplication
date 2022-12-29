package nl.vandalist.model;

import lombok.*;

import jakarta.persistence.*;


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
    private String name;
}
