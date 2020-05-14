package com.rakeshv.networkoverview.services;

import com.rakeshv.networkoverview.models.Equipment;
import com.rakeshv.networkoverview.repositories.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;

    @Transactional
    public Equipment findEquipmentByName(String name) {
        return equipmentRepository.findByNameEqualsIgnoreCase(name);
    }

    @Transactional
    public List<Equipment> findEquipmentByNameLike(String name) {
        return equipmentRepository.findByNameLike(name);
    }

    @Transactional
    public Iterable<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment editEquipment(Equipment equipment) throws Exception {
        Optional<Equipment> existingEquipment = equipmentRepository.findById(equipment.getId());
        Equipment equipment1 = existingEquipment.orElseThrow(() ->
                new Exception("Unable to find equipment"));
        equipment1.setInterfaces(equipment.getInterfaces());
        equipment1.setIpaddress(equipment.getIpaddress());
        equipment1.setModel(equipment.getModel());
        equipment1.setName(equipment.getName());
        equipment1.setType(equipment.getType());

        equipmentRepository.save(equipment1);
        return equipment1;
    }

    public void deleteEquipment(String name) {
        Optional<Equipment> equipmentOptional = Optional.ofNullable(equipmentRepository.findByNameEqualsIgnoreCase(name));
        Equipment equipment = equipmentOptional.orElseThrow(() ->
                new RuntimeException("Unable to find equipment with name " + name));

        equipmentRepository.delete(equipment);
    }
}
