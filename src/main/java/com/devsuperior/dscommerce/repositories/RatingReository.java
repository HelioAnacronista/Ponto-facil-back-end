package com.devsuperior.dscommerce.repositories;

import com.devsuperior.dscommerce.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingReository extends JpaRepository<Rating, Long> {
}
