CREATE TABLE movie_streaming (
     movie_id bigserial,
     streaming_id bigserial,
     CONSTRAINT fk_movie_streaming_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
     CONSTRAINT fk_movie_streaming_streaming FOREIGN KEY(streaming_id) REFERENCES streaming(id)
);