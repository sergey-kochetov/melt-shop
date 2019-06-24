DROP TABLE IF EXISTS public.account;


CREATE TABLE public.account (
  id BIGINT NOT NULL,
  login VARCHAR(60) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  middle_name VARCHAR(100),
  email VARCHAR(100) NOT NULL UNIQUE,
  active BOOLEAN DEFAULT true NOT NULL,
  created TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  updated TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY(id)
)
WITH (oids = false);
CREATE SEQUENCE public.account_seq;

CREATE TABLE public.role (
  id SMALLINT NOT NULL,
  name VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY(id)
)
WITH (oids = false);

CREATE TABLE public.account_role (
  id BIGINT NOT NULL,
  id_account BIGINT NOT NULL,
  id_role SMALLINT NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
CREATE SEQUENCE public.account_role_seq;

ALTER TABLE public.account_role
  ADD CONSTRAINT account_role_fk FOREIGN KEY (id_account)
    REFERENCES public.account(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

ALTER TABLE public.account_role
  ADD CONSTRAINT account_role_fk1 FOREIGN KEY (id_role)
    REFERENCES public.role(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

CREATE UNIQUE INDEX account_role_idx ON public.account_role
  USING btree (id_account, id_role);

CREATE TABLE public.account_registration (
  id BIGINT NOT NULL,
  code VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY(id)
)
WITH (oids = false);

ALTER TABLE public.account_registration
  ADD CONSTRAINT account_registration_fk FOREIGN KEY (id)
    REFERENCES public.account(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

CREATE TABLE public.test (
  id BIGINT NOT NULL,
  id_account BIGINT NOT NULL,
  name VARCHAR(100) NOT NULL,
  description text NOT NULL,
  time_per_question INTEGER DEFAULT 30 NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
CREATE SEQUENCE public.test_seq;

ALTER TABLE public.test
  ADD CONSTRAINT test_fk FOREIGN KEY (id_account)
    REFERENCES public.account(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

CREATE TABLE public.question (
  id BIGINT NOT NULL,
  id_test BIGINT NOT NULL,
  name VARCHAR(101) NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
CREATE SEQUENCE public.question_seq;

ALTER TABLE public.question
  ADD CONSTRAINT question_fk FOREIGN KEY (id_test)
    REFERENCES public.test(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

CREATE TABLE public.answer (
  id BIGINT NOT NULL,
  id_question BIGINT NOT NULL,
  name VARCHAR(100) NOT NULL,
  correct BOOLEAN DEFAULT false NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
CREATE SEQUENCE public.answer_seq;

ALTER TABLE public.answer
  ADD CONSTRAINT answer_fk FOREIGN KEY (id_question)
    REFERENCES public.question(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

CREATE TABLE public.student (
  id BIGINT NOT NULL,
  id_account BIGINT NOT NULL,
  id_test BIGINT NOT NULL,
  percent DOUBLE PRECISION NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);

ALTER TABLE public.student
  ADD CONSTRAINT student_fk FOREIGN KEY (id_account)
    REFERENCES public.account(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

ALTER TABLE public.student
  ADD CONSTRAINT student_fk1 FOREIGN KEY (id_test)
    REFERENCES public.test(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

CREATE TABLE public.result (
  id BIGINT NOT NULL,
  id_account BIGINT NOT NULL,
  id_test BIGINT,
  persent DOUBLE PRECISION NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
CREATE SEQUENCE public.result_seq;

ALTER TABLE public.result
  ADD CONSTRAINT result_fk FOREIGN KEY (id_account)
    REFERENCES public.account(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

ALTER TABLE public.result
  ADD CONSTRAINT result_fk1 FOREIGN KEY (id_test)
    REFERENCES public.test(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    NOT DEFERRABLE;

ALTER TABLE public.result
  ADD COLUMN test_name VARCHAR(100) NOT NULL;
ALTER TABLE public.result
  ADD COLUMN test_description TEXT NOT NULL;
ALTER TABLE public.result
  ADD COLUMN created TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT now() NOT NULL;