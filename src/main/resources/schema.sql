DROP TABLE IF EXISTS "books";
DROP TABLE IF EXISTS "authors";

--DROP SEQUENCE IF EXISTS authors_id_seq;
-- CREATE SEQUENCE IF NOT EXISTS authors_id_seq;

CREATE TABLE "authors" (
                           "id" BIGINT DEFAULT nextval('authors_id_seq') NOT NULL ,
                           "name" TEXT,
                           "age" INTEGER,
                           CONSTRAINT "authors_pk" PRIMARY KEY("id")
);

CREATE TABLE "books"(
                        "isbn" TEXT NOT NULL ,
                        "title" TEXT,
                        "author_id" BIGINT,
                        CONSTRAINT "books_pk" PRIMARY KEY ("isbn"),
                        CONSTRAINT "book_author_fk" FOREIGN KEY ("author_id")
                            REFERENCES authors(id)
);
