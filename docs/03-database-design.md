# Database Design (v1)

## Conventions
- Money stored as integer **paise** (BIGINT). ₹500 = 50000. Never use float.
- Payments are never deleted; mistakes are handled with status (VOID) + audit_log.
- Change schema only through new Flyway migrations (never edit an applied one).

## Relationships
```
users 1--0..1 members
users 1--0..1 trainers
members 1--* memberships *--1 membership_plans
members 1--* payments *--0..1 memberships
members 1--* attendance
members *--* trainers   (via member_trainer)
members 1--* notification_log
enquiries: standalone (website "Join Now" form)
audit_log: who changed what
```

## Key business rules
- A member has at most one ACTIVE membership at a time (enforced in the service layer).
- `memberships.end_date` is stored, not recomputed.
- Renewal while still active starts the new period the day after the current end_date.
- Payment status: PENDING -> PAID | FAILED | VOID. Online payments become PAID only after server-side verification.
