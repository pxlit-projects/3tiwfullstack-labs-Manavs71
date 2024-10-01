package be.pxl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="organizations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;

    @OneToMany
    private List<Employee> employees;

    @OneToMany
    private List<Department> departments;

}
