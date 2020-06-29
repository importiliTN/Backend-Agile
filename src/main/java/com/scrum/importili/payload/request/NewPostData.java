package com.scrum.importili.payload.request;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Houssem
 */
    public class NewPostData {
    
    private String title;
    
    private String description;
    
    private String type;
    
    private float montant;

    private long  poster;

    
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

    public long getPoster() {
        return poster;
    }

    public void setPoster(long poster) {
        this.poster = poster;
    }
}

