# Todo List Java Project

This repository contains a simple Java-based Todo List application developed as part of a backend home assignment. The project demonstrates clean code structure, JSON-based persistence, basic console UI, and additional algorithmic and design tasks.

---

## Project Structure

The project is organized as follows (matches the IntelliJ structure shown in the screenshot):

```
migdal-project/
├── .idea/
├── out/                 
├── src/
│   ├── algorithm/        # Part 2 – algorithms
│   │   └── algorithm.java
│   ├── Main.java         # Console UI demonstrating all functionalities
│   ├── Status.java       # Enum: NEW, IN_PROGRESS, DONE
│   ├── Task.java         # Task model
│   ├── TaskRepository.java  # Handles JSON file storage
│   └── TaskService.java  # Business logic layer
├── test.JSON             # JSON file used to store tasks
├── .gitignore
└── migdal-project.iml
```

---

##  Requirements

* Java JDK 11 or higher
* IntelliJ IDEA (recommended)
* Git

---

##  Running the Project in IntelliJ

1. Open IntelliJ IDEA.
2. Click **File → Open** and select the root folder (`migdal-project`).
3. Ensure the SDK is set:

    * `File → Project Structure → Project SDK → Select JDK 11+`
4. Make sure the file `test.JSON` exists in the project root.
5. Run the project:

    * Open `Main.java`
    * Click **Run**

The console menu will appear and allow:

* Add tasks
* Update tasks
* Delete tasks
* Search tasks
* Mark tasks as DONE
* List tasks sorted by status

---

##  JSON File Structure (`test.JSON`)

```
[
  {
    "id": 1,
    "title": "Buy groceries",
    "description": "Milk, eggs, bread",
    "status": "NEW"
  }
]
```

---

##  Part 1 – Backend (Java)

### **Task.java**

Contains:

* id
* title
* description
* status (enum)

### **TaskRepository.java**

Handles JSON-based persistence:

* add
* update
* delete
* getById
* listAll

### **TaskService.java**

Business logic:

* Mark task as DONE
* Search by title/description
* Return tasks sorted by status

### **Main.java**

Implements a console-based UI demonstrating all functionalities.

---

## Part 2 – Algorithms

Function that returns all strictly increasing sub-sequences from an array.
Example input:

```
[1, 2, 3, 1, 2]
```

Output:

```
[1, 2, 3]
[1, 2]
```

---


## Part 3 
The complete explanation for Part 3 is provided in the `docs/part3.docx` document.

---



## Author

* Developed by: yafa farkash

---



