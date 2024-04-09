package weeb.worthex.laba5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repair_works")
public class RepairWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false, name = "work_title")
    private String workTitle;
    @Column(length = 45, nullable = false, name = "cost")
    private double cost;
    @Column(length = 10, nullable = false, name = "repair_days")
    private Long repairDays;

    @ManyToOne
    @JoinColumn(name = "fk_master_id")
    private Master master;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "repairWork")
    private List<WorkStage> workStages;

    public String getShortName() {
        return String.format("%03d | %s | %f | Repair days: %d | Master: %d", id, workTitle, cost, repairDays, master.getId());
    }
}
