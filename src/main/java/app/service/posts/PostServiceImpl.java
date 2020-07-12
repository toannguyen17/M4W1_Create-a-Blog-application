package app.service.posts;

import app.model.Posts;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {
	@Autowired
	private PostRepository postRepository;

	@Override
	public Iterable<Posts> findAll() {
		return postRepository.findAll();
	}

	@Override
	public void save(Posts posts) {
		postRepository.save(posts);
	}

	@Override
	public Posts findById(Long id) {
		return postRepository.findOne(id);
	}

	@Override
	public void delete(Posts posts) {
		postRepository.delete(posts);
	}
}
