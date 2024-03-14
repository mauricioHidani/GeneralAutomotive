package com.mh.ga.administrative.services.administrator;

import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministratorService {

    private final FindByIdAdministrator<String, AdministratorResponse> findById;
    private final FindByDocAdministrator<String, AdministratorResponse> findByDoc;
    private final FindByOfficeAdministrator<String, Page<AdministratorResponse>> findByOffice;
    private final SaveAdministrator<AdministratorRequest, AdministratorResponse> save;

    public AdministratorService(
            FindByIdAdministrator<String, AdministratorResponse> findById,
            FindByDocAdministrator<String, AdministratorResponse> findByDoc,
            FindByOfficeAdministrator<String, Page<AdministratorResponse>> findByOffice,
            SaveAdministrator<AdministratorRequest, AdministratorResponse> save) {
        this.findById = findById;
        this.findByDoc = findByDoc;
        this.findByOffice = findByOffice;
        this.save = save;
    }

    @Transactional(readOnly = true)
    public AdministratorResponse findById(String id) {
        return findById.execute(id);
    }

    @Transactional(readOnly = true)
    public AdministratorResponse findByDoc(String document) {
        return findByDoc.execute(document);
    }

    @Transactional(readOnly = true)
    public Page<AdministratorResponse> findByOffice(String officeName, Pageable pageable) {
        return findByOffice.execute(officeName, pageable);
    }

    @Transactional
    public AdministratorResponse save(AdministratorRequest request) {
        return save.execute(request);
    }

}
