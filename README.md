
# Online Housing Showcase

## Description
The Online Housing Showcase project is designed to facilitate the management and majestically display of housing listings. 
It provides functionality for both owners and visitors to interact with housing data through private and public APIs.


### Key Features

 **Owner Account Management:**
Save owner account with required fields using FormUrlEncoded value[^1]

**Housing Management**
Save and edit housing with required fields by owner using JSON value.[^2].


**Private API for Owners**
```
Upon login, owners can view their housing list with Pagination.
Filter housing by housingName, floors, masterRoom, singleRoom, amount, or postedDate (createdDate) using token-based authentication.
```

**Public API for Visitors**
```
Visitors can view all housing listings with Pagination.
Search housing by housingName, floors, masterRoom, singleRoom, amount, or postedDate (createdDate).
```

## Backend Technologies Enforcements
- **Spring REST API** - Framework for building the backend API
- **MyBatis** - Persistence framework for interacting with the database
- **PostgreSQL** - Relational database

## API Endpoints

### Private API (Owner Access)
 [!IMPORTANT]
**GET /api/housings/: Retrieve all housings with pagination and filtering options.**[^1]
**POST /api/housings/: Create a new housing listing.**[^2]
**PUT /api/housings/{id}: Update an existing housing listing.**[^3]
**DELETE /api/housings/{id}: Delete a housing listing.**[^4]

### Public API (Visitor Access)
**GET /api/housings/: Retrieve all housings with pagination and search options.**

[!TIP]
### Setup Instructions

**Clone the Repository**
```
git clone https://github.com/your-username/online-housing-showcase.git
cd online-housing-showcase
```
**Database Setup**
[!IMPORTANT]
#0969DA Ensure PostgreSQL is installed and running.
Create a database named housing_db.

**Application Configuration**
#0969DA Open src/main/resources/application.properties and configure your database connection.

**Run The Application**
```./mvnw spring-boot:run```

### Accessing APIs
[!NOTE]
**Use tools like Postman to interact with the APIs:
Public API: http://localhost:8080/api/housings/?page=1&size=10&housingName=Brimming%20Ground%20Field&floors=12
Private API: Authenticate first and use tokens to access owner-specific endpoints.**


### Contributing

## Fork the Repository:
**We welcome contributions to enhance the project! Follow these steps to contribute:
Click the "Fork" button at the top right of the repository page.**

## Create a New Branch:
```git checkout -b feature/your-feature```

## Make Your Changes:
Implement your feature or bug fix.

## Commit Your Changes:
```git commit -m "Add feature: your feature description"```

## Push To The Branch
```git push origin feature/your-feature```

## Create a Pull Request:
Open a pull request on GitHub and describe your changes.


### Acknowledgements
🙏
Special thanks to the open-source community for the tools and libraries used in this project






