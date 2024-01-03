package ad.clinic.service;

import org.springframework.stereotype.Service;

import ad.clinic.data.EreceptRepository;
import ad.clinic.model.ERecept;

@Service
public class EReceptService {
    private final EreceptRepository ereceptRepository;

    public EReceptService(EreceptRepository ereceptRepository) {
        this.ereceptRepository = ereceptRepository;
    }

    public void saveRecept(ERecept erecept) {
        ereceptRepository.save(erecept);
    }

    public ERecept findRecept(Long id) {
        ERecept eRecept = ereceptRepository.findEReceptById(id);
        return eRecept;
    }

}
