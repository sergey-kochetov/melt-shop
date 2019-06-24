CREATE TABLE public.profile (
  id BIGINT NOT NULL,
  uid VARCHAR(100) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  birth_day DATE DEFAULT NULL,
  phone VARCHAR(20) DEFAULT NULL UNIQUE,
  email VARCHAR(100) DEFAULT NULL UNIQUE,
  country VARCHAR(50) DEFAULT NULL,
  city VARCHAR(50) DEFAULT NULL,
  objective TEXT DEFAULT NULL,
  summary TEXT DEFAULT NULL,
  large_photo VARCHAR(255) DEFAULT NULL,
  small_photo VARCHAR(255) DEFAULT NULL,
  info TEXT DEFAULT NULL,
  password VARCHAR(255) NOT NULL,
  completed BOOLEAN DEFAULT false NOT NULL,
  created TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  skype VARCHAR(100) DEFAULT NULL,
  vkontakte VARCHAR(255) DEFAULT NULL,
  facebook VARCHAR(255) DEFAULT NULL,
  linkedin VARCHAR(255) DEFAULT NULL,
  github VARCHAR(255) DEFAULT NULL,
  stackoverflow VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
CREATE SEQUENCE public.profile_seq;

CREATE TABLE public.certificate (
  id BIGINT NOT NULL,
  id_profile BIGINT NOT NULL,
  name VARCHAR(50) NOT NULL,
  large_url VARCHAR(255) NOT NULL,
  smaill_url VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
ALTER TABLE public.certificate
  ADD CONSTRAINT certificate_fk FOREIGN KEY (id_profile)
    REFERENCES public.profile(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
CREATE SEQUENCE public.certificate_seq;

CREATE TABLE public.course (
  id BIGINT NOT NULL,
  id_profile BIGINT NOT NULL,
  name VARCHAR(100) NOT NULL,
  school VARCHAR(100) NOT NULL,
  finish_date TIME WITHOUT TIME ZONE DEFAULT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
ALTER TABLE public.course
  ADD CONSTRAINT course_fk FOREIGN KEY (id_profile)
    REFERENCES public.profile(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
CREATE SEQUENCE public.course_seq;

CREATE TABLE public.education (
  id BIGINT NOT NULL,
  id_profile BIGINT NOT NULL,
  summary VARCHAR(100) NOT NULL,
  begin_year INTEGER DEFAULT NULL,
  finish_year INTEGER DEFAULT NULL,
  university TEXT DEFAULT NULL,
  faculty VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
ALTER TABLE public.education
  ADD CONSTRAINT education_fk FOREIGN KEY (id_profile)
    REFERENCES public.profile(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
CREATE SEQUENCE public.education_seq;

CREATE TABLE public.hobby (
  id BIGINT NOT NULL,
  id_profile BIGINT NOT NULL,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
ALTER TABLE public.hobby
  ADD CONSTRAINT hobby_fk FOREIGN KEY (id_profile)
    REFERENCES public.profile(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
CREATE SEQUENCE public.hobby_seq;

CREATE TABLE public.language (
  id BIGINT NOT NULL,
  id_profile BIGINT NOT NULL,
  name VARCHAR(30) NOT NULL,
  level VARCHAR(30) NOT NULL,
  type VARCHAR(10) DEFAULT 0 NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
ALTER TABLE public.language
  ADD CONSTRAINT language_fk FOREIGN KEY (id_profile)
    REFERENCES public.profile(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
CREATE SEQUENCE public.language_seq;

CREATE TABLE public.practic (
  id BIGINT NOT NULL,
  id_profile BIGINT NOT NULL,
  "position" VARCHAR(100) NOT NULL,
  company VARCHAR(100) NOT NULL,
  begin_data DATE NOT NULL,
  finish_data DATE DEFAULT NULL,
  responsibilities TEXT NOT NULL,
  demo VARCHAR(255) DEFAULT NULL,
  src VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
ALTER TABLE public.practic
  ADD CONSTRAINT practic_fk FOREIGN KEY (id_profile)
    REFERENCES public.profile(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
CREATE SEQUENCE public.practic_seq;

CREATE TABLE public.skill (
  id BIGINT NOT NULL,
  id_profile BIGINT NOT NULL,
  category VARCHAR(100) NOT NULL,
  value TEXT NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);
ALTER TABLE public.skill
  ADD CONSTRAINT skill_fk FOREIGN KEY (id_profile)
    REFERENCES public.profile(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
CREATE SEQUENCE public.skill_seq;

CREATE TABLE public.profile_restore (
  id BIGINT NOT NULL,
  token VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);

CREATE TABLE public.skill_category (
  id BIGINT NOT NULL,
  category VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);