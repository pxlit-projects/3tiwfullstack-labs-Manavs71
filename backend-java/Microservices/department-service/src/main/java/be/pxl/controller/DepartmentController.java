package be.pxl.controller;

import be.pxl.Department;
import be.pxl.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Endpoint to add a new department
    @PostMapping()
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department newDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    // Endpoint to find a department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable("id") Long id) {
        Department department = departmentService.findById(id);
        if (department != null) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to find all departments
    @GetMapping()
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }

    // Endpoint to find all departments by organization ID
    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<Department>> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        List<Department> departments = departmentService.findByOrganization(organizationId);
        return ResponseEntity.ok(departments);
    }

    // Endpoint to find all departments by organization ID with their employees
    @GetMapping("/organization/{organizationId}/with-employees")
    public ResponseEntity<List<Department>> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
        List<Department> departments = departmentService.findByOrganizationWithEmployees(organizationId);
        return ResponseEntity.ok(departments);
    }
}