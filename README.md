# FIDA-ToDo Application

The `FIDA-ToDo Application` is a simple example application to showcase basic knowledge of `JavaEE/Jakarta` combined
with `JAX-RS` providing a simple service and javascript frontend to interact with each-other.

## Prerequisites

- Node 18+
- Java 17+
- Docker (For Production Ready Deployment)

## Frontend

The frontend single page application uses `react` as its framework. The javascript code is built and run by `next.js`.

## Backend

The backend application uses `Quarkus` as its framework built upon the specification of `Jakarta 10` and using the
implementation of the `JAX-RS` specification `RESTeasy`.
Example requests are contained in the exported postman collection in `fida-todo.postman_collection.json`.

## Development

To run the application in development mode run `./gradlew quarkusDev` to start the backend application
and `npm run-script dev` in the `fida-todo-frontend` directory to start the frontend application.

## 'Production' Deployment

The production ready deployment can be triggered executing the shell script `productionStart.sh`. The script will create and run individual docker images for the backend and frontend. The frontend can be reached on `http://localhost:3000`, as well the backend can be reached on `http://localhost:8080`.
