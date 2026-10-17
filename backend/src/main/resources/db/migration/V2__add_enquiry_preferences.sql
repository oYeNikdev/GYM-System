ALTER TABLE enquiries
  ADD COLUMN preferred_plan VARCHAR(30) NULL AFTER phone,
  ADD COLUMN preferred_date DATE NULL AFTER preferred_plan;