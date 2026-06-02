package luciano_558975.GeoShield.controller;

import jakarta.validation.Valid;
import luciano_558975.GeoShield.dtos.RegiaoDTO;
import luciano_558975.GeoShield.service.RegiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/regioes")
public class RegiaoController {

    @Autowired
    private RegiaoService regService;

    @PostMapping
    public ResponseEntity<RegiaoDTO> createRegiao(@RequestBody @Valid RegiaoDTO dto) {
        dto = regService.saveRegiao(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<RegiaoDTO>> getAllRegioes() {
        List<RegiaoDTO> dtos = regService.getAllRegioes();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoDTO> getRegiaoById(@PathVariable Long id) {
        return ResponseEntity.ok(regService.getRegiaoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoDTO> updateRegiao(@PathVariable Long id,
                                                  @Valid @RequestBody RegiaoDTO dto) {
        return ResponseEntity.ok(regService.updateRegiao(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegiao(@PathVariable Long id) {
        regService.deleteRegiao(id);
        return ResponseEntity.noContent().build();
    }
}
