package com.finalproject.facilgest.repository;

import com.finalproject.facilgest.entity.Clientes;
import com.finalproject.facilgest.entity.Funcionario;
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
public class FuncionarioRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class FuncionarioRowMapper implements RowMapper <Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(rs.getString("CPF"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setDataDeNascimento(rs.getString("dataDeInicio"));
            funcionario.setDataDeNascimento(rs.getString("dataDeNascimento"));
            return funcionario;
        }
    }

    public List<Funcionario> findAll() {
        return jdbcTemplate.query("select * from funcionario", new FuncionarioRowMapper());
    }

    public Optional<Funcionario> findByCpf(String cpf) {
        return Optional.of(jdbcTemplate.queryForObject("select * from funcionario where cpf = ?", new Object[] {
                cpf},
                new BeanPropertyRowMapper<Funcionario>(Funcionario.class)));
    }

        public int deleteByCpf(String cpf) {
        return jdbcTemplate.update("delete from funcionario where CPF=?", new Object[] {
                cpf
        });
    }

    public int insert(Funcionario funcionario) {
        return jdbcTemplate.update("insert into funcionario (cpf, nome, dataDeInicio,  dataDeNascimento) " +
                "values(?, ?, ?, ?)", new Object[] {
                       funcionario.getCpf(), funcionario.getNome(), funcionario.getDataDeInicio(), funcionario.getDataDeNascimento()
                });
    }
    //i was sleepy. sorry
    //without this, the fields that wasnt in the body (cause in some cases u dont wanna update all fields) turns null at json parse
    //then causing a sqlExcpetion when passed to SQL query
    private static Funcionario uglySolutionAgainstNullFields(Funcionario receivedFuncionario, Optional<Funcionario> funcionario){
        var nome = receivedFuncionario.getNome();
        var dataDeInicio = receivedFuncionario.getDataDeInicio();
        var dataDeNascimento = receivedFuncionario.getDataDeNascimento();


        if(nome != null ) {
            funcionario.get().setNome(nome);
        }

        if(dataDeNascimento != null ) {
            funcionario.get().setDataDeNascimento(dataDeNascimento);
        }

        if(dataDeInicio != null ) {
            funcionario.get().setDataDeInicio(dataDeInicio);
        }

        return funcionario.get();
    }
    public int update(Funcionario receivedFuncionario) {

        Optional<Funcionario> funcionario = Optional.of(jdbcTemplate.queryForObject("select * from funcionario where cpf = ?", new Object[] {
                        receivedFuncionario.getCpf()},
                new BeanPropertyRowMapper<Funcionario>(Funcionario.class)));

        Funcionario newFuncionario = FuncionarioRepository.uglySolutionAgainstNullFields(receivedFuncionario, funcionario);

        return jdbcTemplate.update("update funcionario  set nome = ?, dataDeInicio = ?, dataDeNascimento = ?" +
                        " where CPF = ?",

                newFuncionario.getNome(), newFuncionario.getDataDeInicio(), newFuncionario.getDataDeNascimento(),
                newFuncionario.getCpf());
    }
}
