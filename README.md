## My Backend

[![Backend CI](https://github.com/hongmoSung/my-backend/actions/workflows/backend-ci.yml/badge.svg)](https://github.com/hongmoSung/my-backend/actions/workflows/backend-ci.yml)

### DB

```shell
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name my-backend-db mysql:5.7.42
```

### Backend

```shell
docker build -t my-backend:latest .
docker run -e "spring.profiles.active=dev" -p 8080:8080 my-backend:latest
```

### git submodule

```shell
git submodule foreach git pull
```
