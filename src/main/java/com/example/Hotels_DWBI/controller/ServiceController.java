package com.example.Hotels_DWBI.controller;

import com.example.Hotels_DWBI.model.Service;
import com.example.Hotels_DWBI.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Service> getAll() {
        return serviceService.findAll();
    }

    @GetMapping("/{id}")
    public Service getById(@PathVariable Integer id) {
        return serviceService.findById(id);
    }

    @PostMapping
    public Service create(@RequestBody Service service) {
        return serviceService.save(service);
    }

    @PutMapping("/{id}")
    public Service update(@PathVariable Integer id, @RequestBody Service service) {
        return serviceService.update(id, service);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        serviceService.delete(id);
    }
}
