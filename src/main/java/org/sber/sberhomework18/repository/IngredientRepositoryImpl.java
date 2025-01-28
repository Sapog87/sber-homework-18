package org.sber.sberhomework18.repository;

import lombok.RequiredArgsConstructor;
import org.sber.sberhomework18.entity.Ingredient;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IngredientRepositoryImpl implements IngredientRepository {
    private static final String INSERT_INTO_INGREDIENT = """
            INSERT INTO ingredient (name) \
            VALUES (?) \
            RETURNING id, name""";
    private static final String SELECT_ALL_FROM_INGREDIENT = "SELECT id, name FROM ingredient";
    private static final String SELECT_FROM_INGREDIENT_WHERE_ID = "SELECT id, name FROM ingredient WHERE id = ?";
    private static final BeanPropertyRowMapper<Ingredient> INGREDIENT_MAPPER = new BeanPropertyRowMapper<>(Ingredient.class);

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query(
                SELECT_ALL_FROM_INGREDIENT,
                INGREDIENT_MAPPER
        );
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return jdbcTemplate.queryForObject(
                INSERT_INTO_INGREDIENT,
                INGREDIENT_MAPPER,
                ingredient.getName()
        );
    }

    @Override
    public Ingredient findById(Long id) {
        List<Ingredient> result = jdbcTemplate.query(
                SELECT_FROM_INGREDIENT_WHERE_ID,
                INGREDIENT_MAPPER,
                id
        );
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}