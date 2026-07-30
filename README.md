☕ QueueEase - Backend

A robust Spring Boot backend powering QueueEase, a smart restaurant management platform developed during VibeAthon 6.0 – Vibecoding Hackathon 2026.

The backend provides secure authentication, RESTful APIs, menu management, queue management, automatic bill generation, and restaurant administration, enabling seamless communication between customers and restaurant staff.

👥 Team Details

Team Name: TeachEra

Team Leader: Aashi (Solo Participant)

Hackathon: VibeAthon 6.0 – Vibecoding Hackathon 2026

🚀 Features
🔐 Authentication
Google OAuth Login
JWT Authentication
Secure API Access
Role-Based Authorization
🍽️ Menu Management
Add Menu Items
Update Menu Items
Delete Menu Items
Live Menu Availability
View Complete Dish Ingredients
⏳ Queue Management
Join Virtual Queue
Queue Status Management
Queue Monitoring
Customer Queue Tracking
🧾 Billing
Automatic Bill Generation
Bill Calculation
Order Summary
👨‍🍳 Restaurant Management
Staff Dashboard APIs
Menu Management APIs
Queue Management APIs
Customer Management
Order Management
🛠️ Tech Stack
Language: Java 17
Framework: Spring Boot
Security: Spring Security + JWT
Database: MySQL
ORM: Spring Data JPA (Hibernate)
Authentication: Google OAuth
Build Tool: Maven
API Style: REST APIs
Deployment: Render
📁 Project Structure
src/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── config/
├── security/
├── exception/
├── util/
└── QueueEaseApplication.java
⚙️ Getting Started
Clone Repository
git clone <backend-repository-url>

cd queueease-backend
Configure Database

Create a MySQL database.

CREATE DATABASE queueease;
Configure Environment Variables

Update application.properties or environment variables.

spring.datasource.url=jdbc:mysql://localhost:3306/queueease
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update

jwt.secret=YOUR_SECRET_KEY

google.client.id=YOUR_GOOGLE_CLIENT_ID
google.client.secret=YOUR_GOOGLE_CLIENT_SECRET
Build Project
mvn clean install
Run Application
mvn spring-boot:run

Application runs on

http://localhost:8080 
Avien cloud database
🌐 Deployment

Backend is deployed using Render.

Live API:

https://queueease-backend-ifx3.onrender.com
📡 REST API Modules
Authentication
Register/Login
Google OAuth
JWT Token
Menu
Get Menu
Add Item
Update Item
Delete Item
Update Availability
View Ingredients
Queue
Join Queue
View Queue
Update Queue Status
Billing
Generate Bill
View Bill
Dashboard
Manage Restaurant Operations
🔄 Backend Workflow
Authenticate User
Validate Request
Process Business Logic
Access MySQL Database
Return JSON Response
Update Restaurant Operations
🗄️ Database

Main entities include:

User
MenuItem
Queue
Order
Bill
🚧 Project Status
✅ Completed
JWT Authentication
Google OAuth
Menu Management
Live Availability
Ingredient Transparency
Queue Management
Automatic Bill Generation
Staff Dashboard APIs
💎 Platinum (Level 5) Status

The current submission successfully completes the Bronze, Silver, and Gold user stories of the VibeAthon 6.0 challenge.

Planned Platinum (AI) Features

The following AI-powered capabilities are planned for future development:

AI-Based Wait Time Prediction
Personalized Dish Recommendations
Smart Inventory Prediction
Demand Forecasting
AI Restaurant Assistant
Intelligent Operational Insights

These AI features are currently under development and are not implemented in this submission.

🤖 AI Usage

This project was developed with assistance from AI tools during the hackathon.

AI Tools Used
ChatGPT (OpenAI)
Claude (Anthropic)
AI was used for
Project planning
System architecture
Backend API design
Debugging
Documentation
Code optimization
Presentation preparation

All AI-assisted suggestions and generated code were reviewed, modified, integrated, and tested by the developer before submission.

🔮 Future Enhancements
AI-Based Analytics
Inventory Forecasting
QR Table Ordering
Payment Gateway Integration
WhatsApp Notifications
Multi-Branch Support
Advanced Reports
Cloud Scalability
📄 License

This project was developed as part of VibeAthon 6.0 – Vibecoding Hackathon 2026.

It is intended for educational and demonstration purposes.

// note login as staff use:- email:-evaluator1@gmail.com password:-evaluator@123
