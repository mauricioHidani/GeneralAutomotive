package com.mh.ga.administrative.services.administrator.configs;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.repositories.AdministratorRepository;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.repositories.adapter.impls.AdministratorAdapterImpl;
import com.mh.ga.administrative.services.administrator.FindByDocAdministrator;
import com.mh.ga.administrative.services.administrator.FindByIdAdministrator;
import com.mh.ga.administrative.services.administrator.SaveAdministrator;
import com.mh.ga.administrative.services.administrator.impls.FindByDocAdministratorImpl;
import com.mh.ga.administrative.services.administrator.impls.FindByIdAdministratorImpl;
import com.mh.ga.administrative.services.administrator.impls.SaveAdministratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AdministratorConfig {

    @Bean public AdministratorAdapter<Administrator, UUID> adapter(
            AdministratorRepository repository) {
        return new AdministratorAdapterImpl(repository);
    }

    @Bean public FindByIdAdministrator<String, AdministratorResponse> findById(
            AdministratorAdapter<Administrator, UUID> adapter) {
        return new FindByIdAdministratorImpl(adapter);
    }

    @Bean public FindByDocAdministrator<String, AdministratorResponse> findByDoc(
            AdministratorAdapter<Administrator, UUID> adapter) {
        return new FindByDocAdministratorImpl(adapter);
    }

    @Bean public SaveAdministrator<AdministratorRequest, AdministratorResponse> save(
            AdministratorAdapter<Administrator, UUID> adapter) {
        return new SaveAdministratorImpl(adapter);
    }

}
