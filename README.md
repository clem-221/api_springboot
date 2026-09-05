# API Spring Boot – Gestion d'employés

Petit projet réalisé dans le cadre du cours OpenClassrooms **[Créez une application Java avec Spring Boot](https://openclassrooms.com/fr/courses/6900101-creez-une-application-java-avec-spring-boot)**.

L'objectif était de découvrir Spring Boot de A à Z : création et structuration d'un projet Maven, configuration, écriture d'une API REST avec Spring Data JPA, puis tests unitaires.

Le résultat est une API REST simple permettant de gérer une liste d'employés (créer, lister, consulter, modifier, supprimer), avec une base de données H2 en mémoire.

## Ce que j'ai appris / mis en pratique

- Structurer un projet Spring Boot en couches : `controller`, `service`, `repository`, `model`
- Créer une entité JPA (`@Entity`, `@Table`, `@Column`, `@Id`, `@GeneratedValue`) mappée sur une table H2
- Utiliser `CrudRepository` de Spring Data pour les opérations CRUD sans écrire de SQL
- Exposer une ressource via un `@RestController` avec les annotations `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- Injecter les dépendances par constructeur (Service → Repository, Controller → Service)
- Initialiser une base H2 au démarrage via un script `data.sql`
- Utiliser Lombok (`@Data`) pour réduire le code répétitif (getters/setters)
- Écrire des tests avec `@SpringBootTest`, `@AutoConfigureMockMvc` et `MockMvc` pour vérifier le comportement des endpoints

## Stack technique

- Java 25
- Spring Boot (Spring Web MVC, Spring Data JPA)
- Base de données H2 (en mémoire)
- Lombok
- Maven
- JUnit 5 / MockMvc pour les tests

## Structure du projet

```
src/main/java/com/openclassrooms/api/
├── ApiApplication.java          # Point d'entrée de l'application
├── model/
│   └── Employee.java            # Entité JPA représentant un employé
├── repository/
│   └── EmployeeRepository.java  # Accès aux données (CrudRepository)
├── service/
│   └── EmployeeService.java     # Logique métier
└── controller/
    └── EmployeeController.java  # Endpoints REST

src/main/resources/
├── application.properties       # Configuration (port, H2, logs)
└── data.sql                     # Données d'initialisation

src/test/java/com/openclassrooms/api/
├── ApiApplicationTests.java         # Test de chargement du contexte Spring
└── EmployeeControllerTest.java      # Test du endpoint GET /employees
```

## Modèle de données

Un `Employee` possède les champs suivants :

| Champ       | Type   | Colonne en base |
|-------------|--------|------------------|
| id          | Long   | id               |
| firstName   | String | first_name       |
| lastName    | String | last_name        |
| email       | String | mail             |
| password    | String | password         |

## Endpoints de l'API

| Méthode | URL                  | Description                          |
|---------|----------------------|---------------------------------------|
| GET     | `/employees`         | Récupère la liste de tous les employés |
| GET     | `/employees/{id}`    | Récupère un employé par son id        |
| POST    | `/employees`         | Crée un nouvel employé                |
| PUT     | `/employees/{id}`    | Met à jour un employé existant        |
| DELETE  | `/employees/{id}`    | Supprime un employé                   |

## Lancer le projet

Le projet inclut le wrapper Maven, aucune installation de Maven n'est donc nécessaire.

```bash
git clone https://github.com/clem-221/api_springboot.git
cd api_springboot
./mvnw spring-boot:run
```

L'application démarre sur **http://localhost:9000**.

À chaque démarrage, la base H2 est réinitialisée avec 3 employés d'exemple (voir `data.sql`).

## Console H2

La console H2 est activée, ce qui permet de consulter directement le contenu de la base :

- URL : `http://localhost:9000/h2-console`

## Tester l'API

Avec `curl` :

```bash
# Lister tous les employés
curl http://localhost:9000/employees

# Récupérer un employé par id
curl http://localhost:9000/employees/1

# Créer un employé
curl -X POST http://localhost:9000/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jean","lastName":"Dupont","email":"jean.dupont@mail.com","password":"secret"}'

# Modifier un employé
curl -X PUT http://localhost:9000/employees/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jean","lastName":"Dupont","email":"jean.dupont@mail.com","password":"secret"}'

# Supprimer un employé
curl -X DELETE http://localhost:9000/employees/1
```

## Lancer les tests

```bash
./mvnw test
```

## Pistes d'amélioration

- Ajouter de la validation des champs (`@Valid`, `@NotBlank`...) sur les requêtes entrantes
- Ne pas exposer le mot de passe tel quel dans les réponses JSON (utiliser un DTO)
- Ajouter la gestion des erreurs avec des codes HTTP appropriés (404, 400...) via `@ExceptionHandler`
- Compléter la couverture de tests (POST, PUT, DELETE)

## Ressources

- [Cours OpenClassrooms : Créez une application Java avec Spring Boot](https://openclassrooms.com/fr/courses/6900101-creez-une-application-java-avec-spring-boot)
- [Documentation Spring Boot](https://docs.spring.io/spring-boot/index.html)
