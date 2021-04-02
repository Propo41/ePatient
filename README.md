# ePatient
A patient management desktop application built using JavaFX and MSSQL database.
It mainly focuses on doctor to patient management where the doctors can easily keep track of their patients' past records, including their medical history and prescriptions. 
The application consists of 3 roles: 
  - Admin
  - Receptionist
  - Doctor(s)

------------------------------------------------------------------------------------

- The Admin will be able to add/edit Doctor profiles and track the statistics of the entire hospital.
- The Receptionist will be handling the patients, create their profiles, make new appointments and add patient medical test reports, that will all be displayed in a user-friendly UI. 
- Finally, the Doctor(s) can receive patients and add prescriptions to their profile, which can later be printed out as a file.

APIs used
- HikariCP (for connection pooling)
- jfoenix

Patterns used
- DAO pattern for database operations
- MVC pattern
