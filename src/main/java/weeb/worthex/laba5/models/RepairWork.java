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
@Table(name = "repair_works")
public class RepairWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false, name = "work_title")
    private String workTitle;
    @Column(length = 45, nullable = false, name = "cost")
    private Float cost;
    @Column(length = 10, nullable = false, name = "repair_days")
    private Long repairDays;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_master_id")
    private Master master;
}
