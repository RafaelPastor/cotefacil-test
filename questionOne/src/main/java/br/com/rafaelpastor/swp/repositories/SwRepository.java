package br.com.rafaelpastor.swp.repositories;

import br.com.rafaelpastor.swp.entities.SwData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwRepository extends JpaRepository<SwData, String> {

    List<SwData> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    List<SwData> findAllByOrderByNameAsc();

    List<SwData> findByIdContainingIgnoreCaseOrderByNameAsc(String id);
}
