package vsb.vis.cz.moviezzz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vsb.vis.cz.moviezzz.mappers.CategoryMapper;
import vsb.vis.cz.moviezzz.models.Category;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryMapper.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveCategory(@RequestParam Category category) {
        categoryMapper.insert(category);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateCategory(@RequestParam Category category) {
        categoryMapper.update(category);
    }

}
