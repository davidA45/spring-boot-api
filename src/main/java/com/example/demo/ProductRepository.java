
package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
         this.jdbcTemplate = jdbcTemplate;
    }

    public int deleteById(Long id) {

    String sql = "DELETE FROM products WHERE id = ?";

    return jdbcTemplate.update(sql, id);
}
        

    public List<Product> findAll() {
        String sql = "SELECT id, name, price FROM products";
         
        
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                )
        );


    }

    public Product findById(Long id) {

    String sql = "SELECT id, name, price FROM products WHERE id = ?";

    List<Product> products = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price")
            ),
            id
    );

    return products.isEmpty() ? null : products.get(0);
}

    public Product save(Product product) {

        String sql = """
                INSERT INTO products (name, price)
                VALUES (?, ?)
                RETURNING id
                """;
        Long id = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                product.getName(),
                product.getPrice()
        );
        
    
        return new Product(
            id,
            product.getName(),
            product.getPrice()
         );

       
    }
   public int update(Product product) {
    String sql = """
            UPDATE products
            SET name = ?, price = ?
            WHERE id = ?
            """;

    return jdbcTemplate.update(
            sql,
            product.getName(),
            product.getPrice(),
            product.getId()
    );
}
}
