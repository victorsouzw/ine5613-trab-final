package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Funcionario;
import com.finalproject.facilgest.entity.Gerente;
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
public class GerenteRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class GerenteRowMapper implements RowMapper <Gerente> {
        @Override
        public Gerente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Gerente gerente = new Gerente();
            gerente.setCpfGerente(rs.getString("CPF_gerente"));

            return gerente;
        }
    }

    public List<Gerente> findAll() {
        return jdbcTemplate.query("select * from gerente", new GerenteRowMapper());
    }

    public Optional<Gerente> findByCpf(String cpf) {
        return Optional.of(jdbcTemplate.queryForObject("select * from gerente where cpf_gerente = ?", new Object[] {
                cpf},
                new BeanPropertyRowMapper<Gerente>(Gerente.class)));
    }

        public int deleteByCpf(String cpf) {
        return jdbcTemplate.update("delete from gerente where CPF_gerente=?", new Object[] {
                cpf
        });
    }

    public int insert(Gerente gerente) {
        return jdbcTemplate.update("insert into gerente (cpf_gerente) " +
                "values(?)", new Object[] {
                gerente.getCpfGerente()
        });
    }

}
