package dukkan.dukkanapp.service;

import dukkan.dukkanapp.model.PaperType;
import dukkan.dukkanapp.repository.PaperTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaperTypeService {
    private final PaperTypeRepository paperTypeRepository;

    public PaperTypeService(PaperTypeRepository paperTypeRepository) {
        this.paperTypeRepository = paperTypeRepository;
    }
    public List<PaperType> getAllPaperTypes() {
        return paperTypeRepository.findAll();
    }

    public Optional<PaperType> getPaperTypeById(Long id) {
        return paperTypeRepository.findById(id);
    }

    public PaperType savePaperType(PaperType paperType) {
        return paperTypeRepository.save(paperType);
    }

    public void deletePaperType(Long id) {
        paperTypeRepository.deleteById(id);
    }
}
