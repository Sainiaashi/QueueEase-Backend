☕ QueueEase Backend

QueueEase Backend is a production-ready Spring Boot application powering QueueEase, a smart restaurant management platform built for VibeAthon 6.0 – Vibecoding Hackathon 2026.

It provides secure authentication, role-based authorization, restaurant management APIs, queue management, menu management, billing, and Google OAuth authentication through a clean REST architecture.

👥 Team Information

Team Name: TeachEra

Participant: Aashi (Solo Participant)

Hackathon: VibeAthon 6.0 – Vibecoding Hackathon 2026

🚀 Features
🔐 Authentication
JWT Authentication
Google OAuth Login
Email OTP Verification
Secure REST APIs
Role-Based Authorization (Customer / Staff / Admin)
🍽️ Menu Management
Add Menu Items
Update Menu Items
Delete Menu Items
Toggle Item Availability
Live Menu Display
Ingredient Transparency
⏳ Queue Management
Join Virtual Queue
Live Queue Tracking
Queue Position Updates
Estimated Waiting Time
Staff Queue Management
🧾 Order & Billing
Place Customer Orders
Automatic Bill Generation
Bill Summary
Order Tracking
👨‍🍳 Restaurant Administration
Dashboard APIs
Customer Management
Queue Monitoring
Menu Management
Restaurant Operations
🛠️ Tech Stack
Category	Technology
Language	Java 17
Framework	Spring Boot
Security	Spring Security + JWT
Authentication	Google OAuth 2.0
Database	MySQL (Aiven Cloud)
ORM	Spring Data JPA (Hibernate)
Build Tool	Maven
API Style	REST APIs
Deployment	Render
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
⚙️ Local Development
Clone Repository
git clone <backend-repository-url>

cd queueease-backend
Configure Database

Create a MySQL database.

CREATE DATABASE queueease;
Configure Environment Variables
spring.datasource.url=jdbc:mysql://localhost:3306/queueease
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update

jwt.secret=YOUR_SECRET_KEY

google.client.id=YOUR_GOOGLE_CLIENT_ID
google.client.secret=YOUR_GOOGLE_CLIENT_SECRET
Build Project
mvn clean install
Run Project
mvn spring-boot:run

Backend runs at

http://localhost:8080
🌐 Deployment
Backend

Render

https://queueease-backend-ifx3.onrender.com
Database

Aiven Cloud MySQL

📡 REST API Modules
Authentication
Register
Verify OTP
Login
Google OAuth
JWT Authentication
Menu
Get Available Menu
Get Recommendations
Add Menu Item
Update Menu Item
Delete Menu Item
Toggle Availability
View Ingredients
Queue
Join Queue
Queue Status
Update Queue
Customer Queue Tracking
Orders
Place Order
Update Order
Customer Orders
Billing
Generate Bill
View Bill
Dashboard
Restaurant Overview
Staff Operations
🔄 Backend Workflow
Client Request
      │
      ▼
Spring Security
      │
      ▼
JWT Authentication
      │
      ▼
Controller
      │
      ▼
Service Layer
      │
      ▼
Repository
      │
      ▼
MySQL Database
      │
      ▼
JSON Response
🗄️ Core Entities
User
MenuItem
Queue
Order
Bill
👨‍💻 Demo Staff Account

Use the following credentials to explore staff features.

Email

evaluator1@gmail.com

Password

evaluator@123
🚧 Current Status
✅ Completed
JWT Authentication
Email OTP Verification
Google OAuth
Menu Management
Queue Management
Customer Ordering
Automatic Billing
Staff Dashboard APIs
Live Menu Availability
💎 Hackathon Progress

This submission successfully completes the Bronze, Silver, and Gold milestones of the VibeAthon 6.0 challenge.

Planned AI Features
AI Wait Time Prediction
Personalized Food Recommendations
Inventory Forecasting
Demand Prediction
AI Restaurant Assistant
Operational Insights

These AI capabilities are planned for future development and are not included in the current submission.

🤖 AI Assistance

This project was developed with assistance from AI tools during the hackathon.

AI Tools
ChatGPT (OpenAI)
Claude (Anthropic)
AI was used for
System Design
Architecture Planning
Backend API Design
Debugging
Documentation
Code Refactoring
Presentation Preparation

All AI-assisted suggestions were reviewed, modified, integrated, and tested before being included in the project.

🔮 Future Enhancements
QR Code Table Ordering
Payment Gateway Integration
WhatsApp Notifications
Multi-Branch Restaurant Support
AI Analytics
Inventory Management
Cloud Auto Scaling
Advanced Reports & Insights
📄 License

This project was developed as part of VibeAthon 6.0 – Vibecoding Hackathon 2026 for educational and demonstration purposes.
