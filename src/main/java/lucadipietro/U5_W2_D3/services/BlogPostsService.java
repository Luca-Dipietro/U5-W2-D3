package lucadipietro.U5_W2_D3.services;

import lucadipietro.U5_W2_D3.controllers.BlogPostPayLoad;
import lucadipietro.U5_W2_D3.entities.Autore;
import lucadipietro.U5_W2_D3.entities.BlogPost;
import lucadipietro.U5_W2_D3.exceptions.BadRequestException;
import lucadipietro.U5_W2_D3.exceptions.NotFoundException;
import lucadipietro.U5_W2_D3.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostsRepository blogPostsRepository;
    @Autowired
    private AutoriService autoriService;

    Random rnd = new Random();

    public Page<BlogPost> getBlogPosts(int pageNumber,int pageSize,String sortBy){
        if(pageSize > 100) pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        return blogPostsRepository.findAll(pageable);
    }

    public BlogPost save(BlogPostPayLoad newBlogPost){
        this.blogPostsRepository.findByTitolo(newBlogPost.getTitolo()).ifPresent(
                blogPost -> {
                    throw new BadRequestException("Esiste già un post con questo titolo " + newBlogPost.getTitolo());
                }
        );
        Autore autore = autoriService.findById(newBlogPost.getAutoreId());
        BlogPost newPost = new BlogPost(newBlogPost.getCategoria(),newBlogPost.getTitolo(),newBlogPost.getContenuto(),newBlogPost.getTempoDiLettura(),autore);
        return this.blogPostsRepository.save(newPost);
    }

    public BlogPost findById(UUID blogPostId) {
        return this.blogPostsRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate(UUID blogPostId, BlogPost updateBlogPost){
        BlogPost found = this.findById(blogPostId);
        found.setCategoria(updateBlogPost.getCategoria());
        found.setTitolo(updateBlogPost.getTitolo());
        found.setCover("https://picsum.photos/" + rnd.nextInt(1,300) + "/" + rnd.nextInt(1,300));
        found.setContenuto(updateBlogPost.getContenuto());
        found.setTempoDiLettura(updateBlogPost.getTempoDiLettura());
        found.setAutore(updateBlogPost.getAutore());
        return blogPostsRepository.save(found);
    }

    public void findByIdAndDelete(UUID blogPostId) {
        BlogPost found = this.findById(blogPostId);
        this.blogPostsRepository.delete(found);
    }
}
