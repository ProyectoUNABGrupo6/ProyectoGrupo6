package grupo6.proyectogrupo6.service;

import java.util.List;

import grupo6.proyectogrupo6.entity.Product;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);
    void save(Product... entities);
    void update(Product... entities);
    void delete(Product... entities);

}

