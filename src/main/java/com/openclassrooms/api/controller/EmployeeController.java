package com.openclassrooms.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.api.service.EmployeeService;
import com.openclassrooms.api.model.Employee;

/**
 * Contrôleur REST pour gérer les opérations liées aux employés. 
 * @RestController est une combinaison de @Controller et @ResponseBody. Elle permet à Spring de savoir que cette classe est un bean.
 * Deux missions principales :
 * 1. Gérer les requêtes HTTP entrantes et les mapper aux méthodes appropriées
 * 2. Convertir les objets Java en JSON pour les réponses HTTP 
 * Les applications qui vont communiquer avec l'API accéderont au résultat de leur requête en parsant la réponse HTTP.
 */
@RestController 

public class EmployeeController {
    
    /**
     * Service pour gérer les opérations liées aux employés
     * équivaut à l'annotation @Autowired, mais permet d'injecter la dépendance via le constructeur
     */
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Appelle la méthode getEmployees() du service, ce dernier appellera la méthode findAll() du repository, 
     * et nous obtiendrons ainsi tous les employés enregistrés en base de données.
     * 
     * @return un objet Iterable de tous les employés, qui sera automatiquement converti en JSON par Spring Boot grâce à l'annotation @RestController.
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
        .orElseThrow(() -> new IllegalArgumentException("Employé introuvable avec l'ID : " + id));
    }
}
