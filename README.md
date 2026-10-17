# Gym System

A work-in-progress website and gym-management project for a small Indian gym. The currently usable website is a static demo; business details shown on it are unverified.

## Current project structure

- `Basics Frontend/` - Working single-page HTML, CSS, and vanilla JavaScript demo.
- `frontend/` - React + Vite public-site preview with custom CSS.
- `backend/` - Spring Boot 3.5 / Java 25 Maven API, Flyway schema, and the public enquiry submission endpoint.
- `docker-compose.yml` - Local MySQL service configuration only.
- `docs/` - Draft requirements, project scope, and database design.

The React preview is still independent of the backend. The static website submits free-trial enquiries to the backend. Authentication and the planned admin panel are not implemented.

## Run the static website demo

To view the static page without submitting an enquiry, no Node.js, Java, or database is needed.

Open `Basics Frontend/index.html` directly in a browser.

## Run the enquiry workflow locally

Requirements: Java 25, Maven 3.9+, and Docker Compose.

1. Copy `.env.example` to `.env` and replace the sample database passwords with local values.
2. Start MySQL from the repository root:

	```powershell
	docker compose up -d mysql
	```

3. In a PowerShell terminal, set the database variables for the backend process, then start Spring Boot:

	```powershell
	$env:DB_HOST = "localhost"
	$env:DB_PORT = "3306"
	$env:DB_NAME = "gymdb"
	$env:DB_USER = "gymuser"
	$env:DB_PASSWORD = "your-local-password"
	cd backend
	mvn spring-boot:run
	```

	The default allowed frontend origins include `http://localhost:8000`. Set `CORS_ALLOWED_ORIGINS` to a comma-separated list of exact origins for deployment. Do not use `*` with private data.

4. In a second terminal, serve the static frontend over HTTP (opening the file directly uses an opaque origin and cannot call the API):

	```powershell
	cd "Basics Frontend"
	python -m http.server 8000
	```

5. Open `http://localhost:8000`. Set `CONFIG.apiBaseUrl` in `Basics Frontend/script/data.js` to the API origin for cross-origin hosting; use an empty string when the frontend and API share an origin.

Valid enquiries are stored in MySQL by Flyway-managed schema. The public API does not expose an enquiry listing; authenticated admin access is not implemented yet.

## Run the React preview

From the repository root:

```powershell
cd frontend
npm install
npm run dev
```

Open the local URL printed by Vite. To check a production build, run `npm run build` from `frontend/`.

The page uses example plan names and unverified placeholder content. Its trial form sends the minimum enquiry fields to the API and does not create a membership or take payment. Contact actions stay disabled until verified business details are added in `Basics Frontend/script/data.js`.

## Planned work

The project scope proposes a React + Tailwind public site and admin panel, plus Spring Boot features for enquiries, members, plans, memberships, payments, and reporting. Only public enquiry submission and persistence are implemented; the admin panel and other management features remain planned. Database-backed end-to-end behavior requires local MySQL configuration.

Review `docs/02-project-scope.md` before treating planned features as requirements.