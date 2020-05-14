package com.rakeshv.networkoverview.controllers;

import com.rakeshv.networkoverview.models.Equipment;
import com.rakeshv.networkoverview.services.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@Slf4j
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/listall")
    public List<Equipment> getAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        Iterable<Equipment> equipment = equipmentService.getAllEquipments();
        equipment.forEach(equipmentList::add);
        return equipmentList;
    }

    @GetMapping("/equipment/{name}")
    public Equipment getEquipment(@PathVariable("name") String name) {
        return equipmentService.findEquipmentByName(name);
    }

    @GetMapping("/equipments/{name}")
    public List<Equipment> getEquipments(@PathVariable("name") String name) {
        return equipmentService.findEquipmentByNameLike(name);
    }

}
