package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.ComprasMes;
import com.finalproject.facilgest.entity.ListaDesejos;
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
public class ListaDesejosRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class ListaDesejosRowMapper implements RowMapper <ListaDesejos> {
        @Override
        public ListaDesejos mapRow(ResultSet rs, int rowNum) throws SQLException {
            ListaDesejos listaDesejos = new ListaDesejos();
            listaDesejos.setPrecoDesejado(rs.getInt("preço_desejado"));
            listaDesejos.setCpfCnpj(rs.getString("CPF_CNPJ"));
            listaDesejos.setIdProduto(rs.getInt("id_produto"));

            return listaDesejos;
        }
    }

    public List<ListaDesejos> findAll() {
        return jdbcTemplate.query("select * from lista_desejos", new ListaDesejosRowMapper());
    }

    public Optional<ListaDesejos> findByCpfAndId(String cpf, int id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from lista_desejos where (CPF_CNPJ = ? AND id_produto = ?)", new Object[] {
                cpf, id},
                new BeanPropertyRowMapper<ListaDesejos>(ListaDesejos.class)));
    }

    public int deleteByCpfAndId(String cpf, int id) {
        return jdbcTemplate.update("delete from lista_desejos where (CPF_CNPJ = ? AND id_produto = ?)", new Object[] {
                cpf, id
        });
    }

    public int insert(ListaDesejos listaDesejos) {
        return jdbcTemplate.update("insert into lista_desejos (preço_desejado, CPF_CNPJ, id_produto)" +
                " values(?, ?, ?)", new Object[] {
                listaDesejos.getPrecoDesejado(), listaDesejos.getCpfCnpj(), listaDesejos.getIdProduto()
        });
    }
    //i was sleepy. sorry
    //without this, the fields that wasnt in the body (cause in some cases u dont wanna update all fields) turns null at json parse
    //then causing a sqlExcpetion when passed to SQL query
    private static ListaDesejos uglySolutionAgainstNullFields(ListaDesejos receivedListaDesejos, Optional<ListaDesejos> listaDesejos){
        var precoDesejado = receivedListaDesejos.getPrecoDesejado();

        if(!Objects.isNull(precoDesejado)) {
            listaDesejos.get().setPrecoDesejado(precoDesejado);
        }

        return listaDesejos.get();
    }
    public int update(ListaDesejos receivedListaDesejos) {

        Optional<ListaDesejos> listaDesejos = Optional.of(jdbcTemplate.queryForObject("select * from lista_desejos where " +
                        "(CPF_CNPJ = ? AND id_produto = ?)", new Object[] {
                        receivedListaDesejos.getCpfCnpj(), receivedListaDesejos.getIdProduto()},
                new BeanPropertyRowMapper<ListaDesejos>(ListaDesejos.class)));

        ListaDesejos newListaDesejos = ListaDesejosRepository.uglySolutionAgainstNullFields(receivedListaDesejos, listaDesejos);

        return jdbcTemplate.update("update lista_desejos  set preço_desejado = ? " +
                        "where (CPF_CNPJ = ? AND id_produto = ?)",

                newListaDesejos.getPrecoDesejado(), newListaDesejos.getCpfCnpj(),
                newListaDesejos.getIdProduto());
    }
}
