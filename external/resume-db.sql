CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);

CREATE TABLE certificate (
    id bigint NOT NULL,
    id_profile bigint NOT NULL,
    name character varying(50) NOT NULL,
    large_url character varying(255) NOT NULL,
    small_url character varying(255) NOT NULL
);


ALTER TABLE certificate OWNER TO resume;

CREATE SEQUENCE certificate_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE certificate_seq OWNER TO resume;

CREATE TABLE course (
    id bigint NOT NULL,
    id_profile bigint NOT NULL,
    name character varying(60) NOT NULL,
    school character varying(60) NOT NULL,
    finish_date date
);


ALTER TABLE course OWNER TO resume;


CREATE SEQUENCE course_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE course_seq OWNER TO resume;

CREATE TABLE education (
    id bigint NOT NULL,
    id_profile bigint NOT NULL,
    summary character varying(100) NOT NULL,
    begin_year integer NOT NULL,
    finish_year integer,
    university text NOT NULL,
    faculty character varying(255) NOT NULL
);


ALTER TABLE education OWNER TO resume;

CREATE SEQUENCE education_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE education_seq OWNER TO resume;

CREATE TABLE hobby (
    id bigint NOT NULL,
    id_profile bigint NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE hobby OWNER TO resume;


CREATE SEQUENCE hobby_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hobby_seq OWNER TO resume;


CREATE TABLE language (
    id bigint NOT NULL,
    id_profile bigint NOT NULL,
    name character varying(30) NOT NULL,
    level character varying(18) NOT NULL,
    type character varying(7) DEFAULT 0 NOT NULL
);


ALTER TABLE language OWNER TO resume;

CREATE SEQUENCE language_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE language_seq OWNER TO resume;


CREATE TABLE persistent_logins (
    username character varying(64) NOT NULL,
    series character varying(64) NOT NULL,
    token character varying(64) NOT NULL,
    last_used timestamp without time zone NOT NULL
);


ALTER TABLE persistent_logins OWNER TO resume;


CREATE TABLE practic (
    id bigint NOT NULL,
    id_profile bigint NOT NULL,
    "position" character varying(100) NOT NULL,
    company character varying(100) NOT NULL,
    begin_date date NOT NULL,
    finish_date date,
    responsibilities text NOT NULL,
    demo character varying(255),
    src character varying(255)
);


ALTER TABLE practic OWNER TO resume;

CREATE SEQUENCE practic_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE practic_seq OWNER TO resume;


CREATE TABLE profile (
    id bigint NOT NULL,
    uid character varying(100) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birth_day date,
    phone character varying(20),
    email character varying(100),
    country character varying(60),
    city character varying(100),
    objective text,
    summary text,
    large_photo character varying(255),
    small_photo character varying(255),
    info text,
    password character varying(255) NOT NULL,
    completed boolean NOT NULL,
    created timestamp(0) without time zone DEFAULT now() NOT NULL,
    skype character varying(80),
    vkontakte character varying(255),
    facebook character varying(255),
    linkedin character varying(255),
    github character varying(255),
    stackoverflow character varying(255)
);


ALTER TABLE profile OWNER TO resume;


CREATE TABLE profile_restore (
    id bigint NOT NULL,
    token character varying(255) NOT NULL
);


ALTER TABLE profile_restore OWNER TO resume;


CREATE SEQUENCE profile_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profile_seq OWNER TO resume;

CREATE TABLE skill (
    id bigint NOT NULL,
    id_profile bigint NOT NULL,
    category character varying(50) NOT NULL,
    value text NOT NULL
);


ALTER TABLE skill OWNER TO resume;


CREATE TABLE skill_category (
    id bigint NOT NULL,
    category character varying(50) NOT NULL
);


ALTER TABLE skill_category OWNER TO resume;


CREATE SEQUENCE skill_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE skill_seq OWNER TO resume;

ALTER TABLE ONLY certificate
    ADD CONSTRAINT certificate_pkey PRIMARY KEY (id);


ALTER TABLE ONLY course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


ALTER TABLE ONLY education
    ADD CONSTRAINT education_pkey PRIMARY KEY (id);

ALTER TABLE ONLY hobby
    ADD CONSTRAINT hobbi_pkey PRIMARY KEY (id);

ALTER TABLE ONLY language
    ADD CONSTRAINT language_pkey PRIMARY KEY (id);


ALTER TABLE ONLY persistent_logins
    ADD CONSTRAINT persistent_logins_pkey PRIMARY KEY (series);


ALTER TABLE ONLY practic
    ADD CONSTRAINT practic_pkey PRIMARY KEY (id);

ALTER TABLE ONLY profile
    ADD CONSTRAINT profile_email_key UNIQUE (email);


ALTER TABLE ONLY profile
    ADD CONSTRAINT profile_phone_key UNIQUE (phone);

ALTER TABLE ONLY profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);

ALTER TABLE ONLY profile_restore
    ADD CONSTRAINT profile_restore_pkey PRIMARY KEY (id);

ALTER TABLE ONLY profile_restore
    ADD CONSTRAINT profile_restore_uid_key UNIQUE (token);

ALTER TABLE ONLY profile
    ADD CONSTRAINT profile_uid_key UNIQUE (uid);

ALTER TABLE ONLY skill_category
    ADD CONSTRAINT skill_category_category_key UNIQUE (category);

ALTER TABLE ONLY skill_category
    ADD CONSTRAINT skill_category_pkey PRIMARY KEY (id);

ALTER TABLE ONLY skill
    ADD CONSTRAINT skill_pkey PRIMARY KEY (id);

CREATE INDEX certificate_idx ON certificate USING btree (id_profile);

CREATE INDEX course_idx ON course USING btree (finish_date);

CREATE INDEX course_idx1 ON course USING btree (id_profile);

CREATE INDEX education_idx ON education USING btree (id_profile);

CREATE INDEX education_idx1 ON education USING btree (finish_year);

CREATE INDEX hobbi_idx ON hobby USING btree (id_profile);

CREATE INDEX language_idx ON language USING btree (id_profile);

CREATE INDEX practic_idx ON practic USING btree (id_profile);

CREATE INDEX practic_idx1 ON practic USING btree (finish_date);

CREATE INDEX skill_idx ON skill USING btree (id_profile);

ALTER TABLE ONLY certificate
    ADD CONSTRAINT certificate_fk FOREIGN KEY (id_profile) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY course
    ADD CONSTRAINT course_fk FOREIGN KEY (id_profile) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY education
    ADD CONSTRAINT education_fk FOREIGN KEY (id_profile) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY hobby
    ADD CONSTRAINT hobby_fk FOREIGN KEY (id_profile) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY language
    ADD CONSTRAINT language_fk FOREIGN KEY (id_profile) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE ONLY persistent_logins
    ADD CONSTRAINT persistent_logins_fk FOREIGN KEY (username) REFERENCES profile(uid) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY practic
    ADD CONSTRAINT practic_fk FOREIGN KEY (id_profile) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY profile_restore
    ADD CONSTRAINT profile_restore_fk FOREIGN KEY (id) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY skill
    ADD CONSTRAINT skill_fk FOREIGN KEY (id_profile) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE;

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;

