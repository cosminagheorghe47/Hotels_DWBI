package com.example.Hotels_DWBI.oltp.multisource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/oltp-data")
public class OltpMultiSourceController {

    private final OltpMultiSourceService multiSourceService;

    public OltpMultiSourceController(OltpMultiSourceService multiSourceService) {
        this.multiSourceService = multiSourceService;
    }

    @GetMapping("/sources")
    public List<OltpSourceInfo> getSources() {
        return multiSourceService.listSources();
    }

    @GetMapping("/entities")
    public Map<String, String> getEntities() {
        return multiSourceService.listEntityAliases();
    }

    @GetMapping("/{sourceKey}")
    public ResponseEntity<?> getRows(
            @PathVariable String sourceKey,
            @RequestParam("entity") String entity,
            @RequestParam(value = "limit", defaultValue = "200") int limit
    ) {
        try {
            return ResponseEntity.ok(multiSourceService.fetchRows(sourceKey, entity, limit));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }
}
