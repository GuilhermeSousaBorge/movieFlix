CREATE TABLE movie_category (
    movie_id bigserial,
    category_id bigserial,
    CONSTRAINT fk_movie_category_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
    CONSTRAINT fk_movie_category_category FOREIGN KEY(category_id) REFERENCES category(id)
);