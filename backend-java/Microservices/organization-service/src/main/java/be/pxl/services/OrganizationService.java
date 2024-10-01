package be.pxl.services;

import be.pxl.Organization;
import be.pxl.repository.OrganizationRepository;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;


    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }


    public Organization findById(Long id) {
      Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElseThrow(() -> new ResourceClosedException("Organization not found with id: " + id));
    }


    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }


    public Organization update(Long id, Organization organization) {
        Organization existingOrganization = findById(id);  // Reusing findById method
        existingOrganization.setName(organization.getName());
        existingOrganization.setAddress(organization.getAddress());
        // Set other fields as needed
        return organizationRepository.save(existingOrganization);
    }


    public void delete(Long id) {
        Organization organization = findById(id);  // Reusing findById method to ensure the org exists
        organizationRepository.delete(organization);
    }
}
