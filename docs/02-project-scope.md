# Gym Website & Management System: Project Scope (DRAFT)
Client: [NAME] | Gym: [NAME] | Developer: [YOU] | Date: [DATE] | Version: 0.1

## 1. Objective
Give [GYM] a professional online presence and replace paper/Excel tracking with a simple system for members, memberships, payments and renewals.

## 2. Features in scope (MVP)
| # | Module | Feature | Acceptance criterion |
|---|---|---|---|
| 1 | Website | Home, About, Plans, Trainers, Facilities, Gallery, Contact | Loads well on mobile; content approved by client |
| 2 | Website | Google Maps, WhatsApp button, Join Now enquiry form | Enquiries saved and visible to admin |
| 3 | Auth | Admin login | Only logged-in admin reaches admin pages |
| 4 | Members | Add / edit / search / deactivate member | Search by name or phone in under 2 sec |
| 5 | Plans | Manage membership plans and prices | Admin can add/edit plans |
| 6 | Membership | Assign plan, auto-calculate expiry, renew | Expiry date correct incl. renewals |
| 7 | Payments | Record cash/UPI payment, payment history | Every payment has a record |
| 8 | Receipts | PDF receipt per payment | Downloadable, shows receipt number |
| 9 | Renewals | Expiring-soon and expired lists; WhatsApp reminder link | One click opens WhatsApp with message |
| 10 | Dashboard | Active, expiring, expired, dues, today/month collection | Totals match manual check |

## 3. Pages
Public: Home, About, Plans, Trainers, Facilities, Gallery, Contact.
Admin: Login, Dashboard, Members, Member detail, Plans, Payments, Expiring/Expired, Enquiries.

## 4. User roles
MVP: ADMIN. (Optional: STAFF with limited rights.) Member and Trainer portals are out of scope.

## 5. Technology
React + Tailwind, Java 25 + Spring Boot, MySQL, hosted over HTTPS.

## 6. Deliverables
Deployed website and admin panel, admin credentials, source code (after final payment), short user guide, one training session.

## 7. Timeline (estimate)
| Milestone | Target |
|---|---|
| Requirements sign-off | Week 1 |
| Design approved | Week 2-3 |
| MVP demo | Week 7-8 |
| Testing with real data | Week 9-10 |
| Go-live | Week 10-11 |
Depends on client supplying content, photos and member data on time.

## 8. Payment terms
40% advance / 30% at MVP demo / 30% before go-live. Source code and production credentials handed over after final payment. Amount: ₹[____].

## 9. Hosting, domain, third-party costs
Billed separately, in the client's own accounts: domain ₹[__]/yr, hosting ₹[__]/mo, [others]. Client owns the accounts.

## 10. Maintenance
[30-60] days free bug fixes after go-live. Afterwards: ₹[__]/month covering bug fixes, backups, small content edits, response within [__] working hours. New features are quoted separately.

## 11. Change requests
Anything not listed in section 2 is a change request: written, estimated, and approved before work starts. Design revisions: [2] rounds.

## 12. Assumptions and client responsibilities
Timely feedback (within [3] working days), supplies logo/photos/text, provides existing member data in a usable format, owns the Razorpay/KYC process if online payments are added.

## 13. Limitations
Single branch. No mobile app. No biometric hardware integration. WhatsApp reminders are click-to-send, not automated.

## 14. Out of scope (MVP)
Online payments (V2, priced separately), attendance, trainer module, member login, expense tracking, automated WhatsApp/SMS, AI features, SEO campaigns, content writing, photography.

## 15. Acceptance
Client signs off each milestone. Date / Signature: ______
