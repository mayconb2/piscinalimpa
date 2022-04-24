package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.entity.InterventionLevel;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.repository.InterventionLevelRepository;
import org.springframework.stereotype.Service;

@Service
public class InterventionLevelService {

    private final InterventionLevelRepository interventionLevelRepository;

    public InterventionLevelService(InterventionLevelRepository interventionLevelRepository) {
        this.interventionLevelRepository = interventionLevelRepository;
    }

    public InterventionLevel findById(Long id) {
        return this.interventionLevelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível achar o nível de intervenção com id: " + id));
    }
}
