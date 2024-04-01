package weeb.worthex.laba5.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weeb.worthex.laba5.models.Master;
import weeb.worthex.laba5.repositories.MasterRepo;

import java.util.List;
import java.util.Optional;

@Service
public class MasterService {
    private final MasterRepo masterRepo;

    @Autowired
    public MasterService(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    public List<Master> getMasters() {
        return masterRepo.findAll();
    }

    public void saveMaster(Master master) {
        masterRepo.save(master);
    }

    public void updateMaster(Master master) {
        masterRepo.save(master);
    }

    public void deleteMaster(Long id) {
        masterRepo.deleteById(id);
    }

    public Master getById(Long id) {
        Optional<Master> result = masterRepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EntityNotFoundException("Master with this id could not be found.");
    }

    public String cmth() {
        return "";
    }
}
