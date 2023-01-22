package com.example.smartagenda.model;

import com.example.smartagenda.model.embeddable.ServiceProviderId;
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
@Table(name = "service_provider")
public class ServiceProvider {
    @EmbeddedId
    ServiceProviderId serviceProviderId;

    @Column(name = "price")
    private float price;

    @Column(name = "duration")
    private float duration;

    @Column(name = "description")
    private String description;
}
