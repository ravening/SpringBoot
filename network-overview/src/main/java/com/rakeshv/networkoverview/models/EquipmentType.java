package com.rakeshv.networkoverview.models;

public enum EquipmentType {
    SWITCH("Switch"),
    ROUTER("Router"),
    SERVER("Server")
    ;

    private final String description;

    EquipmentType(String type) {
        this.description = type;
    }

    public String getDescription() {
        return this.description;
    }
}
