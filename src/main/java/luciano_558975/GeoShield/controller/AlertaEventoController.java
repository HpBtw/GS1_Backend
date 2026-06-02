package luciano_558975.GeoShield.controller;

import jakarta.validation.Valid;
import luciano_558975.GeoShield.dtos.AlertaEventoDTO;
import luciano_558975.GeoShield.service.AlertaEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaEventoController {

    @Autowired
    private AlertaEventoService aleService;

    @PostMapping
    public ResponseEntity<AlertaEventoDTO> createAlerta(@RequestBody @Valid AlertaEventoDTO dto) {
        dto = aleService.saveAlerta(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<AlertaEventoDTO>> getAllAlertas() {
        List<AlertaEventoDTO> dtos = aleService.getAllAlertas();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaEventoDTO> getAlertaById(@PathVariable Long id) {
        return ResponseEntity.ok(aleService.getAlertaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaEventoDTO> updateAlerta(@PathVariable Long id,
                                                        @RequestBody @Valid AlertaEventoDTO dto) {
        return ResponseEntity.ok(aleService.updateAlerta(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlerta(@PathVariable Long id) {
        aleService.deleteAlerta(id);
        return ResponseEntity.noContent().build();
    }
}
