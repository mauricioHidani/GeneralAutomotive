package com.mh.ga.administrative.services.administrator;

import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministratorService {

    private final FindByIdAdministrator<String, AdministratorResponse> findById;
    private final SaveAdministrator<AdministratorRequest, AdministratorResponse> save;

    public AdministratorService(
            FindByIdAdministrator<String, AdministratorResponse> findById,
            SaveAdministrator<AdministratorRequest, AdministratorResponse> save) {
        this.findById = findById;
        this.save = save;
    }

    @Transactional(readOnly = true)
    public AdministratorResponse findById(String id) {
        return findById.execute(id);
    }

    @Transactional
    public AdministratorResponse save(AdministratorRequest request) {
        return save.execute(request);
    }

}
