package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.entities.Rating;

import java.util.HashSet;
import java.util.Set;

public class RatingMinDTO {

    private Long id;
    private Integer stars;
    private String comments;
    private Product product;
    private Long userId;

    public RatingMinDTO() {
    }

    public RatingMinDTO(Long id, Integer stars, String comments, Long userId) {
        this.id = id;
        this.stars = stars;
        this.comments = comments;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RatingMinDTO(Rating entity) {
        id = entity.getId();
        stars = entity.getStars();
        comments = entity.getComments();
        product = entity.getProduct();
        userId = entity.getUser().getId();
    }
}

