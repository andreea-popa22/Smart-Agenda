package com.example.smartagenda.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ServiceProviderId implements Serializable {
    @Column(name = "service_id", nullable = false)
    private int serviceId;

    @Column(name = "provider_id", nullable = false)
    private int providerId;
}
