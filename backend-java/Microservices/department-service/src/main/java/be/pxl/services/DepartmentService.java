package be.pxl.services;

import be.pxl.Department;
import be.pxl.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    public Department addDepartment(Department department) {
        // Save the department to the repository
        return departmentRepository.save(department);
    }

    // Find a department by ID
    public Department findById(Long id) {
        // Find the department by its ID, return null if not found
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElse(null);
    }

    // Find all departments
    public List<Department> findAll() {
        // Find all departments in the repository
        return departmentRepository.findAll();
    }

    // Find departments by organization ID
    public List<Department> findByOrganization(Long organizationId) {
        // Custom query to find departments by organization ID
        return departmentRepository.findByOrganizationId(organizationId);
    }

    // Find departments by organization ID with employees
    public List<Department> findByOrganizationWithEmployees(Long organizationId) {
        // Custom query to find departments by organization ID, eager fetch with employees
        return departmentRepository.findByOrganizationIdWithEmployees(organizationId);
    }
}
