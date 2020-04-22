package com.abeldevelop.petclinic.services.vets.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "vets")
public class VetsProperties {

    private Cache cache;

    @Data
    public static class Cache {

        private int ttl;

        private int heapSize;
    }
}