package com.nzleyuan.persistance.repositories;

import com.nzleyuan.persistance.com.nzleyuan.entity.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photos, Long> {
}
