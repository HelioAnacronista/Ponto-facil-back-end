package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.entities.Rating;

import java.util.HashSet;
import java.util.Set;

public class RatingDTO {
    private Long id;
    private Integer stars;
    private String comments;
    private Long productId;
    private Long userId;

    public RatingDTO() {
    }

    public RatingDTO(Long id, Integer stars, String comments, Long productId, Long userId) {
        this.id = id;
        this.stars = stars;
        this.comments = comments;
        this.productId = productId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RatingDTO(Rating entity) {
        id = entity.getId();
        stars = entity.getStars();
        comments = entity.getComments();
        Product provis = new Product();
        setId(1L);
        productId = entity.getProducts().stream().filter(p -> p.getId() == id).findFirst().orElse(provis).getId();
        userId = entity.getUser().getId();
    }
}

