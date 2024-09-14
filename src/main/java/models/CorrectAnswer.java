package models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class CorrectAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdCorrectAnswer;
    @NotNull
    private String CorrectAnswer;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "correct_answer")
    @JoinColumn(name="id_question", referencedColumnName = "IdQuestion")
    private Questions Question;
}
