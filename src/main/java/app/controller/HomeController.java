package app.controller;

import app.form.PostForm;
import app.model.Posts;
import app.service.posts.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private IPostService postService;

	@GetMapping("/")
	public String index(Model model){
		Iterable<Posts> list = postService.findAll();
		model.addAttribute("list", list);
		return "index";
	}

	@GetMapping("/add")
	public String add(){
		return "add";
	}

	@PostMapping("/add")
	public String addPost(@ModelAttribute PostForm postForm, Model model){
		Posts post = new Posts();
		post.setName(postForm.getName());
		post.setContent(postForm.getContent());
		postService.save(post);

		model.addAttribute("success", "Add successfully");

		return "add";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		Posts post = postService.findById(id);
		if (post == null){
			return "redirect:/";
		}
		model.addAttribute("post", post);
		return "edit";
	}

	@PostMapping("/edit/{id}")
	public String editPost(@PathVariable Long id, @ModelAttribute PostForm postForm, Model model){
		Posts post = postService.findById(id);
		post.setName(postForm.getName());
		post.setContent(postForm.getContent());
		postService.save(post);

		model.addAttribute("post", post);
		model.addAttribute("success", "Save successfully");

		return "edit";
	}

	@GetMapping("/post/{id}")
	public String post(@PathVariable Long id, Model model){
		Posts posts = postService.findById(id);
		if (posts != null){
			model.addAttribute("title", posts.getName());
			model.addAttribute("post", posts);
			return "post";
		}
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model){
		Posts posts = postService.findById(id);

		if (posts != null){
			Posts postsClone = new Posts();
			postsClone.setId(posts.getId());
			postsClone.setName(posts.getName());
			postsClone.setContent(posts.getContent());
			postService.delete(postsClone);
		}

		return "redirect:/";
	}
}
