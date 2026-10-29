# Gym System

A work-in-progress website and gym-management project for a small Indian gym. The currently usable website is a static demo; business details shown on it are unverified.

## Current project structure

- `frontend/` - React + Vite public-site preview with custom CSS.
- `backend/` - Spring Boot 3.5 / Java 21 Maven scaffold, application configuration, and an initial Flyway database migration.
- `docker-compose.yml` - Local MySQL service configuration only.
- `docs/` - Draft requirements, project scope, and database design.

The React preview does not connect to the backend or database. The backend does not yet provide application API endpoints, authentication, or admin features. Tailwind and the planned admin panel are not implemented.

## Run the React preview

From the repository root:

```powershell
cd frontend
npm install
npm run dev
```

Open the local URL printed by Vite. To check a production build, run `npm run build` from `frontend/`.

The page uses example plan names and unverified placeholder content. It has no booking form and does not collect or submit personal information.

## Planned work

The project scope proposes a React + Tailwind public site and admin panel, plus Spring Boot features for enquiries, members, plans, memberships, payments, and reporting. These features are not implemented. Database configuration and an initial schema migration exist, but end-to-end database-backed application behavior has not been verified.

Review `docs/02-project-scope.md` before treating planned features as requirements.