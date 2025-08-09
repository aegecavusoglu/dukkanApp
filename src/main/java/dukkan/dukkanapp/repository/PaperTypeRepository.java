package dukkan.dukkanapp.repository;

import dukkan.dukkanapp.model.PaperType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaperTypeRepository extends JpaRepository<PaperType, Long> {

}
