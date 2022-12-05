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
            cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
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

    public Optional<Clientes> findByCpfCnpj(String cpfCnpj) {
        return Optional.of(jdbcTemplate.queryForObject("select * from clientes where cpf_cnpj = ?", new Object[] {
                cpfCnpj},
                new BeanPropertyRowMapper<Clientes>(Clientes.class)));
    }

        public int deleteByCpfCnpj(String cpfCnpj) {
        return jdbcTemplate.update("delete from clientes where cpf_cnpj=?", new Object[] {
                cpfCnpj
        });
    }

    public int insert(Clientes cliente) {
        return jdbcTemplate.update("insert into clientes (endereco, cpf_cnpj, dataDeNascimento, email," +
                        " nomeCompleto, telefone) values(?, ?, ?, ?, ?, ?)", new Object[] {
                       cliente.getEndereco(), cliente.getCpfCnpj(), cliente.getDataDeNascimento(), cliente.getEmail(),
                        cliente.getNomeCompleto(), cliente.getTelefone(),
                });
    }
    //i was sleepy. sorry
    //without this, the fields that wasnt in the body (cause in some cases u dont wanna update all fields) turns null at json parse
    //then causing a sqlExcpetion when passed to SQL query
    private static Clientes uglySolutionAgainstNullFields(Clientes receivedCliente, Optional<Clientes> cliente){
        var endereco = receivedCliente.getEndereco();
        var dataDeNascimento = receivedCliente.getDataDeNascimento();
        var email = receivedCliente.getEmail();
        var nomeCompleto = receivedCliente.getNomeCompleto();
        var telefone = receivedCliente.getTelefone();

        if(endereco != null) {
            cliente.get().setEndereco(receivedCliente.getEndereco());
        }

        if(dataDeNascimento != null ) {
            cliente.get().setDataDeNascimento(receivedCliente.getDataDeNascimento());
        }

        if(email != null ) {
            cliente.get().setEmail(receivedCliente.getEmail());
        }

        if(nomeCompleto != null ) {
            cliente.get().setNomeCompleto(receivedCliente.getNomeCompleto());
        }

        if(telefone != null) {
            cliente.get().setTelefone(receivedCliente.getTelefone());
        }
        return cliente.get();
    }
    public int update(Clientes receivedCliente) {

        Optional<Clientes> cliente = Optional.of(jdbcTemplate.queryForObject("select * from clientes where cpf_cnpj = ?", new Object[] {
                        receivedCliente.getCpfCnpj()},
                new BeanPropertyRowMapper<Clientes>(Clientes.class)));
        System.out.println(cliente.toString());
        Clientes clientes = ClientesRepository.uglySolutionAgainstNullFields(receivedCliente, cliente);
        System.out.println(clientes.toString());
        return jdbcTemplate.update("update clientes  set endereco = ?, dataDeNascimento = ?" +
                        ", email = ?, nomeCompleto = ?, telefone = ? where CPF_CNPJ = ?",

                clientes.getEndereco(), clientes.getDataDeNascimento(), clientes.getEmail(),
                clientes.getNomeCompleto(), clientes.getTelefone(), clientes.getCpfCnpj());

    }
}
