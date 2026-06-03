package luciano_558975.GeoShield.service;

import jakarta.persistence.EntityNotFoundException;
import luciano_558975.GeoShield.dtos.AlertaEventoDTO;
import luciano_558975.GeoShield.entities.AlertaEvento;
import luciano_558975.GeoShield.entities.NivelRisco;
import luciano_558975.GeoShield.entities.Regiao;
import luciano_558975.GeoShield.exceptions.DatabaseException;
import luciano_558975.GeoShield.exceptions.ResourceNotFoundException;
import luciano_558975.GeoShield.repository.AlertaEventoRepository;
import luciano_558975.GeoShield.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlertaEventoService {

    @Autowired
    AlertaEventoRepository aleRepo;

    @Autowired
    RegiaoRepository regRepo;

    @Transactional
    public AlertaEventoDTO saveAlerta(AlertaEventoDTO dto) {
        try {
            AlertaEvento al = new AlertaEvento();
            mapDtoToAlerta(dto, al);
            return new AlertaEventoDTO(aleRepo.save(al));
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível salvar a alerta. Região de ID: '" + dto.getRegiao().getId() + "' não encontrada.");
        }
    }

    @Transactional(readOnly = true)
    public List<AlertaEventoDTO> getAllAlertas() {
        List<AlertaEvento> alertas = aleRepo.findAll();
        return alertas.stream().map(AlertaEventoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public AlertaEventoDTO getAlertaById(Long id) {
        return new AlertaEventoDTO(aleRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Alerta de ID: '" + id + "' não encontrado.")
        ));
    }

    @Transactional
    public AlertaEventoDTO updateAlerta(Long id, AlertaEventoDTO dto) {
        try {
            AlertaEvento al = aleRepo.getReferenceById(id);
            mapDtoToAlerta(dto, al);
            return new AlertaEventoDTO(aleRepo.save(al));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Alerta de ID: '" + id + "' não encontrado.");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível salvar a alerta. Região de ID: '" + dto.getRegiao().getId() + "' não encontrada.");
        }
    }

    @Transactional
    public void deleteAlerta(Long id) {
        if(!aleRepo.existsById(id)) throw new ResourceNotFoundException("Alerta de ID: '" + id + "' não encontrado.");
        aleRepo.deleteById(id);
    }

    private void mapDtoToAlerta(AlertaEventoDTO dto, AlertaEvento al) {
        al.setTipoAlerta(dto.getTipoAlerta());
        al.setDataEmissao(dto.getDataEmissao());
        al.setProbabilidadeTragedia(dto.getProbabilidadeTragedia());
        Regiao regiao = regRepo.getReferenceById(dto.getRegiao().getId());
        al.setRegiao(regiao);
        Double aux = dto.getProbabilidadeTragedia();
        if (aux <= 25.0) al.setNivelRisco(NivelRisco.LEVE);
        else if (aux <= 50.0) al.setNivelRisco(NivelRisco.MEDIO);
        else if (aux <= 75.0) al.setNivelRisco(NivelRisco.GRANDE);
        else al.setNivelRisco(NivelRisco.EXTREMO);
    }
}
