CREATE TABLE users
(
    id SERIAL NOT NULL PRIMARY KEY,
    email character varying(45) NOT NULL,
    password character varying(60) NOT NULL,
    name character varying(45) NOT NULL,
    surname character varying(45) NOT NULL,
    phone character varying(11) NOT NULL,
    enabled smallint NOT NULL DEFAULT 0,
    role smallint NOT NULL DEFAULT 0,
    UNIQUE (username)
);

CREATE TABLE tokens
(
  email character varying(45) NOT NULL,
  token character varying(32) NOT NULL,
  PRIMARY KEY(email),
  FOREIGN KEY (email) REFERENCES users(email)
);

CREATE TABLE books
(
  id SERIAL NOT NULL PRIMARY KEY,
  title character varying(60) NOT NULL,
  date date NOT NULL,
  pages integer NOT NULL,
  ISBN character varying(13) NOT NULL,
  publisher character varying(100) NOT NULL,
  author character varying(70) NOT NULL,
  user_id integer NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  genre_id integer NOT NULL,
  FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE bookshelves
(
  id SERIAL NOT NULL PRIMARY KEY,
  name character varying(20) NOT NULL,
  description character varying(60) NOT NULL,
  user_id integer NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE books_bookshelves
(
  book_id integer NOT NULL,
  FOREIGN KEY (book_id) REFERENCES books(id),
  bookshelf_id integer NOT NULL,
  FOREIGN KEY (bookshelf_id) REFERENCES bookshelves(id)
);

ALTER TABLE books_bookshelves
ADD CONSTRAINT UQ_BookId_BSID UNIQUE(book_id, bookshelf_id);

CREATE TABLE reviews
(
  id SERIAL NOT NULL PRIMARY KEY,
  opinion TEXT NOT NULL,
  mark smallint NOT NULL default 0,
  spoiler boolean NOT NULL default false,
  user_id integer NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  book_id integer NOT NULL,
  FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE genres
(
  id SERIAL NOT NULL PRIMARY KEY,
  name character varying(20) NOT NULL,
  description character varying(60) NOT NULL
);