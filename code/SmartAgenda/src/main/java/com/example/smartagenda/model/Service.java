package com.example.smartagenda.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
public class Service {
    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    public Service(int id, String name, String description) {
//        this.serviceId = id;
//        this.name = name;
//        this.description = description;
//    }
}
