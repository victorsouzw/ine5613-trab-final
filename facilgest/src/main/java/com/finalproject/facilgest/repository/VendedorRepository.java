package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Gerente;
import com.finalproject.facilgest.entity.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class VendedorRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class VendedorRowMapper implements RowMapper <Vendedor> {
        @Override
        public Vendedor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vendedor vendedor = new Vendedor();
            vendedor.setCpfVendedor(rs.getString("CPF_vendedor"));

            return vendedor;
        }
    }

    public List<Vendedor> findAll() {
        return jdbcTemplate.query("select * from vendedor", new VendedorRowMapper());
    }

    public Optional<Vendedor> findByCpf(String cpf) {
        return Optional.of(jdbcTemplate.queryForObject("select * from vendedor where cpf_vendedor = ?", new Object[] {
                cpf},
                new BeanPropertyRowMapper<Vendedor>(Vendedor.class)));
    }

        public int deleteByCpf(String cpf) {
        return jdbcTemplate.update("delete from vendedor where CPF_vendedor=?", new Object[] {
                cpf
        });
    }

    public int insert(Vendedor vendedor) {
        return jdbcTemplate.update("insert into vendedor (cpf_vendedor) " +
                "values(?)", new Object[] {
                vendedor.getCpfVendedor()
        });
    }

}
