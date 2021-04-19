package com.bachelors.nni.database.repositories;

import com.bachelors.nni.database.models.RssFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssFeedRepository extends JpaRepository<RssFeed, String> {
}
