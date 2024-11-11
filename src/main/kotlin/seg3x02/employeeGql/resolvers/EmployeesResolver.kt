package seg3x02.employeeGql.resolvers

import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository

@Controller
class EmployeesResolver(
    private val employeeRepository: EmployeesRepository
) {
    private val logger = LoggerFactory.getLogger(EmployeesResolver::class.java)

    @QueryMapping
    fun employees(): List<Employee> {
        val employees = employeeRepository.findAll()
        logger.info("Fetched employees: $employees")
        return employees
    }

    @MutationMapping
    fun newEmployee(
        @Argument name: String,
        @Argument dateOfBirth: String,
        @Argument city: String,
        @Argument salary: Float,
        @Argument gender: String?,
        @Argument email: String?
    ): Employee {
        val newEmployee = Employee(
            name = name,
            dateOfBirth = dateOfBirth,
            city = city,
            salary = salary,
            gender = gender,
            email = email
        )
        val savedEmployee = employeeRepository.save(newEmployee)
        logger.info("Added new employee: $savedEmployee")
        return savedEmployee
    }
}
