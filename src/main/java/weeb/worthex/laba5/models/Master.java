package weeb.worthex.laba5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "masters")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false, name = "surname")
    private String surname;
    @Column(length = 45, nullable = false, name = "name")
    private String name;
    @Column(length = 45, nullable = false, name = "patronymic")
    private String patronymic;
    @Column(length = 1, nullable = false, name = "rank")
    private Integer rank;
    @Column(length = 15, nullable = false, name = "hiring_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiring_date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "master")
    private List<RepairWork> repairWorks;


    public String getHiringDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(this.hiring_date);
    }

    public String getShortName() {
        if (patronymic != null)
            return String.format("%03d |  %s %s. %s. (Rank %d)", id, surname, name.charAt(0), patronymic.charAt(0), rank);
        return String.format("%03d | %s %s (Rank %d)", id, surname, name, rank);
    }
}