package weeb.worthex.laba5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weeb.worthex.laba5.models.RepairWork;

@Repository
public interface RepairWorkRepo extends JpaRepository<RepairWork, Long> {
}
