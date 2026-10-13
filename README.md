# Gym System

A work-in-progress website and gym-management project for a small Indian gym. The currently usable website is a static demo; business details shown on it are unverified.

## Current project structure

- `Basics Frontend/` - Working single-page HTML, CSS, and vanilla JavaScript demo.
- `frontend/` - React + Vite public-site preview with custom CSS.
- `backend/` - Spring Boot 3.5 / Java 21 Maven scaffold, application configuration, and an initial Flyway database migration.
- `docker-compose.yml` - Local MySQL service configuration only.
- `docs/` - Draft requirements, project scope, and database design.

The React preview and static demo do not connect to the backend or database. The backend does not yet provide application API endpoints, authentication, or admin features. Tailwind and the planned admin panel are not implemented.

## Run the static website demo

No Node.js, Java, or database is needed to view the demo.

Open `Basics Frontend/index.html` directly in a browser.

## Run the React preview

From the repository root:

```powershell
cd frontend
npm install
npm run dev
```

Open the local URL printed by Vite. To check a production build, run `npm run build` from `frontend/`.

The page uses example plan names and unverified placeholder content. Its trial form only validates fictional test values in the browser; it does not save or send information or book a visit. Contact actions stay disabled until verified business details are added in `Basics Frontend/script/data.js`.

## Planned work

The project scope proposes a React + Tailwind public site and admin panel, plus Spring Boot features for enquiries, members, plans, memberships, payments, and reporting. These features are not implemented. Database configuration and an initial schema migration exist, but end-to-end database-backed application behavior has not been verified.

Review `docs/02-project-scope.md` before treating planned features as requirements.