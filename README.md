# Toy Prize Draw — Java

Console application that simulates a toy prize draw with weighted selection and a prize queue.

## Features

- Add toys with quantity and draw probability
- Weighted prize selection based on probability and available quantity
- Change a toy's draw probability
- Remove toys automatically when their quantity reaches zero
- Keep a queue of won prizes
- Persist collected prizes to a local text file
- Console UI separated from business logic using an MVP-style structure

## Tech stack

- Java
- OOP
- Collections
- File I/O
- MVP pattern

## Project structure

```text
src/
├── Classes/          # Domain models
├── MVP/              # Model, View interface and Presenter
├── Prize_database/   # Local prize storage
└── UI/               # Console application and user input
```

## Run

Compile the sources and start `Program` from your IDE or Java toolchain.

The prize history is stored in `src/Prize_database/Database.txt` using a path relative to the project, so the application is portable across operating systems.

## Notes

This project demonstrates object-oriented design, separation of responsibilities, weighted random selection and basic persistence in a small console application.
