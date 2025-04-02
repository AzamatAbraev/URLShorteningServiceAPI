# URL Shortening Service

## Overview
This is a RESTful API for a URL shortening service built using **Java Spring Boot** and **PostgreSQL**. The API allows users to shorten long URLs, retrieve original URLs from shortened ones, update and delete shortened URLs, and fetch access statistics.

## Features
- Shorten long URLs into unique short codes
- Retrieve the original URL from a short code
- Update an existing shortened URL
- Delete a shortened URL
- Retrieve access statistics for shortened URLs

## Tech Stack
- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Version Control:** Git

## Setup and Installation
### Prerequisites
- Java 17+
- PostgreSQL installed and running
- Maven

### Installation Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/AzamatAbraev/URLShorteningServiceAPI.git
   cd URLShorteningServiceAPI
   ```
2. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortener
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```
4. The API will be accessible at `http://localhost:8080`

## License
This project is open-source and available under the MIT License.

