PGDMP  "                    }            parfage    17.5    17.5     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    24739    parfage    DATABASE     {   CREATE DATABASE parfage WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.utf8';
    DROP DATABASE parfage;
                     postgres    false            �            1259    24741    users    TABLE     t   CREATE TABLE public.users (
    "idUsers" integer NOT NULL,
    login text,
    password text,
    username text
);
    DROP TABLE public.users;
       public         heap r       postgres    false            �            1259    24740    users_idUsers_seq    SEQUENCE     �   CREATE SEQUENCE public."users_idUsers_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public."users_idUsers_seq";
       public               postgres    false    218            �           0    0    users_idUsers_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public."users_idUsers_seq" OWNED BY public.users."idUsers";
          public               postgres    false    217            !           2604    24744    users idUsers    DEFAULT     r   ALTER TABLE ONLY public.users ALTER COLUMN "idUsers" SET DEFAULT nextval('public."users_idUsers_seq"'::regclass);
 >   ALTER TABLE public.users ALTER COLUMN "idUsers" DROP DEFAULT;
       public               postgres    false    218    217    218            �          0    24741    users 
   TABLE DATA           E   COPY public.users ("idUsers", login, password, username) FROM stdin;
    public               postgres    false    218   �
       �           0    0    users_idUsers_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."users_idUsers_seq"', 9, true);
          public               postgres    false    217            #           2606    24748    users users_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("idUsers");
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    218            �   m   x�3�442�a.#Μ����Ĵ��T��������\���]�saÅ�
@���/콰�b;���®���� .e�Lri�2���q3T��(	��������� h/�     