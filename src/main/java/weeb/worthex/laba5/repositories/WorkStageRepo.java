package weeb.worthex.laba5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import weeb.worthex.laba5.models.WorkStage;

@Repository
public interface WorkStageRepo extends JpaRepository<WorkStage, Long> {
    @Query("SELECT SUM(ws.cost) FROM WorkStage ws WHERE ws.repairWork.id = :repairWorkId")
    Double getTotalStagesCostByRepairWorkId(@Param("repairWorkId") Long repairWorkId);
}
