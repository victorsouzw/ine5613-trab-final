SELECT lista_desejos.id_produto,produtos.nome,COUNT(*) total FROM lista_desejos
INNER JOIN produtos ON lista_desejos.id_produto = produtos.id_produto
GROUP BY produtos.id_produto,produtos.nome
ORDER BY total desc;


SELECT fun.CPF,fun.nome,fun.dataDeInicio,SUM(ve.valorVendasMes) total_vendido
FROM vendas_mes ve
INNER JOIN funcionario fun ON fun.CPF = ve.CPF_vendedor
GROUP BY fun.CPF,fun.nome
ORDER BY total_vendido desc;

 
SELECT nf.CPF_CNPJ,cli.nomeCompleto,COUNT(*) compras,SUM(total_nota) total 
FROM nota_fiscal nf
INNER JOIN clientes cli on cli.CPF_CNPJ = nf.CPF_CNPJ
GROUP BY cli.CPF_CNPJ,cli.nomeCompleto
ORDER BY compras desc;
