package com.scrum.importili.services;

import com.scrum.importili.mvc.model.Post;
import com.scrum.importili.payload.request.PostSearchQuery;
import com.scrum.importili.payload.response.ListResponse;
import com.scrum.importili.payload.response.MessageResponse;
import com.scrum.importili.repositories.PostRepository;
import com.scrum.importili.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.scrum.importili.payload.request.NewPostData;

import javax.persistence.*;
import org.hibernate.search.jpa.*;
import org.hibernate.search.query.dsl.QueryBuilder;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public ResponseEntity<?> createPost(@RequestBody NewPostData data)
{
        Post post=new Post();
        post.setTitle(data.getTitle());
        post.setDescription(data.getDescription());
        post.setMontant(data.getMontant());
        post.setType(data.getType());
        post.setPoster(userRepository.findById(data.getPoster()).orElse(null));
        postRepository.save(post);
        return ResponseEntity.ok(new MessageResponse("Post added successfully"));
}
    
    public ResponseEntity<ListResponse> getPostList(@PathVariable int page)
    {
		
        ListResponse response=new ListResponse();
        Page p=postRepository.findAll(PageRequest.of(page,5));
        response.setList(p.getContent());
        response.setTotalPages(p.getTotalPages());
        return ResponseEntity.ok().body(response);
    }
    
    public ResponseEntity<?> getPost(Integer id)
    {
        Post p= postRepository.findById(id).orElse(null);
        return ResponseEntity.ok().body(p);
    }
    
    public ResponseEntity<ListResponse> searchPosts(PostSearchQuery query)
    {
        
      FullTextEntityManager fullTextEntityManager =
      org.hibernate.search.jpa.Search.
      getFullTextEntityManager(entityManager);
	  
      
      QueryBuilder queryBuilder = 
      fullTextEntityManager.getSearchFactory()
      .buildQueryBuilder().forEntity(Post.class).get();
      
      org.apache.lucene.search.Query keywordsQuery;
      if (query.getKeywords().equals(""))
      {
          keywordsQuery=queryBuilder
                  .keyword()
                    .wildcard()
                  .onField("title")
                  .matching("*")
                  .createQuery();
      }
      else
      {
          keywordsQuery=queryBuilder
                  .keyword()
                  .onField("title")
                  .matching(query.getKeywords())
                  .createQuery();
      }

              
      org.apache.lucene.search.Query typeQuery= queryBuilder
              .keyword()
              .onField("type")
              .matching(query.getType())
              .createQuery();
      
              
      org.apache.lucene.search.Query montantQuery;
      
      if(query.getBorne().equals(">"))
      {
          montantQuery=queryBuilder
                  .range()
                  .onField("montant")
                  .above(query.getMontant())
                  .createQuery();
      }
      else
      {
          montantQuery=queryBuilder
                  .range()
                  .onField("montant")
                  .from(0f).to(query.getMontant())
                  .createQuery();
      }
      
      org.apache.lucene.search.Query keyQuery= queryBuilder
              .bool()
                .must(keywordsQuery)
                .must(typeQuery)
                .must(montantQuery)
              .createQuery();
      
      org.hibernate.search.jpa.FullTextQuery jpaQuery =
      fullTextEntityManager.createFullTextQuery(keyQuery, Post.class);
      
      jpaQuery.setFirstResult(query.getPage()*10);
      jpaQuery.setMaxResults(10);
      
      ListResponse r=new ListResponse();
      r.setList(jpaQuery.getResultList());
      r.setTotalPages(jpaQuery.getResultSize()/10);
        return ResponseEntity.ok(r);
    }
}
