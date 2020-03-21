package pl.kafarsoon.craftymarkdown.feature.suggestion.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Suggestion {
    @Id
    private Long id;
    private String name;
}
