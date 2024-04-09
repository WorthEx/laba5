package weeb.worthex.laba5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work_stages")
public class WorkStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stage_title", nullable = false)
    private String stage_title;
    @Column(name = "cost", nullable = false)
    private double cost;

    @ManyToOne
    @JoinColumn(name = "fk_repair_work_id")
    private RepairWork repairWork;
}
