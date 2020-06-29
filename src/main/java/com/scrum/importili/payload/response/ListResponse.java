/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrum.importili.payload.response;

import com.scrum.importili.mvc.model.Post;
import java.util.List;

public class ListResponse {
    private List<Post> list;
    
    private int totalPages;
    
    public List<Post> getList()
    {
        return list;
    }
    
    public int getTotalPages()
    {
        return totalPages;
    }
    
    public void setList(List<Post> list)
    {
        this.list=list;
    }
    
    public void setTotalPages(int totalPages)
    {
        this.totalPages=totalPages;
    }
}

