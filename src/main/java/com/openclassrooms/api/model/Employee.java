package com.openclassrooms.api.model;

//les annotations JPA pour la persistance des données : javax.persistence.* passe à jakarta.persistence.* depuis la version 3.0 de Spring Boot
import jakarta.persistence.Entity; //annotation JPA qui indique que cette classe est une entité persistante
import jakarta.persistence.GeneratedValue; //annotation JPA qui indique que la valeur de l'identifiant sera générée automatiquement
import jakarta.persistence.GenerationType; //annotation JPA qui indique la stratégie de génération de l'identifiant
import jakarta.persistence.Id; //annotation JPA qui indique que l'attribut est l'identifiant de l'entité
import jakarta.persistence.Table; //annotation JPA qui indique le nom de la table dans la base de données
import jakarta.persistence.Column; //annotation JPA qui indique le nom de la colonne dans la table de la base de données

import lombok.Data;

@Entity //annotation qui indique que la classe correspond à une table de la base de données.
@Table(name = "employees") //nom de la table associée à la base de données.
@Data //annotation de Lombok qui génère automatiquement les getters, setters, equals, hashCode et toString 

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //annotation JPA qui indique que l'identifiant sera généré automatiquement par la base de données
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mail")
    private String email;

    @Column(name = "password")
    private String password;
}
