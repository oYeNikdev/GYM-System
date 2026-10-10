# Gym Website & Management System

Website + membership management for a small Indian gym.
Stack: React + Tailwind, Spring Boot (modular monolith), MySQL, Flyway.

## Status
| Phase | Status |
|---|---|
| 0 Requirements | Draft docs ready, awaiting client answers |
| 2 Database | Initial schema in `backend/.../db/migration/V1__init_schema.sql` |
| 1, 3-11 | Not started |

## Structure
- `docs/`      requirements, scope, database design
- `backend/`   Spring Boot API (Java 21, Maven)
- `frontend/`  React app (Phase 1)

## Run locally
1. `cp .env.example .env` and set passwords
2. `docker compose up -d`  (MySQL)
3. `cd backend && mvn spring-boot:run`  (Flyway creates tables automatically)
4. Health check: http://localhost:8080/actuator/health

## Rules
- Never commit `.env`, keys, real member data or DB dumps.
- All schema changes go in a NEW Flyway file (V2__..., V3__...). Never edit an applied one.