package ad.clinic.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.clinic.model.ERecept;

@Repository
public interface EreceptRepository extends JpaRepository<ERecept, Long> {

    ERecept findEReceptById(Long id);

}
