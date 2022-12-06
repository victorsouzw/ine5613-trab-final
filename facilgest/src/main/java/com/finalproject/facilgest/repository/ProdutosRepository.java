package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.Produtos;
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
public class ProdutosRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class ProdutosRowMapper implements RowMapper <Produtos> {
        @Override
        public Produtos mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produtos produtos = new Produtos();
            produtos.setMultiplicadorDesconto(rs.getInt("multiplicadorDesconto"));
            produtos.setIdProduto(rs.getInt("id_produto"));
            produtos.setNome(rs.getString("nome"));
            produtos.setValorUn(rs.getInt("valor_un"));
            produtos.setEstoque(rs.getLong("estoque"));

            return produtos;
        }
    }

    public List<Produtos> findAll() {
        return jdbcTemplate.query("select * from produtos", new ProdutosRowMapper());
    }

    public Optional<Produtos> findById(int id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from produtos where id_produto = ?", new Object[] {
                        id},
                new BeanPropertyRowMapper<Produtos>(Produtos.class)));
    }

        public int deleteById(int id) {
        return jdbcTemplate.update("delete from produtos where id_produto=?", new Object[] {
                id
        });
    }

    public int insert(Produtos produto) {
        return jdbcTemplate.update("insert into produtos (multiplicadorDesconto, id_produto, nome, valor_un," +
                        " estoque) values(?, ?, ?, ?, ?)", new Object[] {
                produto.getMultiplicadorDesconto(), produto.getIdProduto(), produto.getNome(),
                produto.getValorUn(), produto.getEstoque()
                });
    }
    //i was sleepy. sorry
    //without this, the fields that wasnt in the body (cause in some cases u dont wanna update all fields) turns null at json parse
    //then causing a sqlExcpetion when passed to SQL query
    private static Produtos uglySolutionAgainstNullFields(Produtos receivedProduto, Optional<Produtos> produto){
        var multiplicadorDesconto = receivedProduto.getMultiplicadorDesconto();
        var nome = receivedProduto.getNome();
        var valorUn = receivedProduto.getValorUn();
        var estoque = receivedProduto.getEstoque();


        if(!Objects.isNull(multiplicadorDesconto)) {
            produto.get().setMultiplicadorDesconto(multiplicadorDesconto);
        }

        if(nome != null ) {
            produto.get().setNome(nome);
        }

        if(!Objects.isNull(valorUn)) {
            produto.get().setValorUn(valorUn);
        }

        if(!Objects.isNull(estoque)) {
            produto.get().setEstoque(estoque);
        }

        return produto.get();
    }
    public int update(Produtos receivedProduto) {

        Optional<Produtos> produto = Optional.of(jdbcTemplate.queryForObject("select * from produtos where id_produto = ?", new Object[] {
                        receivedProduto.getIdProduto()},
                new BeanPropertyRowMapper<Produtos>(Produtos.class)));

        Produtos newProduto = ProdutosRepository.uglySolutionAgainstNullFields(receivedProduto, produto);

        return jdbcTemplate.update("update produtos  set multiplicadorDesconto = ?, nome = ?" +
                        ", valor_un = ?, estoque = ? where id_produto = ?",

                newProduto.getMultiplicadorDesconto(), newProduto.getNome(), newProduto.getValorUn(),
                newProduto.getEstoque(), newProduto.getIdProduto());
    }
}
