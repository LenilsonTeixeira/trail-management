package br.com.boraviajar.trailmanagement.controller;

import br.com.boraviajar.trailmanagement.dto.TrailDTO;
import br.com.boraviajar.trailmanagement.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trilhas")
public class TrailController {


    @Autowired
    private TrailService trailService;

    @GetMapping
    public ResponseEntity<List<TrailDTO>> findAll() {
        return ResponseEntity.ok(this.trailService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody final TrailDTO trailDTO) {
        final Optional<TrailDTO> trailDb = this.trailService.save(trailDTO);

        final URI uri = UriComponentsBuilder
                .fromPath("trilhas/{id}")
                .buildAndExpand(trailDb.get().getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrailDTO> update(@PathVariable("id") final String id, @Valid @RequestBody final TrailDTO trailDTO) {
        return this.trailService.update(id, trailDTO)
                .map(trail -> ResponseEntity.ok(trail)).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
        this.trailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
