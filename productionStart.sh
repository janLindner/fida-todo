# create production ready backend service build
./gradlew quarkusBuild
docker run -p "8080:8080" --name "fida-todo-backend" --rm -itd $(docker build -q .)

# create production ready frontend service build
docker run -p "3000:3000" --name "fida-todo-frontend" --rm -itd $(docker build -q fida-todo-frontend)
