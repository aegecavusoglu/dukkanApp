package dukkan.dukkanapp.controller;

import dukkan.dukkanapp.model.PaperType;
import dukkan.dukkanapp.service.PaperTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/papertypes")
public class PaperTypeController {
    private final PaperTypeService paperTypeService;

    public PaperTypeController(PaperTypeService paperTypeService) {
        this.paperTypeService = paperTypeService;
    }
    // Tüm kağıt türlerini getir
    @GetMapping
    public List<PaperType> getAllPaperTypes() {
        return paperTypeService.getAllPaperTypes();
    }

    // ID ile kağıt türü getir
    @GetMapping("/{id}")
    public Optional<PaperType> getPaperTypeById(@PathVariable Long id) {
        return paperTypeService.getPaperTypeById(id);
    }

    // Yeni kağıt türü ekle
    @PostMapping
    public PaperType createPaperType(@RequestBody PaperType paperType) {
        return paperTypeService.savePaperType(paperType);
    }

    // Kağıt türü sil
    @DeleteMapping("/{id}")
    public void deletePaperType(@PathVariable Long id) {
        paperTypeService.deletePaperType(id);
    }
}
