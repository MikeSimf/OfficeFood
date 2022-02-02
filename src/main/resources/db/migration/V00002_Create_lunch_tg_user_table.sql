CREATE SEQUENCE public.lunch_tg_users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.lunch_tg_users
(
    id bigint NOT NULL DEFAULT nextval('lunch_tg_users_id_seq'::regclass),
    chat_id  character varying(100),
    active boolean,
    CONSTRAINT lunch_tg_users_pkey PRIMARY KEY (id)
)

