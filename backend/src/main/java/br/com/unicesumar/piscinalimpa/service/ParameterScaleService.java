package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.entity.ParameterScale;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.repository.ParameterScaleRepository;
import org.springframework.stereotype.Service;

@Service
public class ParameterScaleService {

    private final ParameterScaleRepository parameterScaleRepository;

    public ParameterScaleService(ParameterScaleRepository parameterScaleRepository) {
        this.parameterScaleRepository = parameterScaleRepository;
    }

    public ParameterScale findByParameterIdAndValue(Long parameterId, Integer value) {
        return this.parameterScaleRepository.findByParameterIdAndValue(parameterId, value)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar escala de parâmetro"));
    }
}
