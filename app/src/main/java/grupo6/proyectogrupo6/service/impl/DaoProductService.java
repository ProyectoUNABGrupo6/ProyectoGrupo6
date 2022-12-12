package grupo6.proyectogrupo6.service.impl;

import java.util.List;

import grupo6.proyectogrupo6.dao.ProductDao;
import grupo6.proyectogrupo6.entity.Product;
import grupo6.proyectogrupo6.service.ProductService;

public class DaoProductService implements ProductService {

    private ProductDao dao;

    public DaoProductService() {
    }
    public DaoProductService(ProductDao r) {
        this.dao = r;
    }
    public ProductDao getDao() {
        return dao;
    }

    @Override
    public List<Product> findAll() {
        return getDao().findAll();
    }
    @Override
    public Product findById(Long id) {
        return getDao().findById(id);
    }
    @Override
    public void save(Product... entities) {
        getDao().save(entities);
    }
    @Override
    public void update(Product... entities) {
        getDao().update(entities);
    }
    @Override
    public void delete(Product... entities) {
        getDao().delete(entities);
    }

}
