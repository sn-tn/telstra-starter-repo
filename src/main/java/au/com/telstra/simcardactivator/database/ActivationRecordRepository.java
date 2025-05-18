package au.com.telstra.simcardactivator.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationRecordRepository extends JpaRepository<ActivationRecord, Long> {
}
