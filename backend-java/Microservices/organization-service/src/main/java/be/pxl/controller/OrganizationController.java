package be.pxl.controller;

import be.pxl.Department;
import be.pxl.Organization;
import be.pxl.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    // GET method to fetch all organizations
    @GetMapping
    public List<Organization> getAllOrganizations() {
        // Logic to fetch organizations from a database or service layer
        return organizationService.findAll();
    }

    // GET method to fetch a specific organization by ID
    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable Long id) {
        // Logic to fetch a specific organization by ID
        return organizationService.findById(id);
    }

    // POST method to create a new organization
    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        // Logic to create a new organization
        Organization newOrganization = organizationService.save(organization);
        return new ResponseEntity<>(newOrganization, HttpStatus.CREATED);

    }

    // PUT method to update an organization by ID
    @PutMapping("/{id}")
    public Organization updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
        // Logic to update an existing organization
        return organizationService.update(id, organization);
    }

    // DELETE method to delete an organization by ID
    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable Long id) {
        // Logic to delete an organization
        organizationService.delete(id);
    }
}
