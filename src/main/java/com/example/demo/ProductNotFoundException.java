package com.example.demo;

public class ProductNotFoundException  extends RuntimeException{
         public ProductNotFoundException() {
        super("Producto no encontrado");
     }  
    
    }


