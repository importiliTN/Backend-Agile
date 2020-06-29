/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrum.importili.payload.request;

/**
 *
 * @author Houssem
 */
public class PostSearchQuery {
    
    private String keywords;
    private String type;
    private float montant;
    private String borne;
    private int page;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getBorne() {
        return borne;
    }

    public void setBorne(String borne) {
        this.borne = borne;
    }
    
    public int getPage()
    {
        return page;
    }
    
    public void setPage(int page)
    {
        this.page=page;
    }
    
    
    
}