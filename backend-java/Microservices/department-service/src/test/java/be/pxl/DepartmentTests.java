package be.pxl;

import be.pxl.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = DepartmentServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
public class DepartmentTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Container
    private static MySQLContainer sqlContainer = new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }
    @Test
    public void testCreateDepartment() throws Exception {
        Department department = Department.builder().name("HR").build();

        String departmentJson = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentJson))
                .andExpect(status().isCreated());

        // Verify the department was saved
        List<Department> departments = departmentRepository.findAll();
        assertEquals(1, departments.size());
        assertEquals("HR", departments.get(0).getName());


    }

    @Test
    public void testFindDepartmentById() throws Exception {

        Department department = Department.builder().name("HR").build();

        Department savedDepartment = departmentRepository.save(department);


        mockMvc.perform(MockMvcRequestBuilders.get("/department/{id}", savedDepartment.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllDepartment() throws Exception {
        Department department1 = Department.builder().name("HR").build();
        Department department2 = Department.builder().name("IT").build();

        departmentRepository.save(department1);
        departmentRepository.save(department2);

        // Perform the GET request to fetch all employees
        mockMvc.perform(MockMvcRequestBuilders.get("/department")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(2, departmentRepository.findAll().size());
    }



}
