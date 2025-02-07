package cl.somosafac.afacbackend.repository;


import cl.somosafac.afacbackend.entity.FichaInfanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaInfanteRepository extends JpaRepository<FichaInfanteEntity, Long> {
    FichaInfanteEntity findByRutInfante(String rutInfante);
}