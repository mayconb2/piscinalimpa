package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.TrelloCardSugestionForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class TrelloService {

    @Value("${trelloKey}")
    private String trelloKey;

    @Value("${trelloToken}")
    private String trelloToken;

    @Value("${trelloIdBoard")
    private String trelloIdBoard;

    @Value("${trelloIdList}")
    private String trelloIdList;

    private final RestTemplate restTemplate;

    private final AtomicReference<String> trelloBaseUrl = new AtomicReference<>("https://api.trello.com/1/cards");

    @Autowired
    public TrelloService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String createNewCardSuggestion(TrelloCardSugestionForm form) {

        TrelloCardSugestionForm dto = new TrelloCardSugestionForm();
        dto.setName("Quem sugeriu: " + form.getName());
        dto.setDesc("Nome e Marca do produto: " + form.getDesc());

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(trelloBaseUrl.get())
                .queryParam("key",trelloKey)
                .queryParam("token", trelloToken)
                .queryParam("idList", trelloIdList)
                .queryParam("name", URLEncoder.encode(dto.getName(),StandardCharsets.UTF_8))
                .queryParam("desc", URLEncoder.encode(dto.getDesc(),StandardCharsets.UTF_8))
                .queryParam("pos", dto.getPos());

        ResponseEntity<Object> exchange = restTemplate
                .exchange(builder.build(true).toUri(), HttpMethod.POST, new HttpEntity<>(null), Object.class);

        return exchange.getStatusCode().getReasonPhrase();

    }

}
