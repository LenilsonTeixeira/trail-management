package br.com.boraviajar.trailmanagement.service.impl;

import br.com.boraviajar.trailmanagement.dto.TrailDTO;
import br.com.boraviajar.trailmanagement.exception.TrailException;
import br.com.boraviajar.trailmanagement.mapper.TrailMapper;
import br.com.boraviajar.trailmanagement.model.Trail;
import br.com.boraviajar.trailmanagement.repository.TrailRepository;
import br.com.boraviajar.trailmanagement.service.TrailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TrailServiceImpl implements TrailService {

    @Autowired
    private TrailRepository trailRepository;

    @Autowired
    private TrailMapper trailMapper;

    @Override
    public List<TrailDTO> findAll() {
        log.debug("Retornando todas as trilhas cadastradas.");

        return this.trailRepository.findAll().stream()
                .map(trailMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TrailDTO> save(TrailDTO trailDTO) {
        try {
            log.debug("Cadastrando trilha. [Trilha: {}]", trailDTO);
            final Trail trail = this.trailRepository.save(trailMapper.convertToModel(trailDTO));
            return Optional.ofNullable(trailMapper.convertToDTO(trail));
        } catch (Exception e) {
            log.error("Falha ao salvar trilha. [Trilha: {}]", trailDTO);
            throw new TrailException("Falha ao salvar trilha.", e);
        }
    }

    @Override
    public Optional<TrailDTO> update(String id, TrailDTO trailDTO) {
        try {
            log.debug("Atualizando trilha id: {}", id);

            final Optional<TrailDTO> trailDb = this.findOne(id);

            trailDb.ifPresent(trail -> trailDTO.setId(trail.getId()));

            final Trail trailUpdated = this.trailRepository.save(trailMapper.convertToModel(trailDTO));

            return Optional.ofNullable(trailMapper.convertToDTO(trailUpdated));

        } catch (Exception e) {
            log.error("Falha ao atualizar trilha com id: {}", id);
            throw new TrailException(String.format("Falha ao atualizar trilha id: %s", id));
        }
    }

    @Override
    public void delete(String id) {
        try {
            log.debug("Removendo trilha com id: {}", id);
            this.trailRepository.deleteById(id);
        } catch (Exception e){
            log.error("Falha ao remover trilha com id: {}", id);
            throw new TrailException(String.format("Falha ao remover trilha id: %s", id));
        }
    }

    @Override
    public Optional<TrailDTO> findOne(String id) {
        return Optional.ofNullable(this.trailRepository.findById(id)
                .stream()
                .map(trailMapper::convertToDTO)
                .findFirst().orElseThrow(() -> new TrailException(String.format("Trilha id: %s não encontrada.", id))));

    }
}
