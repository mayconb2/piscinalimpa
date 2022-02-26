package br.com.unicesumar.piscinalimpa.controller;

import br.com.unicesumar.piscinalimpa.dto.TrelloCardSugestionForm;
import br.com.unicesumar.piscinalimpa.service.TrelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/trello")
@Slf4j
public class TrelloController {

    @Value("${trelloKey}")
    private String trelloKey;

    @Value("${trelloToken}")
    private String trelloToken;

    @Value("${trelloIdList}")
    private String trelloIdList;

    private final TrelloService trelloService;

    public TrelloController(TrelloService trelloService) {
        this.trelloService = trelloService;
    }

    @PostMapping
    public ResponseEntity<String> createCardSuggestion(@RequestBody TrelloCardSugestionForm form){

        try {
            String newCardSuggestionResponse = trelloService.createNewCardSuggestion(form);

            if (newCardSuggestionResponse.equalsIgnoreCase("OK")) {
                return ResponseEntity.ok().body("Sugestão registrada com sucesso!");
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível registrar sua sugestão. " +
                    "Por favor tente mais tarde.");

        } catch (Exception e) {
            log.error("Erro ao registrar novo produto no trello: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível registrar sua sugestão. " +
                    "Por favor tente mais tarde.");
        }

    }

}
