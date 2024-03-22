package repositories;

import edu.school21.models.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryJdbcImpl implements ProductRepository{
    @Override
    public List<Product> findAll(Long id) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
