package weeb.worthex.laba5.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weeb.worthex.laba5.models.RepairWork;
import weeb.worthex.laba5.repositories.RepairWorkRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RepairWorkService {
    private final RepairWorkRepo rwRepo;

    @Autowired
    public RepairWorkService(RepairWorkRepo masterRepo) {
        this.rwRepo = masterRepo;
    }

    public List<RepairWork> getRepairWorks() {
        return rwRepo.findAll();
    }

    public void saveRepairWork(RepairWork repairWork) {
        rwRepo.save(repairWork);
    }

    public void deleteRepairWork(Long id) {
        rwRepo.deleteById(id);
    }

    public RepairWork getById(Long id) {
        Optional<RepairWork> result = rwRepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EntityNotFoundException("Repair work with this id could not be found.");
    }
}
