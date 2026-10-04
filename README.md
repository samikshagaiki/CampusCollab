# CampusCollab

CampusCollab is a student collaboration platform designed to help students find and work with other students based on their skills, learning goals, and project requirements.

The platform provides structured workflows for creating collaboration opportunities, discovering required skills, applying to projects, and forming teams.

> 🚧 CampusCollab is currently under active development. Additional features such as skill-based matching, automated testing, Docker deployment, CI/CD, AWS deployment, and the Next.js frontend are planned for upcoming versions.

---

## 🎯 Problem Statement

Students often have useful skills but struggle to find the right people for projects, learning, or technical collaboration.

For example:

- A student knows Java but needs someone good at React.
- A student wants to learn Spring Boot and needs guidance.
- A project requires Java, PostgreSQL, and React developers.
- Students have difficulty finding suitable teammates beyond their existing friend groups.

CampusCollab aims to structure these interactions into a single platform where students can:

**Offer skills → Find opportunities → Apply → Form teams → Collaborate**

---

## 🚀 Current Features

### 🔐 Authentication & Security

- User registration and login
- JWT-based authentication
- Spring Security
- BCrypt password hashing
- Role-based user management
- Student and Admin roles

### 👤 Student Profiles

Students can create and manage profiles containing:

- Bio
- Branch
- Graduation year
- Interests
- Availability

### 🛠️ Skill Management

Students can maintain:

- Skills they offer
- Skills they want to learn
- Proficiency levels

Skills are stored separately and linked to users through a relational association.

### 📌 Collaboration Opportunities

Students can create project opportunities containing:

- Project title
- Description
- Required team size
- Opportunity status
- Required skills
- Opportunity owner

Supported opportunity statuses include:

- OPEN
- IN_PROGRESS
- COMPLETED
- CANCELLED

### 📝 Project Applications

Students can:

- Apply to collaboration opportunities
- View their applications
- View applications for their own opportunities
- Accept applications
- Reject applications

Application statuses:

- PENDING
- ACCEPTED
- REJECTED

### 👥 Team Formation

When an opportunity owner accepts an application:

- The applicant is added to the project team.
- The opportunity owner is stored as the team OWNER.
- Other accepted applicants are stored as MEMBERS.
- Team size is enforced.

The application acceptance and team-member creation workflow is handled transactionally to maintain database consistency.

### 🎯 Required Skills

Each collaboration opportunity can specify required skills.

For example:

```text
AI Study Assistant
│
├── Java
├── Spring Boot
└── PostgreSQL
