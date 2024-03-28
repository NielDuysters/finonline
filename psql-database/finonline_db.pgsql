--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Homebrew)
-- Dumped by pg_dump version 16.2 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cashflow; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cashflow (
    id integer NOT NULL,
    type character varying(24) NOT NULL,
    amount numeric DEFAULT 0.00 NOT NULL,
    category_id integer,
    description character varying(512),
    transaction_date date,
    user_id integer NOT NULL
);


ALTER TABLE public.cashflow OWNER TO postgres;

--
-- Name: cashflow_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cashflow_categories (
    id integer NOT NULL,
    label character varying(512) NOT NULL,
    color character varying(255) NOT NULL,
    type character varying(24) NOT NULL,
    persistent boolean DEFAULT false NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.cashflow_categories OWNER TO postgres;

--
-- Name: cashflow_categories_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cashflow_categories_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cashflow_categories_id_seq OWNER TO postgres;

--
-- Name: cashflow_categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cashflow_categories_id_seq OWNED BY public.cashflow_categories.id;


--
-- Name: cashflow_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cashflow_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cashflow_id_seq OWNER TO postgres;

--
-- Name: cashflow_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cashflow_id_seq OWNED BY public.cashflow.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    pass character varying(512) NOT NULL,
    start_capital numeric
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: cashflow id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cashflow ALTER COLUMN id SET DEFAULT nextval('public.cashflow_id_seq'::regclass);


--
-- Name: cashflow_categories id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cashflow_categories ALTER COLUMN id SET DEFAULT nextval('public.cashflow_categories_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: cashflow; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cashflow (id, type, amount, category_id, description, transaction_date, user_id) FROM stdin;
38	EXPENSE	20	78	Champage NYE	2024-01-01	35
40	REVENUE	25	77	NYE money	2024-01-02	35
41	REVENUE	2500	71	Income job	2024-01-10	35
42	EXPENSE	200	70	Gasoline	2024-01-20	35
43	EXPENSE	150	73	Albert Heijn	2024-01-25	35
44	EXPENSE	29	73	Sushi	2024-01-26	35
45	REVENUE	1250	72	Bitcoin profit	2024-01-28	35
46	EXPENSE	25	76	New cat toy	2024-02-02	35
47	REVENUE	2500	71	Job income	2024-02-05	35
48	EXPENSE	350	75	New car tires	2024-02-05	35
50	REVENUE	35	77	Birthday money	2024-02-22	35
51	REVENUE	2500	71	Job income	2024-03-15	35
52	EXPENSE	7500	70	New car	2024-03-15	35
\.


--
-- Data for Name: cashflow_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cashflow_categories (id, label, color, type, persistent, user_id) FROM stdin;
75	Car	#7dc98c	EXPENSE	f	35
76	Cat	#554bea	EXPENSE	f	35
77	Pocket money	#b6cfc9	REVENUE	f	35
78	Party	#535833	EXPENSE	f	35
69	Other	#DF01A5	REVENUE	t	35
70	Other	#DF01A5	EXPENSE	t	35
71	Job	#0000FF	REVENUE	f	35
72	Investment	#FF0040	REVENUE	f	35
73	Food	#088A08	EXPENSE	f	35
74	Electricity	#D7DF01	EXPENSE	f	35
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, pass, start_capital) FROM stdin;
35	niel	$2a$10$vuIUJbnnUbdUQ5peEmRTSOe3NKVl54cTWv6.MC2bOwp8WkbSQA7Tu	2500
\.


--
-- Name: cashflow_categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cashflow_categories_id_seq', 78, true);


--
-- Name: cashflow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cashflow_id_seq', 52, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 35, true);


--
-- Name: cashflow_categories cashflow_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cashflow_categories
    ADD CONSTRAINT cashflow_categories_pkey PRIMARY KEY (id);


--
-- Name: cashflow cashflow_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cashflow
    ADD CONSTRAINT cashflow_pkey PRIMARY KEY (id);


--
-- Name: cashflow_categories uq_cashflow_categories; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cashflow_categories
    ADD CONSTRAINT uq_cashflow_categories UNIQUE (label, type, user_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: cashflow cashflow_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cashflow
    ADD CONSTRAINT cashflow_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.cashflow_categories(id) ON DELETE CASCADE;


--
-- Name: cashflow cashflow_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cashflow
    ADD CONSTRAINT cashflow_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: cashflow_categories fk_cashflow_categories_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cashflow_categories
    ADD CONSTRAINT fk_cashflow_categories_users FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

