package com.aw.train.guiderest.hateoas;

import com.aw.train.guiderest.entity.Employee;
import com.aw.train.guiderest.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class EmployeeModelAssemblerTest {

    @Test
    void shouldCreateEmployeeEntityModelWithLinks() {
        //given
        final EmployeeModelAssembler assembler = new EmployeeModelAssembler();
        final Employee employee = new Employee("John", "Doe", Role.MAGE);
        employee.setId(1L);

        //when
        final EntityModel<Employee> employeeEntityModel = assembler.toModel(employee);

        //then
        assertThat(employeeEntityModel.getLinks().toList()).hasSize(2);

        Link link = employeeEntityModel.getLink(LinkRelation.of("employees"))
                .orElseThrow(() -> new RuntimeException("No link found"));
        assertThat(link.getRel()).isEqualTo(LinkRelation.of("employees"));
        assertThat(link.getHref()).isEqualTo("/employees");

        link = employeeEntityModel.getLink(IanaLinkRelations.SELF)
                .orElseThrow(() -> new RuntimeException("No self link found"));
        assertThat(link.getRel()).isEqualTo(IanaLinkRelations.SELF);
        assertThat(link.getHref()).isEqualTo("/employees/" + employee.getId());
    }
}