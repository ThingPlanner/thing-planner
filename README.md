# ThingPlanner
![Testspace tests count](https://img.shields.io/testspace/passed/:org/:project/:space)


ThingPlanner is the ultimate productivity and organisation tool, affording you total agency to design the way you work — from team hubs and wikis to project dashboards, kanban boards and more!

----------------------------------------------------------------------------------------------------------------------

## ⚙️ Build

ThingPlanner's frontend is built with React.js. [Node.js](https://nodejs.org/en/download) version 22.x is required.

### Frontend

```shell
git clone https://github.com/ThingPlanner/thing-planner.git \ 
cd thing-planner/frontend \
npm install
```

**Run in DevMode**
```shell
npm run dev
```

### Backend

#### Database
```shell
cd .. \
cd backend/db && docker compose up -v
```

#### Quarkus 
**Run in DevMode**
```shell
cd .. \
./gradlew run quarkusDev 
```
