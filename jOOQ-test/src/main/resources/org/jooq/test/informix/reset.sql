DELETE FROM t_exotic_types/
DELETE FROM t_639_numbers_table/
DELETE FROM t_book_to_book_store/
DELETE FROM t_book_store/
DELETE FROM t_book/
DELETE FROM t_author/
DELETE FROM t_language/
DELETE FROM t_booleans/
DELETE FROM t_dates/
DELETE FROM t_identity/
DELETE FROM t_identity_pk/
DELETE FROM t_triggers/

DROP SEQUENCE s_author_id/
CREATE SEQUENCE s_author_id/
DROP SEQUENCE s_trigger_id/
CREATE SEQUENCE s_trigger_id/

INSERT INTO t_language (id, cd, description, description_english) VALUES (1, 'en', 'English', 'English')/
INSERT INTO t_language (id, cd, description, description_english) VALUES (2, 'de', 'Deutsch', 'German')/
INSERT INTO t_language (id, cd, description, description_english) VALUES (3, 'fr', 'Français', 'French')/
INSERT INTO t_language (id, cd, description, description_english) VALUES (4, 'pt', null, null)/
/

INSERT INTO t_author VALUES (s_author_id.nextval, 'George', 'Orwell', '1903-06-25', 1903, null)
/
INSERT INTO t_author VALUES (s_author_id.nextval, 'Paulo', 'Coelho', '1947-08-24', 1947, null)
/

INSERT INTO t_book (id, author_id, co_author_id, details_id, title, published_in, language_id)
VALUES (1, 1, null, null, '1984', 1948, 1)
/
INSERT INTO t_book (id, author_id, co_author_id, details_id, title, published_in, language_id)
VALUES (2, 1, null, null, 'Animal Farm', 1945, 1)
/
INSERT INTO t_book (id, author_id, co_author_id, details_id, title, published_in, language_id)
VALUES (3, 2, null, null, 'O Alquimista', 1988, 4)
/
INSERT INTO t_book (id, author_id, co_author_id, details_id, title, published_in, language_id)
VALUES (4, 2, null, null, 'Brida', 1990, 2)
/

INSERT INTO t_book_store (name) VALUES ('Orell Füssli')/
INSERT INTO t_book_store (name) VALUES ('Ex Libris')/
INSERT INTO t_book_store (name) VALUES ('Buchhandlung im Volkshaus')/

INSERT INTO t_book_to_book_store VALUES ('Orell Füssli', 1, 10)/
INSERT INTO t_book_to_book_store VALUES ('Orell Füssli', 2, 10)/
INSERT INTO t_book_to_book_store VALUES ('Orell Füssli', 3, 10)/
INSERT INTO t_book_to_book_store VALUES ('Ex Libris', 1, 1)/
INSERT INTO t_book_to_book_store VALUES ('Ex Libris', 3, 2)/
INSERT INTO t_book_to_book_store VALUES ('Buchhandlung im Volkshaus', 3, 1)/

INSERT INTO t_directory VALUES                     ( 1, null, 1, 'C:')/
    INSERT INTO t_directory VALUES                 ( 2,    1, 1, 'eclipse')/
        INSERT INTO t_directory VALUES             ( 3,    2, 1, 'configuration')/
        INSERT INTO t_directory VALUES             ( 4,    2, 1, 'dropins')/
        INSERT INTO t_directory VALUES             ( 5,    2, 1, 'features')/
        INSERT INTO t_directory VALUES             ( 7,    2, 1, 'plugins')/
        INSERT INTO t_directory VALUES             ( 8,    2, 1, 'readme')/
            INSERT INTO t_directory VALUES         ( 9,    8, 0, 'readme_eclipse.html')/
        INSERT INTO t_directory VALUES             (10,    2, 1, 'src')/
        INSERT INTO t_directory VALUES             (11,    2, 0, 'eclipse.exe')/
        INSERT INTO t_directory VALUES             (12,    2, 0, 'eclipse.ini')/
    INSERT INTO t_directory VALUES                 (13,    1, 1, 'Program Files')/
        INSERT INTO t_directory VALUES             (14,   13, 1, 'Internet Explorer')/
            INSERT INTO t_directory VALUES         (15,   14, 1, 'de-DE')/
            INSERT INTO t_directory VALUES         (16,   14, 0, 'ielowutil.exe')/
            INSERT INTO t_directory VALUES         (17,   14, 0, 'iexplore.exe')/
        INSERT INTO t_directory VALUES             (18,   13, 1, 'Java')/
            INSERT INTO t_directory VALUES         (19,   18, 1, 'jre6')/
                INSERT INTO t_directory VALUES     (20,   19, 1, 'bin')/
                    INSERT INTO t_directory VALUES (21,   20, 0, 'java.exe')/
                    INSERT INTO t_directory VALUES (22,   20, 0, 'javaw.exe')/
                    INSERT INTO t_directory VALUES (23,   20, 0, 'javaws.exe')/
                INSERT INTO t_directory VALUES     (24,   19, 1, 'lib')/
                    INSERT INTO t_directory VALUES (25,   24, 0, 'javaws.jar')/
                    INSERT INTO t_directory VALUES (26,   24, 0, 'rt.jar')/
