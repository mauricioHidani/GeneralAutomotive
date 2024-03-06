package com.mh.ga.administrative.services.administrator;

import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministratorService {

    private final SaveAdministrator<AdministratorRequest, AdministratorResponse> save;

    public AdministratorService(
            SaveAdministrator<AdministratorRequest, AdministratorResponse> save) {
        this.save = save;
    }

    @Transactional
    public AdministratorResponse save(AdministratorRequest request) {
        return save.execute(request);
    }

}
