package weeb.worthex.laba5.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weeb.worthex.laba5.models.WorkStage;
import weeb.worthex.laba5.repositories.WorkStageRepo;

import java.util.List;
import java.util.Optional;

@Service
public class WorkStageService {
    private final WorkStageRepo wsRepo;

    @Autowired
    public WorkStageService(WorkStageRepo wsRepo) {
        this.wsRepo = wsRepo;
    }

    public List<WorkStage> getWorkStages() {
        return wsRepo.findAll();
    }

    public void saveWorkStage(WorkStage ws) {
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
