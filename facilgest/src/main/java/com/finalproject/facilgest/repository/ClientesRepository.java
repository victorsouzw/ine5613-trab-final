package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Clientes;
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
public class ClientesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class ClientesRowMapper implements RowMapper <Clientes> {
        @Override
        public Clientes mapRow(ResultSet rs, int rowNum) throws SQLException {
            Clientes cliente = new Clientes();
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setCpfCnpj(rs.getInt("cpf_cnpj"));
            cliente.setDataDeNascimento(rs.getString("dataDeNascimento"));
            cliente.setEmail(rs.getString("email"));
            cliente.setNomeCompleto(rs.getString("nomeCompleto"));
            cliente.setTelefone(rs.getString("telefone"));
            return cliente;
        }
    }

    public List<Clientes> findAll() {
        return jdbcTemplate.query("select * from clientes", new ClientesRowMapper());
    }

    public Optional<Clientes> findByCpfCnpj(int cpfCnpj) {
        return Optional.of(jdbcTemplate.queryForObject("select * from clientes where cpf_cnpj = ?", new Object[] {
                cpfCnpj},
                new BeanPropertyRowMapper<Clientes>(Clientes.class)));
    }

    public int deleteByCpfCnpj(int cpfCnpj) {
        return jdbcTemplate.update("delete from clientes where cpf_cnpj=?", new Object[] {
                cpfCnpj
        });
    }

    public int insert(Clientes cliente) {
        return jdbcTemplate.update("update into clientes (endereco, cpf_cnpj, dataDeNascimento, email," +
                        " nomeCompleto, telefone) values(?, ?, ?, ?, ?, ?)", new Object[] {
                       cliente.getEndereco(), cliente.getCpfCnpj(), cliente.getDataDeNascimento(), cliente.getEmail(),
                        cliente.getNomeCompleto(), cliente.getTelefone(),
                });
    }

    public int update(Clientes clientes) {
        return jdbcTemplate.update("update clientes " + " set endereco = ?, dataDeNascimento = ?" +
                        ", email = ?, nomeCompleto = ?, telefone = ? where CPF_CNPJ = ?",
                new Object[] {
                        clientes.getEndereco(), clientes.getDataDeNascimento(), clientes.getEmail(),
                        clientes.getNomeCompleto(), clientes.getTelefone(), clientes.getCpfCnpj()
                });
    }
}
