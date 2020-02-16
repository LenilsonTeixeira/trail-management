package br.com.boraviajar.trailmanagement.repository;

import br.com.boraviajar.trailmanagement.model.Trail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrailRepository extends MongoRepository<Trail, String> {
}