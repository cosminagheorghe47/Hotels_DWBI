package com.example.Hotels_DWBI.service;

import com.example.Hotels_DWBI.model.Service;
import com.example.Hotels_DWBI.repository.ServiceRepository;


import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    public Service findById(Integer id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }

    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    public Service update(Integer id, Service updated) {
        Service existing = findById(id);

        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setUnitPrice(updated.getUnitPrice());
        existing.setCurrency(updated.getCurrency());
        existing.setIsActive(updated.getIsActive());

        return serviceRepository.save(existing);
    }

    public void delete(Integer id) {
        serviceRepository.deleteById(id);
    }
}
