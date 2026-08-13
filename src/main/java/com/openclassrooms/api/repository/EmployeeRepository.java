package com.openclassrooms.api.repository;

import org.springframework.data.repository.CrudRepository;
import com.openclassrooms.api.model.Employee;

/* CrudRepository est une interface générique de Spring Data qui fournit des méthodes pour effectuer des opérations CRUD (Create, Read, Update, Delete) sur une entité. 
 *Elle prend deux paramètres : l'entité sur laquelle elle opère (ici Employee) et le type de l'identifiant de cette entité (ici Long).
 */

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
}
