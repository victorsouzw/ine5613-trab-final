package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.ComprasMes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ComprasMesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class ComprasMesRowMapper implements RowMapper <ComprasMes> {
        @Override
        public ComprasMes mapRow(ResultSet rs, int rowNum) throws SQLException {
            ComprasMes comprasMes = new ComprasMes();
            comprasMes.setNumCompras(rs.getInt("num_compras"));
            comprasMes.setValorCompras(rs.getInt("valor_compras"));
            comprasMes.setDataCompras(rs.getString("data_compras"));
            comprasMes.setCpfGerente(rs.getString("CPF_gerente"));
            return comprasMes;
        }
    }

    public List<ComprasMes> findAll() {
        return jdbcTemplate.query("select * from compras_mes", new ComprasMesRowMapper());
    }

    public Optional<ComprasMes> findByCpfAndData(String cpf, String data) {
        return Optional.of(jdbcTemplate.queryForObject("select * from compras_mes where (cpf_gerente = ? AND data_compras = ?)", new Object[] {
                cpf, data},
                new BeanPropertyRowMapper<ComprasMes>(ComprasMes.class)));
    }

    public int deleteByCpfAndData(String cpf, String data) {
        return jdbcTemplate.update("delete from compras_mes where (cpf_gerente = ? AND data_compras = ?)", new Object[] {
                cpf, data
        });
    }

    public int insert(ComprasMes comprasMes) {
        return jdbcTemplate.update("insert into compras_mes (num_compras, valor_compras, data_compras, CPF_gerente)" +
                " values(?, ?, ?, ?)", new Object[] {
                       comprasMes.getNumCompras(), comprasMes.getValorCompras(), comprasMes.getDataCompras(),
                comprasMes.getCpfGerente()
                });
    }
    //i was sleepy. sorry
    //without this, the fields that wasnt in the body (cause in some cases u dont wanna update all fields) turns null at json parse
    //then causing a sqlExcpetion when passed to SQL query
    private static ComprasMes uglySolutionAgainstNullFields(ComprasMes receivedComprasMes, Optional<ComprasMes> comprasMes){
        var numCompras = receivedComprasMes.getNumCompras();
        var valorCompras = receivedComprasMes.getValorCompras();

        if(!Objects.isNull(numCompras)) {
            comprasMes.get().setNumCompras(numCompras);
        }

        if(!Objects.isNull(valorCompras) ) {
            comprasMes.get().setValorCompras(valorCompras);
        }

        return comprasMes.get();
    }
    public int update(ComprasMes receivedComprasMes) {

        Optional<ComprasMes> comprasMes = Optional.of(jdbcTemplate.queryForObject("select * from compras_mes where " +
                        "(cpf_gerente = ? AND data_compras = ?)", new Object[] {
                        receivedComprasMes.getCpfGerente(), receivedComprasMes.getDataCompras()},
                new BeanPropertyRowMapper<ComprasMes>(ComprasMes.class)));

        ComprasMes newComprasMes = ComprasMesRepository.uglySolutionAgainstNullFields(receivedComprasMes, comprasMes);

        return jdbcTemplate.update("update compras_mes  set num_compras = ?, valor_compras = ? " +
                        "where (cpf_gerente = ? AND data_compras = ?)",

                newComprasMes.getNumCompras(), newComprasMes.getValorCompras(), newComprasMes.getCpfGerente(),
                newComprasMes.getDataCompras());
    }
}
