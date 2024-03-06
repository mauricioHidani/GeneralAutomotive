package com.mh.ga.administrative.services.administrator.configs;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.repositories.AdministratorRepository;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.repositories.adapter.impls.AdministratorAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AdministratorConfig {

    @Bean
    public AdministratorAdapter<Administrator, UUID> adapter(
            AdministratorRepository repository) {
        return new AdministratorAdapterImpl(repository);
    }

}
