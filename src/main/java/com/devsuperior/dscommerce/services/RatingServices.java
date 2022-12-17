package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.*;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.entities.Rating;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.repositories.CategoryRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.repositories.RatingReository;
import com.devsuperior.dscommerce.repositories.UserRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RatingServices {

    @Autowired
    private RatingReository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryService categoryService;

    @Transactional(readOnly = true)
    public RatingMinDTO findById(Long id) {
        Rating rating = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        System.out.println(rating);
        return new RatingMinDTO(rating);
    }

//    @Transactional(readOnly = true)
//    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
//        Page<Product> result = repository.searchByName(name, pageable);
//        return result.map(x -> new ProductMinDTO(x));
//    }

    @Transactional
    public RatingDTO insert(RatingDTO dto) {
        Rating entity = new Rating();
        copyDtoToEntity(dto, entity);

        Product productreferenceById = productRepository.getReferenceById(dto.getProductId());

        entity.setProduct(productreferenceById);
        entity.setUser(userRepository.getReferenceById(dto.getUserId()));
        entity = repository.save(entity);
        return new RatingDTO(entity);
    }

//    @Transactional
//    public ProductDTO update(Long id, ProductDTO dto) {
//        try {
//            Product entity = repository.getReferenceById(id);
//            copyDtoToEntity(dto, entity);
//            entity = repository.save(entity);
//            return new ProductDTO(entity);
//        }
//        catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException("Recurso não encontrado");
//        }
//    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(RatingDTO dto, Rating entity) {
        entity.setStars(dto.getStars());
        entity.setComments(dto.getComments());
    }
}
