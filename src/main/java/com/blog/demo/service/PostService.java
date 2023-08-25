package com.blog.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.demo.client.PostsClient;
import com.blog.demo.enums.PostStatus;
import com.blog.demo.model.Post;
import com.blog.demo.repositories.PostRepository;

@Service
public class PostService {
    private final PostsClient postsClient;
    private final PostRepository postRepository;

    public PostService(PostsClient postsClient, PostRepository postRepository) {
        this.postsClient = postsClient;
        this.postRepository = postRepository;
    }

    public void processPost(Long postId) {
        // Verifica se o postId é válido
        if (postId < 1 || postId > 100) {
            throw new IllegalArgumentException("Invalid postId.");
        }

        // Verifica se o post já existe
        if (postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post already exists.");
        }

        // Busca informações básicas do post da API externa
        Post externalPost = postsClient.getPostById(postId);

        // Salva o post no banco de dados com o estado CREATED
        Post post = new Post();
        post.setId(externalPost.getId());
        post.setTitle(externalPost.getTitle());
        post.setBody(externalPost.getBody());
        post.getHistory().setStatus(PostStatus.CREATED);
        postRepository.save(post);
    }

    public void disablePost(Long postId) {
        // Encontra o post pelo ID
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found."));

        // Verifica se o post está no estado ENABLED
        if (post.getHistory().getStatus() == PostStatus.ENABLED) {
            post.getHistory().setStatus(PostStatus.DISABLED);
            postRepository.save(post);
        } else {
            throw new IllegalArgumentException("Post cannot be disabled.");
        }
    }

    public void reprocessPost(Long postId) {
        // Encontra o post pelo ID
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found."));

        // Verifica se o post está no estado ENABLED ou DISABLED
        if (post.getHistory().getStatus() == PostStatus.ENABLED
                || post.getHistory().getStatus() == PostStatus.DISABLED) {
            // Atualiza o estado para UPDATING e inicia o processamento novamente
            post.getHistory().setStatus(PostStatus.UPDATED);
            postRepository.save(post);

            // Chamada para reprocessar o post
            // ... (coloque aqui a lógica de reprocessamento)

            // Atualiza o estado para ENABLED ou FAILED após o reprocessamento
            // ...
        } else {
            throw new IllegalArgumentException("Post cannot be reprocessed.");
        }
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
}
