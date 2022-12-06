package com.finalproject.facilgest.controller;

import com.finalproject.facilgest.consultasAgregacao.EntityQueryOne;
import com.finalproject.facilgest.consultasAgregacao.EntityQueryThree;
import com.finalproject.facilgest.consultasAgregacao.EntityQueryTwo;
import com.finalproject.facilgest.entity.Produtos;
import com.finalproject.facilgest.repository.ProdutosRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@RestController
public class ControlerConsultasComAgregacao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    Gson gson = new Gson();
    class QueryOneRowMapper implements RowMapper<EntityQueryOne> {
        @Override
        public EntityQueryOne mapRow(ResultSet rs, int rowNum) throws SQLException {
            EntityQueryOne entityQueryOne = new EntityQueryOne();
            entityQueryOne.setIdProduto(rs.getLong("id_produto"));
            entityQueryOne.setNome(rs.getString("nome"));
            entityQueryOne.setTotal(rs.getInt("total"));

            return entityQueryOne;
        }
    }
    class QueryTwoRowMapper implements RowMapper<EntityQueryTwo> {
        @Override
        public EntityQueryTwo mapRow(ResultSet rs, int rowNum) throws SQLException {
            EntityQueryTwo entityQueryTwo = new EntityQueryTwo();
            entityQueryTwo.setCpf(rs.getString("CPF"));
            entityQueryTwo.setNome(rs.getString("nome"));
            entityQueryTwo.setDataDeInicio(rs.getString("dataDeInicio"));
            entityQueryTwo.setTotal_vendido(rs.getInt("total_vendido"));

            return entityQueryTwo;
        }
    }

    class QueryThreeRowMapper implements RowMapper<EntityQueryThree> {
        @Override
        public EntityQueryThree mapRow(ResultSet rs, int rowNum) throws SQLException {
            EntityQueryThree entityQueryThree = new EntityQueryThree();
            entityQueryThree.setCpfCnpj(rs.getString("CPF_CNPJ"));
            entityQueryThree.setNomeCompleto(rs.getString("nomeCompleto"));
            entityQueryThree.setCompras(rs.getInt("compras"));
            entityQueryThree.setTotal(rs.getInt("total"));

            return entityQueryThree;
        }
    }

    @GetMapping("/queryOne")
    public String queryOne() {
        List<EntityQueryOne> result = jdbcTemplate.query("SELECT lista_desejos.id_produto,produtos.nome,COUNT(*) total FROM lista_desejos\n" +
                "INNER JOIN produtos ON lista_desejos.id_produto = produtos.id_produto\n" +
                "GROUP BY produtos.id_produto,produtos.nome\n" +
                "ORDER BY total desc;", new QueryOneRowMapper());
        return gson.toJson(result);
    }

    @GetMapping("/queryTwo")
    public String queryTwo() {
        List<EntityQueryTwo> result = jdbcTemplate.query("SELECT fun.CPF,fun.nome,fun.dataDeInicio,SUM(ve.valorVendasMes) total_vendido\n" +
                "FROM vendas_mes ve\n" +
                "INNER JOIN funcionario fun ON fun.CPF = ve.CPF_vendedor\n" +
                "GROUP BY fun.CPF,fun.nome\n" +
                "ORDER BY total_vendido desc;", new QueryTwoRowMapper());
        return gson.toJson(result);
    }

    @GetMapping("/queryThree")
    public String queryThree() {
        List<EntityQueryThree> result = jdbcTemplate.query("SELECT nf.CPF_CNPJ,cli.nomeCompleto,COUNT(*) compras,SUM(total_nota) total \n" +
                "FROM nota_fiscal nf\n" +
                "INNER JOIN clientes cli on cli.CPF_CNPJ = nf.CPF_CNPJ\n" +
                "GROUP BY cli.CPF_CNPJ,cli.nomeCompleto\n" +
                "ORDER BY compras desc;", new QueryThreeRowMapper());
        return gson.toJson(result);
    }

}
