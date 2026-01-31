# Library Management System (Java)

A comprehensive, console-based management system designed to streamline library operations. This project demonstrates the practical application of **Object-Oriented Programming (OOP)** in Java, featuring robust error handling and a clear hierarchy of users and resources.

K) ✨ Key Features
* **User Management:** Distinct roles for Students and Librarians with specific access levels.
* **Inventory Tracking:** Real-time monitoring of book availability, issues, and returns.
* **Custom Exception Handling:** Implements specialized error handling for common scenarios:
    * `BookNotAvailableException`: Triggered when a requested book is already checked out.
    * `InvalidUserException`: Ensures secure access by validating user credentials.
* **Persistent Logic:** Utilizes a structured class system to maintain data integrity during the session.

P) 📁 Project Structure
The repository is organized into specialized Java classes:
* **`LibraryManagementSystem.java`**: The main driver class and entry point.
* **`Library.class` / `Librarian.class`**: Logic for managing the library collection and staff operations.
* **`Book.class`**: Defines attributes like Title, Author, and Availability.
* **`Student.class` / `User.class`**: Handles user-specific data and borrowing history.
* **Exceptions**: Custom logic in `BookNotAvailableException.class` and `InvalidUserException.class`.

T) 🛠️ Technical Implementation
* **Language:** Java 8+
* **OOP Concepts Used:**
    * **Encapsulation:** Protecting data within classes like `Book` and `User`.
    * **Inheritance:** Leveraging a base `User` class for specialized roles.
    * **Polymorphism:** Method overriding for specific user actions.
    * **Abstraction:** Hiding complex library logic behind simple method calls.

### Prerequisites
* Java Development Kit (JDK) installed on your system.

### Running the Project
1. Clone the repository:
   ```bash
   git clone [https://github.com/SarvambhSangle/library-management-system-in-Java.git](https://github.com/SarvambhSangle/library-management-system-in-Java.git)
