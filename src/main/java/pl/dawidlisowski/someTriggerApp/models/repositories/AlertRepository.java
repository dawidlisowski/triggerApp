package pl.dawidlisowski.someTriggerApp.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dawidlisowski.someTriggerApp.models.entities.AlertEntity;

import java.util.List;

@Repository
public interface AlertRepository extends CrudRepository<AlertEntity, Integer> {

    List<AlertEntity> findAll();
}
