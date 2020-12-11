package com.example.httpverbs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HttpVerbsRepository extends JpaRepository<HttpVerbsModel, Long> {
    List<HttpVerbsModel> findByName(String name);
}
