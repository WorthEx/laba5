package weeb.worthex.laba5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weeb.worthex.laba5.models.Master;

import java.util.List;

@Repository
public interface MasterRepo extends JpaRepository<Master, Long> {
    List<Master> findAllByOrderByIdAsc();
}
