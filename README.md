# ThingPlanner
<img alt="Build" src="https://github.com/ThingPlanner/thing-planner/actions/workflows/quarkus-test.yml/badge.svg?branch=main" />

ThingPlanner is the ultimate productivity and organization tool, affording users a modular, block-based system for creating dynamic workspaces, documentation, and project management dashboards.

## Built With
<p align="left">
<img alt="typescript" src="https://shields.io/badge/TypeScript-3178C6?logo=TypeScript&logoColor=FFF">
<img alt="react" src="https://img.shields.io/badge/-ReactJs-4295a8?logo=react&logoColor=white">
<img alt="vite" src="https://img.shields.io/badge/Vite-646CFF?&logo=Vite&logoColor=white">
<img alt="java21" src="https://img.shields.io/badge/Java-ED8B00?&logo=openjdk&logoColor=white">
<img alt="quarkus" src="https://img.shields.io/badge/-Quarkus-4695EB?style=flat&logo=quarkus&logoColor=white">
<img alt="postgres" src="https://img.shields.io/badge/postgresql-4169e1?&logo=postgresql&logoColor=white">
<img alt="mongodb" src="https://img.shields.io/badge/-MongoDB-13aa52?&logo=mongodb&logoColor=white">
<img alt="keycloak" src="https://img.shields.io/badge/Keycloak-26.4.5-blue">
<img alt="aws" src="https://img.shields.io/badge/-AWS-FF9900?&logo=aws&logoColor=000000">
<img alt="azure" src="https://img.shields.io/badge/-Azure-007FFF?&logo=&logoColor=000000">
<img alt="ubuntu" src="https://img.shields.io/badge/Ubuntu-E95420?&logo=Ubuntu&logoColor=white">
</p>


### ⚙️ Build, installation and setup

#### 🔨 Run in Dev / Test mode
**Frontend**
```shell
cd frontend
npm install
npm run dev
```

**Setup KeyCloak**
```shell
if ! command -v docker >/dev/null 2>&1; then
  echo "[!] Error, unable to find docker installation path."
  exit 1
else
  docker run -p 127.0.0.1:8080:8080 \
    -e KC_BOOTSTRAP_ADMIN_USERNAME="$USERNAME" \
    -e KC_BOOTSTRAP_ADMIN_PASSWORD="$PASS" \
    quay.io/keycloak/keycloak:26.4.0 start-dev
fi
```

**Build postgres database**
```shell
if ! command -v docker >/dev/null 2>&1; then
  echo "[!] Error, unable to find docker installation path."
  exit 1
else
  cd /home/oliver/Development/thing-planner/backend/db 
  docker compose up -v 
fi
```

**Run Quarkus in**
```shell
cd backend
./gradlew run quarkusDev
```
