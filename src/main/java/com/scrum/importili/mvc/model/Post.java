package com.scrum.importili.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.NumericField;

@Entity
@Indexed
@Table(name="Post")
public class Post {
       @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Field
    private String title;
    
    @Field
    private String description;
    
    @Field
    private String type;
    
    @Field
    @NumericField
    private float montant;
    
    @ManyToOne
    @JoinColumn(name="poster",referencedColumnName = "user_id")
    private User poster;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id=id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title=title;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description=description;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type=type;
    }
    
    public float getMontant()
    {
        return montant;
    }
    
    public void setMontant(float montant)
    {
        this.montant=montant;
    }
    
    public User getPoster()
    {
        return poster;
    }
    
    public void setPoster(User poster)
    {
        this.poster=poster;
    }
}