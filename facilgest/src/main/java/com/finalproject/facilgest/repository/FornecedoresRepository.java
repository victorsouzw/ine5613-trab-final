package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Fornecedor;
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
public class FornecedoresRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class FornecedoresRowMapper implements RowMapper <Fornecedor> {
        @Override
        public Fornecedor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setCnpj(rs.getString("cnpj"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setTipoDeProduto(rs.getString("tipo_de_produto"));
            return fornecedor;
        }
    }

    public List<Fornecedor> findAll() {
        return jdbcTemplate.query("select * from fornecedor", new FornecedoresRowMapper());
    }

    public Optional<Fornecedor> findByCnpj(int cnpj) {
        return Optional.of(jdbcTemplate.queryForObject("select * from fornecedor where cnpj = ?", new Object[] {
                        cnpj},
                new BeanPropertyRowMapper<Fornecedor>(Fornecedor.class)));
    }

    public int deleteByCnpj(String cnpj) {
        return jdbcTemplate.update("delete from fornecedor where cnpj=?", new Object[] {
                cnpj
        });
    }

    public int insert(Fornecedor fornecedor) {
        return jdbcTemplate.update("update into fornecedor (cnpj, nome, tipo_de_produto) values(?, ?, ?)", new Object[] {
                fornecedor.getCnpj(), fornecedor.getNome(), fornecedor.getTipoDeProduto()
        });
    }

    public int update(Fornecedor fornecedores) {

        return jdbcTemplate.update("update fornecedores set nome = ? , tipoDeProduto = ? " +
                        "where CNPJ = ?",
                new Object[] {
                        fornecedores.getNome(), fornecedores.getTipoDeProduto(), fornecedores.getCnpj()
                });
    }
}
