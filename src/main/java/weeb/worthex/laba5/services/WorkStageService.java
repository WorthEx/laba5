package weeb.worthex.laba5.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weeb.worthex.laba5.models.RepairWork;
import weeb.worthex.laba5.models.WorkStage;
import weeb.worthex.laba5.repositories.RepairWorkRepo;
import weeb.worthex.laba5.repositories.WorkStageRepo;

import java.util.List;
import java.util.Optional;

@Service
public class WorkStageService {
    private final WorkStageRepo wsRepo;
    private final RepairWorkRepo rwRepo;

    @Autowired
    public WorkStageService(WorkStageRepo wsRepo, RepairWorkRepo rwRepo) {
        this.wsRepo = wsRepo;
        this.rwRepo = rwRepo;
    }

    public List<WorkStage> getWorkStages() {
        return wsRepo.findAll();
    }

    public void saveWorkStage(WorkStage ws, double oldCost) {
        RepairWork repairWork = rwRepo.findById(ws.getRepairWork().getId()).orElse(null);
        if (repairWork == null) {
            throw new IllegalArgumentException("Repair work with such id couldn't be found!");
        }
        double totalStagesCost = wsRepo.getTotalStagesCostByRepairWorkId(repairWork.getId()) != null ?
                wsRepo.getTotalStagesCostByRepairWorkId(repairWork.getId()) : 0;
        if (totalStagesCost - oldCost + ws.getCost() > repairWork.getCost()) {
            throw new IllegalArgumentException("The sum of the cost of the stages exceeds the total cost of the work");
        }
        wsRepo.save(ws);
    }

    public void deleteWorkStage(Long id) {
        wsRepo.deleteById(id);
    }

    public WorkStage getById(Long id) {
        Optional<WorkStage> result = wsRepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EntityNotFoundException("Work stage with this id could not be found.");
    }
}
