package br.com.boraviajar.trailmanagement.service;

import br.com.boraviajar.trailmanagement.dto.TrailDTO;

import java.util.List;
import java.util.Optional;

public interface TrailService {

    List<TrailDTO> findAll();

    Optional<TrailDTO> save(TrailDTO trailDTO);

    Optional<TrailDTO> update(String id, TrailDTO trailDTO);

    void delete(String id);

    Optional<TrailDTO> findOne(String id);
}
