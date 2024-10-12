package be.pxl;

import be.pxl.repository.DepartmentRepository;
import be.pxl.repository.OrganizationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OrganizationServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
public class OrganizationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Container
    private static MySQLContainer sqlContainer = new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @Test
    public void testCreateOrganization() throws Exception {
        Organization organization = Organization.builder().name("IT").build();

        String organizationJson = objectMapper.writeValueAsString(organization);

        mockMvc.perform(MockMvcRequestBuilders.post("/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(organizationJson))
                .andExpect(status().isCreated());

        // Verify the department was saved
        List<Organization> organizations = organizationRepository.findAll();
        assertEquals(1, organizations.size());
        assertEquals("IT", organizations.get(0).getName());


    }


}
