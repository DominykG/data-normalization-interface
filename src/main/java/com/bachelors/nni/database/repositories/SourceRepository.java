package com.bachelors.nni.database.repositories;

import com.bachelors.nni.database.models.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, String> {
}
