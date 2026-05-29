package luciano_558975.GeoShield.service;

import luciano_558975.GeoShield.dtos.RegiaoDTO;
import luciano_558975.GeoShield.entities.Regiao;
import luciano_558975.GeoShield.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RegiaoService {
    @Autowired
    private RegiaoRepository regRepo;

    public RegiaoDTO createRegiao(RegiaoDTO dto) {
        try {
            Regiao r = new Regiao();
            mapDtoToRegiao(dto, r);
            return new RegiaoDTO(regRepo.save(r));
        } catch (DataIntegrityViolationException e) {
            //TODO MENSAGEM DE ERRO
        }
    }

    private void mapDtoToRegiao(RegiaoDTO dto, Regiao r) {
        r.setNome(dto.getNome());
        r.setCoordenadas(dto.getCoordenadas());
        r.setDataMapeamento(dto.getDataMapeamento());
        r.setPopulacaoAfetada(dto.getPopulacaoAfetada());
    }
}
