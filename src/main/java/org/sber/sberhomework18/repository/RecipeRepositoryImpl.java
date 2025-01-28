package org.sber.sberhomework18.repository;

import lombok.RequiredArgsConstructor;
import org.sber.sberhomework18.entity.Recipe;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepository {
    private static final String SELECT_FROM_RECIPE_WHERE_ID = """
            SELECT id, name \
            FROM recipe \
            WHERE id = ?""";
    private static final String SELECT_ALL_FROM_RECIPE = """
            SELECT id, name \
            FROM recipe""";
    private static final String SELECT_FROM_RECIPE_WHERE_NAME_LIKE = """
            SELECT id, name \
            FROM recipe \
            WHERE name ILIKE ?""";
    private static final String SELECT_FROM_RECIPE_WHERE_NAME = """
            SELECT id, name \
            FROM recipe \
            WHERE name LIKE ?""";
    private static final String INSERT_INTO_RECIPE = """
            INSERT INTO recipe (name) \
            VALUES (?) \
            RETURNING *""";
    private static final String DELETE_FROM_RECIPE_WHERE_ID = """
            DELETE FROM recipe \
            WHERE id = ?""";
    private static final BeanPropertyRowMapper<Recipe> RECIPE_MAPPER = new BeanPropertyRowMapper<>(Recipe.class);

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Recipe> findAll() {
        return jdbcTemplate.query(
                SELECT_ALL_FROM_RECIPE,
                RECIPE_MAPPER
        );
    }

    @Override
    public List<Recipe> findByNameLike(String name) {
        return jdbcTemplate.query(
                SELECT_FROM_RECIPE_WHERE_NAME_LIKE,
                RECIPE_MAPPER,
                "%" + name + "%"
        );
    }

    @Override
    public Recipe save(Recipe recipe) {
        return jdbcTemplate.queryForObject(
                INSERT_INTO_RECIPE,
                RECIPE_MAPPER,
                recipe.getName()
        );
    }

    @Override
    public boolean delete(Recipe recipe) {
        return jdbcTemplate.update(
                DELETE_FROM_RECIPE_WHERE_ID,
                recipe.getId()
        ) > 0;
    }

    @Override
    public Recipe findById(Long id) {
        List<Recipe> result = jdbcTemplate.query(
                SELECT_FROM_RECIPE_WHERE_ID,
                RECIPE_MAPPER,
                id
        );
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}