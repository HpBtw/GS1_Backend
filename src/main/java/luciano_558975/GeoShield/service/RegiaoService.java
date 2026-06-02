package luciano_558975.GeoShield.service;

import jakarta.persistence.EntityNotFoundException;
import luciano_558975.GeoShield.dtos.RegiaoDTO;
import luciano_558975.GeoShield.entities.Regiao;
import luciano_558975.GeoShield.exceptions.ResourceNotFoundException;
import luciano_558975.GeoShield.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class RegiaoService {

    @Autowired
    private RegiaoRepository regRepo;

    @Transactional
    public RegiaoDTO saveRegiao(RegiaoDTO dto) {
        Regiao r = new Regiao();
        mapDtoToRegiao(dto, r);
        return new RegiaoDTO(regRepo.save(r));
    }

    @Transactional(readOnly = true)
    public List<RegiaoDTO> getAllRegioes() {
        List<Regiao> regioes = regRepo.findAll();
        return regioes.stream().map(RegiaoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public RegiaoDTO getRegiaoById(Long id) {
        return new RegiaoDTO(regRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Região de ID: '" + id + "' não encontrada.")
        ));
    }

    @Transactional
    public RegiaoDTO updateRegiao(Long id, RegiaoDTO dto) {
        try {
            Regiao r = regRepo.getReferenceById(id);
            mapDtoToRegiao(dto, r);
            return new RegiaoDTO(regRepo.save(r));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Região de ID: '" + id + "' não encontrada.");
        }
    }

    @Transactional
    public void deleteRegiao(Long id) {
        if (!regRepo.existsById(id)) throw new ResourceNotFoundException("Região de ID: '" + id + "' não encontrado.");
        regRepo.deleteById(id);
    }

    private void mapDtoToRegiao(RegiaoDTO dto, Regiao r) {
        r.setNome(dto.getNome());
        r.setCoordenadas(dto.getCoordenadas());
        r.setDataMapeamento(dto.getDataMapeamento());
        r.setPopulacaoAfetada(dto.getPopulacaoAfetada());
    }
}
