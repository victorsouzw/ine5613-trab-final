package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ClientesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class ClientesRowMapper implements RowMapper <Clientes> {
        @Override
        public Clientes mapRow(ResultSet rs, int rowNum) throws SQLException {
            Clientes cliente = new Clientes();
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
            cliente.setDataDeNascimento(rs.getString("dataDeNascimento"));
            cliente.setEmail(rs.getString("email"));
            cliente.setNomeCompleto(rs.getString("nomeCompleto"));
            cliente.setTelefone(rs.getString("telefone"));
            //TODO MAYBE getLong
            cliente.setIdNota(rs.getLong("id_nota"));
            return cliente;
        }
    }

    public Optional<Clientes> findByCpfCnpj(String cpfCnpj) {
        return Optional.of(jdbcTemplate.queryForObject("select * from clientes where cpf_cnpj = ?", new Object[] {
                cpfCnpj},
                new BeanPropertyRowMapper<Clientes>(Clientes.class)));
    }

    public int insert(Clientes cliente) {
        return jdbcTemplate.update("insert into clientes (endereco, cpf_cnpj, dataDeNascimento, email," +
                        " nomeCompleto, telefone, id_nota) values(?, ?, ?, ?, ?, ?, ?)", new Object[] {
                       cliente.getEndereco(), cliente.getCpfCnpj(), cliente.getDataDeNascimento(), cliente.getEmail(),
                        cliente.getNomeCompleto(), cliente.getTelefone(), cliente.getIdNota(),
                });
    }
}
