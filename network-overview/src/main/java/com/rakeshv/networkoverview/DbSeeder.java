package com.rakeshv.networkoverview;

import com.rakeshv.networkoverview.models.Equipment;
import com.rakeshv.networkoverview.models.EquipmentType;
import com.rakeshv.networkoverview.models.Interface;
import com.rakeshv.networkoverview.models.Vlan;
import com.rakeshv.networkoverview.repositories.EquipmentRepository;
import com.rakeshv.networkoverview.repositories.InterfaceRepository;
import com.rakeshv.networkoverview.repositories.VlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@Component
@Slf4j
public class DbSeeder implements CommandLineRunner {
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    InterfaceRepository interfaceRepository;
    @Autowired
    VlanRepository vlanRepository;

    @Override
    public void run(String... args) throws Exception {
        equipmentRepository.deleteAll();
        interfaceRepository.deleteAll();
        vlanRepository.deleteAll();
        // first vlan
        Vlan vlan = Vlan.builder()
                .vlanid(300L).build();
        vlan = vlanFunction.apply(vlan);

        Equipment equipment = Equipment.builder()
                .name("myswitch")
                .ipaddress("22.22.22.22")
                .model("JUNIPER")
                .type(EquipmentType.SWITCH.getDescription()).build();

        // first equipment
        equipment = equipmentFunction.apply(equipment);

        // first interface
        Interface anInterface = Interface.builder()
                .name("ETH11")
                .vlanList(List.of(vlan.getVlanid())).build();
//        anInterface.addVlan(vlan.getVlanid());
        anInterface = interfaceFunction.apply(anInterface);
        log.info("Saved interface: {}", anInterface);

        log.info("Saved vlan: {}", vlan);

        equipmentRepository.createEquipmentRelationship(equipment.getName(), anInterface.getName());
        interfaceRepository.createVlanRelationship(anInterface.getName(), vlan.getVlanid());

        // second equipment
        Equipment equipment1 = Equipment.builder()
                .name("router")
                .ipaddress("33.33.33.33")
                .model("ARISTA")
                .type(EquipmentType.ROUTER.getDescription()).build();
        equipment1 = equipmentFunction.apply(equipment1);

        // second interface
        Interface secondInterface = Interface.builder()
                .name("ETH33")
                .vlanList(List.of(vlan.getVlanid())).build();
        secondInterface = interfaceFunction.apply(secondInterface);
        log.info("Saved second interface: {}", secondInterface);

        equipmentRepository.createEquipmentRelationship(equipment1.getName(), secondInterface.getName());
        interfaceRepository.createVlanRelationship(secondInterface.getName(), vlan.getVlanid());

        // create second interface for each equipment
        Interface thirdInterface = Interface.builder()
                .name("XE11").build();
        thirdInterface = interfaceFunction.apply(thirdInterface);
        Interface fourthInterface = Interface.builder()
                .name("XE22").build();
        fourthInterface = interfaceFunction.apply(fourthInterface);

        equipmentRepository.createEquipmentRelationship(equipment.getName(), thirdInterface.getName());
        equipmentRepository.createEquipmentRelationship(equipment1.getName(), fourthInterface.getName());

        equipment.addInterface(anInterface);
        equipment.addInterface(thirdInterface);

        equipment1.addInterface(secondInterface);
        equipment1.addInterface(fourthInterface);

        log.info("interfaces for equipment are : {}", equipment.getLinks());
        log.info("interfaces for equipment1 are : {}", equipment1.getLinks());
        List<Equipment> equipmentList = Arrays.asList(equipment, equipment1);
        equipmentRepository.saveAll(equipmentList);

        Equipment stored = equipmentRepository.findById(equipment.getId()).get();
        log.info("First equipment is {}", stored);
        stored = equipmentRepository.findById(equipment1.getId()).get();
        log.info("Second equipment is {}", stored);
        List<Interface> interfaceList = Arrays.asList(anInterface, secondInterface, thirdInterface, fourthInterface);
        vlan.addInterface(anInterface);
        vlan.addInterface(secondInterface);
        vlan.addInterface(thirdInterface);
        vlan.addInterface(fourthInterface);
        vlanRepository.save(vlan);
        log.info("First interface : {}, second interface: {}", anInterface.getName(), secondInterface.getName());
        log.info("Thirs interface : {}, fourth interface: {}", thirdInterface.getName(), fourthInterface.getName());
//        interfaceRepository.createVlanRelationship(anInterface.getName(),secondInterface.getName(), vlan.getVlanid());
//        interfaceRepository.createVlanRelationship(thirdInterface.getName(),fourthInterface.getName(), vlan.getVlanid());

        interfaceRepository.createVlanRelationship(thirdInterface.getName(), vlan.getVlanid());
        interfaceRepository.createVlanRelationship(fourthInterface.getName(), vlan.getVlanid());


        Interface fifthInterface = Interface.builder()
                .name("ETH0").build();
        Interface sixthInterface = Interface.builder()
                .name("ETH41").build();



        fifthInterface.addInterface(sixthInterface);

        fifthInterface = interfaceFunction.apply(fifthInterface);
        sixthInterface = interfaceFunction.apply(sixthInterface);

        equipment.addInterface(fifthInterface);
        equipment1.addInterface(sixthInterface);

        equipment = equipmentFunction.apply(equipment);
        equipment1 = equipmentFunction.apply(equipment1);
        equipmentRepository.createEquipmentRelationship(equipment.getName(), fifthInterface.getName());
        equipmentRepository.createEquipmentRelationship(equipment1.getName(), sixthInterface.getName());

//        equipmentRepository.deleteAll();
//        interfaceRepository.deleteAll();
//        vlanRepository.deleteAll();
//
//        buildGraph();
    }

    private void buildGraph() {
        Equipment equipment = Equipment.builder()
                .name("s-1")
                .ipaddress("1.1.1.1").build();
        equipment = equipmentFunction.apply(equipment);

        Equipment equipment1 = Equipment.builder()
                .name("s-2")
                .ipaddress("2.2.2.2").build();
        equipment1 = equipmentFunction.apply(equipment1);

        Interface anInterface = Interface.builder()
                .name("et-1").build();
        anInterface = interfaceFunction.apply(anInterface);

        Interface secondInterface = Interface.builder()
                .name("et-2").build();
        secondInterface = interfaceFunction.apply(secondInterface);

        equipmentRepository.createEquipmentRelationship(equipment.getName(), anInterface.getName());
        equipmentRepository.createEquipmentRelationship(equipment1.getName(), secondInterface.getName());
        IntStream.range(1, 101)
                .forEach(i -> {
                    Vlan vlan = Vlan.builder()
                            .vlanid((long) i).build();
                    vlan = vlanFunction.apply(vlan);
                }
        );

        for (int i=1 ; i< 101; i++) {
            Vlan vlan = vlanRepository.findByVlanid((long) i);
            interfaceRepository.createVlanRelationship(anInterface.getName(), vlan.getVlanid());
            interfaceRepository.createVlanRelationship(secondInterface.getName(), vlan.getVlanid());
        }
        log.info("===graph built successfully===");
    }

    Function<Equipment, Equipment> equipmentFunction = e -> equipmentRepository.save(e);
    Function<Interface, Interface> interfaceFunction = i -> interfaceRepository.save(i);
    Function<Vlan, Vlan> vlanFunction = v -> vlanRepository.save(v);
}
