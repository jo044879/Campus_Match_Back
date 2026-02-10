package com.pigs.holiday.repository;

import com.pigs.holiday.domain.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryImageRepository extends JpaRepository<GalleryImage, Long> {
    boolean findByImageUrl(String imageUrl);
}
