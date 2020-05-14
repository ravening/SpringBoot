package com.rakeshv.networkoverview.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakeshv.networkoverview.models.Equipment;
import com.rakeshv.networkoverview.models.Interface;
import com.rakeshv.networkoverview.repositories.EquipmentRepository;
import com.rakeshv.networkoverview.repositories.InterfaceRepository;
import com.rakeshv.networkoverview.repositories.VlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GraphService {
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    InterfaceRepository interfaceRepository;
    @Autowired
    VlanRepository vlanRepository;

    private Map<String, Object> toD3Format(Collection<Equipment> equipments) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;
        Iterator<Equipment> result = equipments.iterator();
        while (result.hasNext()) {
            Equipment equipment = result.next();
            nodes.add(map("name", equipment.getName(), "label", "equipment"));
            int target = i;
            i++;
            for (String linkstring : equipment.getLinks()) {
                Interface link = null;
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    link = mapper.readValue(linkstring, Interface.class);
                } catch (Exception e) {
                    log.error("Exception: {}", e.getMessage());
                    throw new RuntimeException("Jackson parse failed");
                }
                Map<String, Object> port = map("name", link.getName(), "label", "interface");
                int source = nodes.indexOf(port);
                if (source == -1) {
                    nodes.add(port);
                    source = i++;
                }
                rels.add(map("source", source, "target", target));
            }
        }
        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> graph() {
        Collection<Equipment> equipmentGraph = equipmentRepository.graph(1000);
        return toD3Format(equipmentGraph);
    }
}
