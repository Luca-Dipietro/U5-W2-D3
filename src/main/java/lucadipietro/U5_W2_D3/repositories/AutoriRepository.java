package lucadipietro.U5_W2_D3.repositories;

import lucadipietro.U5_W2_D3.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoriRepository extends JpaRepository<Autore, UUID> {
}
