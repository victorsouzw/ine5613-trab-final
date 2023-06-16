package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Clientes;
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
            fornecedor.setTipoDeProduto(rs.getString("tipoDeProduto"));
            return fornecedor;
        }
    }

    public List<Fornecedor> findAll() {
        return jdbcTemplate.query("select * from fornecedor", new FornecedoresRowMapper());
    }

    public Optional<Fornecedor> findByCnpj(String cnpj) {
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
        return jdbcTemplate.update("insert into fornecedor (cnpj, nome, tipoDeProduto) values(?, ?, ?)", new Object[] {
                fornecedor.getCnpj(), fornecedor.getNome(), fornecedor.getTipoDeProduto()
        });
    }
    private static Fornecedor uglySolutionAgainstNullFields(Fornecedor receivedFornecedor, Optional<Fornecedor> fornecedor){
        var tipoDeProduto = receivedFornecedor.getTipoDeProduto();
        var nome = receivedFornecedor.getNome();


        if(tipoDeProduto != null) {
            fornecedor.get().setTipoDeProduto(receivedFornecedor.getTipoDeProduto());
        }

        if(nome != null ) {
            fornecedor.get().setNome(receivedFornecedor.getNome());
        }

        return fornecedor.get();
    }
    public int update(Fornecedor receivedFornecedor) {
        Optional<Fornecedor> fornecedor = Optional.of(jdbcTemplate.queryForObject("select * from fornecedor where cnpj = ?", new Object[] {
                        receivedFornecedor.getCnpj()},
                new BeanPropertyRowMapper<Fornecedor>(Fornecedor.class)));

        Fornecedor fornecedores = FornecedoresRepository.uglySolutionAgainstNullFields(receivedFornecedor, fornecedor);

        return jdbcTemplate.update("update fornecedor set nome = ? , tipoDeProduto = ? " +
                        "where CNPJ = ?",
                new Object[] {
                        fornecedores.getNome(), fornecedores.getTipoDeProduto(), fornecedores.getCnpj()
                });
    }
}
