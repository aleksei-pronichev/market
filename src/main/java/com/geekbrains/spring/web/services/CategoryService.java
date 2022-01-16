package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.entities.Category;
import com.geekbrains.spring.web.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Category findByTitle(String title) {
        log.info("Try to find category by title: {}", title);

        return categoryRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }
}