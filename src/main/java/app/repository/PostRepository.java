package app.repository;

import app.model.Posts;

public interface PostRepository {
	Posts save(Posts entity);

	Posts findOne(Long aLong);

	boolean exists(Long aLong);

	Iterable<Posts> findAll();

	void delete(Posts entity);
}
