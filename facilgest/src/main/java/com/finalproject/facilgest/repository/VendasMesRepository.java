package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.ComprasMes;
import com.finalproject.facilgest.entity.VendasMes;
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
public class VendasMesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class VendasMesRowMapper implements RowMapper <VendasMes> {
        @Override
        public VendasMes mapRow(ResultSet rs, int rowNum) throws SQLException {
            VendasMes vendasMes = new VendasMes();
            vendasMes.setNumeroDeVendasMes(rs.getInt("numeroDeVendasMes"));
            vendasMes.setValorVendasMes(rs.getInt("valorVendasMes"));
            vendasMes.setDataVenda(rs.getString("data_venda"));
            vendasMes.setCpfVendedor(rs.getString("CPF_vendedor"));
            return vendasMes;
        }
    }

    public List<VendasMes> findAll() {
        return jdbcTemplate.query("select * from vendas_mes", new VendasMesRowMapper());
    }

    public Optional<VendasMes> findByCpfAndData(String cpf, String data) {
        return Optional.of(jdbcTemplate.queryForObject("select * from vendas_mes where (CPF_vendedor = ? AND data_venda = ?)", new Object[] {
                cpf, data},
                new BeanPropertyRowMapper<VendasMes>(VendasMes.class)));
    }

    public int deleteByCpfAndData(String cpf, String data) {
        return jdbcTemplate.update("delete from vendas_mes where (CPF_vendedor = ? AND data_venda = ?)", new Object[] {
                cpf, data
        });
    }

    public int insert(VendasMes vendasMes) {
        return jdbcTemplate.update("insert into vendas_mes (numeroDeVendasMes, valorVendasMes, data_venda, CPF_vendedor)" +
                " values(?, ?, ?, ?)", new Object[] {
                       vendasMes.getNumeroDeVendasMes(), vendasMes.getValorVendasMes(), vendasMes.getDataVenda(),
                vendasMes.getCpfVendedor()
                });
    }
    //i was sleepy. sorry
    //without this, the fields that wasnt in the body (cause in some cases u dont wanna update all fields) turns null at json parse
    //then causing a sqlExcpetion when passed to SQL query
    private static VendasMes uglySolutionAgainstNullFields(VendasMes receivedVendasMes, Optional<VendasMes> vendasMes){
        var numeroDeVendasMes = receivedVendasMes.getNumeroDeVendasMes();
        var valorVendasMes = receivedVendasMes.getValorVendasMes();

        if(!Objects.isNull(numeroDeVendasMes)) {
            vendasMes.get().setNumeroDeVendasMes(numeroDeVendasMes);
        }

        if(!Objects.isNull(valorVendasMes) ) {
            vendasMes.get().setValorVendasMes(valorVendasMes);
        }

        return vendasMes.get();
    }
    public int update(VendasMes receivedVendasMes) {

        Optional<VendasMes> vendasMes = Optional.of(jdbcTemplate.queryForObject("select * from vendas_mes where (CPF_vendedor = ? AND data_venda = ?)", new Object[] {
                        receivedVendasMes.getCpfVendedor(), receivedVendasMes.getDataVenda()},
                new BeanPropertyRowMapper<VendasMes>(VendasMes.class)));

        VendasMes newVendasMes = VendasMesRepository.uglySolutionAgainstNullFields(receivedVendasMes, vendasMes);

        return jdbcTemplate.update("update vendas_mes  set numeroDeVendasMes = ?, valorVendasMes = ? " +
                        "where (CPF_vendedor = ? AND data_venda = ?)",

                newVendasMes.getNumeroDeVendasMes(), newVendasMes.getValorVendasMes(),
                newVendasMes.getCpfVendedor(), newVendasMes.getDataVenda());
    }
}
