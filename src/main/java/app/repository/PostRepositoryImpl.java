package app.repository;

import app.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class PostRepositoryImpl implements PostRepository {
	//@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Posts save(Posts posts) {
		if (posts.getId() == null){
			entityManager.persist(posts);
		}else{
			entityManager.merge(posts);
		}
		return posts;
	}

	@Override
	public Posts findOne(Long id) {
		return entityManager.find(Posts.class, id);
	}

	@Override
	public boolean exists(Long id) {
		Posts posts = entityManager.find(Posts.class, id);
		return posts != null;
	}

	@Override
	public Iterable<Posts> findAll() {
		TypedQuery<Posts> posts = entityManager.createQuery("SELECT c FROM Posts as c", Posts.class);
		return posts.getResultList();
	}

	@Override
	public void delete(Posts entity) {
		Object managed = entityManager.merge(entity);
		entityManager.remove(managed);
	}
}
