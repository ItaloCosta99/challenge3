package com.blog.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog.demo.client.PostsClient;
import com.blog.demo.enums.PostStatus;
import com.blog.demo.model.Post;
import com.blog.demo.repositories.PostRepository;

@Service
public class PostService {
    private static final String POST_NOT_FOUND = "Post not found.";
    private PostsClient postsClient;
    private PostRepository postRepository;
    private HistoryService historyService;

    public PostService(PostsClient postsClient, PostRepository postRepository,
            HistoryService historyService) {
        this.postsClient = postsClient;
        this.postRepository = postRepository;
        this.historyService = historyService;
    }

    public Post getPost(Long postId) {
        // Encontra o post pelo ID
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(POST_NOT_FOUND));

        // Verifica se o post está no estado ENABLED
        if (post.getStatus() == PostStatus.ENABLED) {
            return post;
        } else {
            throw new IllegalArgumentException(POST_NOT_FOUND);
        }
    }

    public Post processPost(Long postId) {
        // Verifica se o postId é válido
        if (postId < 1 || postId > 100) {
            throw new IllegalArgumentException("Invalid postId.");
        }

        // Verifica se o post já existe
        if (postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post already exists.");
        }

        Post post = postRepository.findById(postId).orElse(postsClient.getPostById(postId));
        historyService.saveHistory(PostStatus.POST_FIND, post);
        historyService.saveHistory(PostStatus.POST_OK, post);

        // Salva o post no banco de dados com o estado CREATED

        post.setStatus(PostStatus.ENABLED);
        postRepository.save(post);
        historyService.saveHistory(PostStatus.ENABLED, post);
        return post;
    }

    public Post disablePost(Long postId) {
        // Encontra o post pelo ID
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(POST_NOT_FOUND));

        // Verifica se o post está no estado ENABLED

        if (post.getStatus() == PostStatus.ENABLED) {
            post.setStatus(PostStatus.DISABLED);
            historyService.saveHistory(PostStatus.DISABLED, post);
            return postRepository.save(post);
        } else {
            throw new IllegalArgumentException("Post cannot be disabled.");
        }

    }

    public Post reprocessPost(Long postId) {
        // Encontra o post pelo ID
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(POST_NOT_FOUND));

        // Verifica se o post está no estado ENABLED ou DISABLED

        if (post.getStatus() == PostStatus.ENABLED
                || post.getStatus() == PostStatus.DISABLED) {
            // Atualiza o estado para UPDATING e inicia o processamento novamente
            post.setStatus(PostStatus.UPDATING);
            historyService.saveHistory(PostStatus.UPDATING, post);
            historyService.saveHistory(PostStatus.ENABLED, post);
            return postRepository.save(post);

            // Chamada para reprocessar o post
            // ... (coloque aqui a lógica de reprocessamento)

            // Atualiza o estado para ENABLED ou FAILED após o reprocessamento
            // ...
        } else {
            throw new IllegalArgumentException("Post cannot be reprocessed.");
        }

    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Page<Post> getPostsWithPageList(int pageNum, int pageSize) {
        return postRepository.findAll(PageRequest.of(pageNum, pageSize));
    }
}
