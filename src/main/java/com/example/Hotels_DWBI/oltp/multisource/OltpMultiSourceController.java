package com.example.Hotels_DWBI.oltp.multisource;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Trebuie să fie o rută literală, nu sub /{sourceKey}, altfel „schema” e tratat ca sourceKey
     * și lipsește query {@code entity} → HTTP 400.
     */
    @GetMapping("/schema")
    public Map<String, Object> getCrudSchema() {
        return multiSourceService.getCrudSchema();
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
        } catch (DataAccessException ex) {
            return dataAccessError(ex);
        }
    }

    @PostMapping("/{sourceKey}/{entity}")
    public ResponseEntity<?> createRow(
            @PathVariable String sourceKey,
            @PathVariable String entity,
            @RequestBody Map<String, Object> body
    ) {
        try {
            int rowsAffected = multiSourceService.createRow(sourceKey, entity, body);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("rowsAffected", rowsAffected));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        } catch (DataAccessException ex) {
            return dataAccessError(ex);
        }
    }

    @PutMapping("/{sourceKey}/{entity}/{id}")
    public ResponseEntity<?> updateRow(
            @PathVariable String sourceKey,
            @PathVariable String entity,
            @PathVariable String id,
            @RequestBody Map<String, Object> body
    ) {
        try {
            int rowsAffected = multiSourceService.updateRow(sourceKey, entity, id, body);
            return ResponseEntity.ok(Map.of("rowsAffected", rowsAffected));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        } catch (DataAccessException ex) {
            return dataAccessError(ex);
        }
    }

    @DeleteMapping("/{sourceKey}/{entity}/{id}")
    public ResponseEntity<?> deleteRow(
            @PathVariable String sourceKey,
            @PathVariable String entity,
            @PathVariable String id
    ) {
        try {
            int rowsAffected = multiSourceService.deleteRow(sourceKey, entity, id);
            return ResponseEntity.ok(Map.of("rowsAffected", rowsAffected));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        } catch (DataAccessException ex) {
            return dataAccessError(ex);
        }
    }

    private ResponseEntity<Map<String, String>> dataAccessError(DataAccessException ex) {
        Throwable cause = ex.getMostSpecificCause();
        String message = cause != null ? cause.getMessage() : ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", message));
    }
}
