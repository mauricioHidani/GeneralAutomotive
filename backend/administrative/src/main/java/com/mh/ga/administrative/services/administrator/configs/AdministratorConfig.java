package com.mh.ga.administrative.services.administrator.configs;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.repositories.AdministratorRepository;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.repositories.adapter.impls.AdministratorAdapterImpl;
import com.mh.ga.administrative.services.administrator.*;
import com.mh.ga.administrative.services.administrator.impls.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Configuration
public class AdministratorConfig {

    @Bean
    public AdministratorAdapter<Administrator, UUID> administratorAdapter(
            AdministratorRepository repository) {
        return new AdministratorAdapterImpl(repository);
    }

    @Bean
    public FindByIdAdministrator<String, AdministratorResponse> administratorFindById(
            AdministratorAdapter<Administrator, UUID> adapter) {
        return new FindByIdAdministratorImpl(adapter);
    }

    @Bean
    public FindByDocAdministrator<String, AdministratorResponse> administratorFindByDoc(
            AdministratorAdapter<Administrator, UUID> adapter) {
        return new FindByDocAdministratorImpl(adapter);
    }

    @Bean
    public FindByOfficeAdministrator<String, Page<AdministratorResponse>> administratorFindByOffice(
            AdministratorAdapter<Administrator, UUID> adapter) {
        return new FindByOfficeAdministratorImpl(adapter);
    }
    
    @Bean
    public SaveAdministrator<AdministratorRequest, AdministratorResponse> administratorSave(
            AdministratorAdapter<Administrator, UUID> adapter) {
        return new SaveAdministratorImpl(adapter);
    }

    @Bean
    public UpdateAdministrator<String, AdministratorRequest, AdministratorResponse> administratorUpdate(
            AdministratorAdapter<Administrator, UUID> adapter) {
        return new UpdateAdministratorImpl(adapter);
    }

}
