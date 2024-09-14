package models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class TypeOfQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdTypeOfQuestion;
    @NotNull
    private String NameType;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "TypeOfQuestion")
    private List<Questions> Question;
}
