package com.street.cat.cat.repository;

import com.street.cat.cat.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, String> {
    Optional<Point> findByCharacteristics(String s);
}
