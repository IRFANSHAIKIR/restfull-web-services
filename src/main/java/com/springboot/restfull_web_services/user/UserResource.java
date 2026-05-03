package com.springboot.restfull_web_services.user;

import com.springboot.restfull_web_services.jpa.PostRepository;
import com.springboot.restfull_web_services.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {
    private UserRepository repository;
    private PostRepository postRepository;
    UserResource(UserDaoService service, UserRepository repository, PostRepository postRepository){
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User>  getUser(@PathVariable int id){
        Optional<User> user =  repository.findById(id);

        if(user.isEmpty()){
            throw  new UserNotFoundException("user not found "+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        entityModel.add(
                linkTo(methodOn(this.getClass()).getAllUsers())
                        .withRel("all-users")
        );
        return  entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        repository.deleteById(id);


    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        user.setId(null);
        User savedUser =   repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Integer id, @Valid @RequestBody Post post){
        Optional<User> user =  repository.findById(id);

        if(user.isEmpty()){
            throw  new UserNotFoundException("user not found "+id);
        }

        post.setUser(user.get());
        Post savedPost =   postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post>  getPosts(@PathVariable int id){
        Optional<User> user =  repository.findById(id);

        if(user.isEmpty()){
            throw  new UserNotFoundException("user not found "+id);
        }

        return  user.get().getPosts();
    }
}
