package com.ism.repository.bd;



import java.util.List;

import com.ism.repository.Repository;
public class RepositoryBDImpl<T> implements Repository<T>{

    
    @Override
    public void insert (T data){
        System.out.println("creation de compte dans une BD");
        //sql
    }

    @Override
    public List<T> selectAll(){
        //sql
        return null;
    }
    
}