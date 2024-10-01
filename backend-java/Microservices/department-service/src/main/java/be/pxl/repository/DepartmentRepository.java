package be.pxl.repository;

import be.pxl.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    // Find departments by organization ID
    List<Department> findByOrganizationId(Long organizationId);

    // Find departments by organization ID with employees (assuming @OneToMany or @ManyToMany relationship with employees)
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.organizationId = :organizationId")
    List<Department> findByOrganizationIdWithEmployees(Long organizationId);
}