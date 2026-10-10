-- V1: initial schema. Money in paise. Never edit after it has been applied; add V2__...

CREATE TABLE users (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  username      VARCHAR(100) NOT NULL UNIQUE,
  password_hash VARCHAR(100) NOT NULL,
  role          VARCHAR(20)  NOT NULL,
  active        BOOLEAN      NOT NULL DEFAULT TRUE,
  created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT chk_users_role CHECK (role IN ('ADMIN','STAFF','TRAINER','MEMBER'))
) ENGINE=InnoDB;

CREATE TABLE members (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id            BIGINT NULL UNIQUE,
  full_name          VARCHAR(150) NOT NULL,
  phone              VARCHAR(15)  NOT NULL,
  email              VARCHAR(150) NULL,
  gender             VARCHAR(10)  NULL,
  date_of_birth      DATE NULL,
  address            VARCHAR(300) NULL,
  emergency_contact  VARCHAR(150) NULL,
  photo_url          VARCHAR(300) NULL,
  join_date          DATE NOT NULL,
  status             VARCHAR(15) NOT NULL DEFAULT 'ACTIVE',
  notes              VARCHAR(500) NULL,
  created_at         DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at         DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_members_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT chk_members_status CHECK (status IN ('ACTIVE','INACTIVE'))
) ENGINE=InnoDB;
CREATE INDEX idx_members_phone ON members(phone);
CREATE INDEX idx_members_name  ON members(full_name);

CREATE TABLE membership_plans (
  id             BIGINT AUTO_INCREMENT PRIMARY KEY,
  name           VARCHAR(100) NOT NULL,
  duration_days  INT NOT NULL,
  price_paise    BIGINT NOT NULL,
  description    VARCHAR(300) NULL,
  active         BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT chk_plan_duration CHECK (duration_days > 0),
  CONSTRAINT chk_plan_price    CHECK (price_paise >= 0)
) ENGINE=InnoDB;

CREATE TABLE memberships (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  member_id   BIGINT NOT NULL,
  plan_id     BIGINT NOT NULL,
  start_date  DATE NOT NULL,
  end_date    DATE NOT NULL,
  status      VARCHAR(15) NOT NULL DEFAULT 'ACTIVE',
  created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_ms_member FOREIGN KEY (member_id) REFERENCES members(id),
  CONSTRAINT fk_ms_plan   FOREIGN KEY (plan_id)   REFERENCES membership_plans(id),
  CONSTRAINT chk_ms_dates  CHECK (end_date >= start_date),
  CONSTRAINT chk_ms_status CHECK (status IN ('ACTIVE','EXPIRED','CANCELLED'))
) ENGINE=InnoDB;
CREATE INDEX idx_ms_end_date ON memberships(end_date);
CREATE INDEX idx_ms_member   ON memberships(member_id);

CREATE TABLE payments (
  id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
  receipt_no            VARCHAR(30) NOT NULL UNIQUE,
  member_id             BIGINT NOT NULL,
  membership_id         BIGINT NULL,
  amount_paise          BIGINT NOT NULL,
  method                VARCHAR(10) NOT NULL,
  status                VARCHAR(10) NOT NULL DEFAULT 'PAID',
  reference_note        VARCHAR(200) NULL,
  razorpay_order_id     VARCHAR(60) NULL UNIQUE,
  razorpay_payment_id   VARCHAR(60) NULL UNIQUE,
  paid_at               DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  recorded_by           BIGINT NULL,
  created_at            DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_pay_member FOREIGN KEY (member_id)     REFERENCES members(id),
  CONSTRAINT fk_pay_ms     FOREIGN KEY (membership_id) REFERENCES memberships(id),
  CONSTRAINT fk_pay_user   FOREIGN KEY (recorded_by)   REFERENCES users(id),
  CONSTRAINT chk_pay_amount CHECK (amount_paise > 0),
  CONSTRAINT chk_pay_method CHECK (method IN ('CASH','UPI','CARD','ONLINE')),
  CONSTRAINT chk_pay_status CHECK (status IN ('PENDING','PAID','FAILED','VOID'))
) ENGINE=InnoDB;
CREATE INDEX idx_pay_paid_at ON payments(paid_at);
CREATE INDEX idx_pay_member  ON payments(member_id);

CREATE TABLE trainers (
  id             BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id        BIGINT NULL UNIQUE,
  full_name      VARCHAR(150) NOT NULL,
  phone          VARCHAR(15) NULL,
  specialization VARCHAR(150) NULL,
  bio            VARCHAR(500) NULL,
  photo_url      VARCHAR(300) NULL,
  show_on_website BOOLEAN NOT NULL DEFAULT TRUE,
  active         BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT fk_trainers_user FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB;

CREATE TABLE member_trainer (
  member_id  BIGINT NOT NULL,
  trainer_id BIGINT NOT NULL,
  PRIMARY KEY (member_id, trainer_id),
  CONSTRAINT fk_mt_member  FOREIGN KEY (member_id)  REFERENCES members(id),
  CONSTRAINT fk_mt_trainer FOREIGN KEY (trainer_id) REFERENCES trainers(id)
) ENGINE=InnoDB;

CREATE TABLE attendance (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  member_id    BIGINT NOT NULL,
  check_in_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_att_member FOREIGN KEY (member_id) REFERENCES members(id)
) ENGINE=InnoDB;
CREATE INDEX idx_att_member_time ON attendance(member_id, check_in_at);

CREATE TABLE enquiries (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(150) NOT NULL,
  phone       VARCHAR(15) NOT NULL,
  message     VARCHAR(500) NULL,
  status      VARCHAR(15) NOT NULL DEFAULT 'NEW',
  created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT chk_enq_status CHECK (status IN ('NEW','CONTACTED','JOINED','LOST'))
) ENGINE=InnoDB;

CREATE TABLE notification_log (
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  member_id  BIGINT NOT NULL,
  type       VARCHAR(30) NOT NULL,
  channel    VARCHAR(15) NOT NULL,
  sent_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_nl_member FOREIGN KEY (member_id) REFERENCES members(id)
) ENGINE=InnoDB;

CREATE TABLE audit_log (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id     BIGINT NULL,
  action      VARCHAR(50) NOT NULL,
  entity      VARCHAR(50) NOT NULL,
  entity_id   BIGINT NULL,
  details     VARCHAR(500) NULL,
  created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;
