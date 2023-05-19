# RecipeBook
Web application to view and add recipes


## Environment:
- Java version: 17
- Maven version: 3.8.8
- Spring Boot version: 3.0.6
- Mendix Studio Pro: 9.24.2

## Server-Side:
- Spring Boot framework, java an h2 database
- Running on port 8081 -> http://localhost:8081
- Access h2 database : http://localhost:8081/h2-console , user : sa and password = password, url=jdbc:h2:mem:testdb

- To start server, use below commands:
    - clone this git repository and execute below commands
    # Run : 
    mvn spring-boot:run (Install maven and navigate to project folder)

    # Test :
    mvn clean test

# UI
- Developed using Mendix studio
- Running on port 8080 -> http://localhost:8080

# Getting started:
- Ensure that both the server-side and UI side are up and running and are accessible.

# API Endpoints:

Recipe Book provides the following API endpoints for interacting with the server-side:

GET /recipes: Retrieves all recipes. Supports filtering using name and category.
GET /recipes/{id}: Retrieves a specific recipe by its ID.
POST /recipes: Adds a new recipe.
GET /recipes/categories/{id}: Retrieves recipes belonging to a specific category by its ID.
GET /categories: Retrieves all categories.
GET /categories/{id}: Retrieves a specific category by its ID.
GET /categories/{id}/recipes: Retrieves recipes belonging to a specific category by its ID.

## Data:
Sample example of Recipe data object:
```Recipe
{                
    "name": "Butter milk 99",
    "yield": "2",
    "categories": [
        {
            name": "Main dish2"
        }                     
    ],
    "recipeIngredients": [
        {
            "qty": "1",
            "unit": "can",
            "item": "curd"
        }
    ],
    "recipeDirectons": [
        {                        
            "stepId": 1,
            "step": "To make buttermilk, simply measure the vinegar or lemon juice into a liquid measuring cup. Fill the cup with milk up to the 1-cup line (so yes, you’ll be using just a tiny bit less than 1 full cup milk). Stir to combine, and let the mixture rest for at least 5 to 10 minutes before using."
        }

    ]
                
}