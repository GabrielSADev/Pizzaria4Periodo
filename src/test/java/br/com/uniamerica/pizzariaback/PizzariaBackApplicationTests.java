package br.com.uniamerica.pizzariaback;
import br.com.uniamerica.pizzariaback.controller.*;
import br.com.uniamerica.pizzariaback.dto.*;
import br.com.uniamerica.pizzariaback.entity.*;
import br.com.uniamerica.pizzariaback.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class PizzariaBackApplicationTests {

	@MockBean
	UsuarioRep usuarioRep;
	@Autowired
	UsuarioController usuarioController;
	private List<Usuario> usuarioList;

	@MockBean
	EnderecoRep enderecoRep;
	@Autowired
	EnderecoController enderecoController = new EnderecoController();
	private List<Endereco> enderecoList;

	@MockBean
	FuncionarioRep funcionarioRep;
	@Autowired
	FuncionarioController funcionarioController = new FuncionarioController();
	private List<Funcionario> funcionarioList;

	@MockBean
	SaboresRep saboresRep;
	@Autowired
	SaboresController saboresController = new SaboresController();
	private List<Sabores> saboresList;

	@MockBean
	LoginRep loginRep;
	@Autowired
	LoginController loginController = new LoginController();
	private List<Login> loginList;

	@MockBean
	EstoqueProdRep estoqueProdRep;
	@Autowired
	EstoqueProdsController estoqueProdsController = new EstoqueProdsController();
	private List<EstoqueProds> estoqueProdsList;

	@MockBean
	PizzaRep pizzaRep;
	@Autowired
	PizzaController pizzaController = new PizzaController();
	private List<Pizza> pizzaList;

	@MockBean
	ProdutosRep produtosRep;
	@Autowired
	ProdutosController produtosController;
	private List<Produtos> produtosList;

	@MockBean
	PedidoRep pedidoRep;
	@Autowired
	PedidoController pedidoController;
	private List<Pedido> pedidoList;


	@BeforeEach
	void injectData(){
		Usuario usuario = new Usuario(1L, "Gabriel", "45998036059");
		Usuario usuario2 = new Usuario(2L, "Joao", "45999690045");
		usuarioList = new ArrayList<>();
		usuarioList.add(usuario);
		usuarioList.add(usuario2);

		Mockito.when(usuarioRep.save(usuario)).thenReturn(usuario);
		Mockito.when(usuarioRep.save(usuario2)).thenReturn(usuario2);
		Mockito.when(usuarioRep.findById(1L)).thenReturn(Optional.of(usuario));
		Mockito.when(usuarioRep.findById(2L)).thenReturn(Optional.of(usuario2));
		Mockito.when(usuarioRep.findAll()).thenReturn(usuarioList);

		Endereco endereco = new Endereco(1L,"RUAteste","BAIRROteste",530,usuario);
		Endereco endereco2 = new Endereco(2L, "RUAdois", "BAIRROdois",777,usuario2);
		enderecoList = new ArrayList<>();
		enderecoList.add(endereco);
		enderecoList.add(endereco2);

		Mockito.when(enderecoRep.save(endereco)).thenReturn(endereco);
		Mockito.when(enderecoRep.save(endereco2)).thenReturn(endereco2);
		Mockito.when(enderecoRep.findById(1L)).thenReturn(Optional.of(endereco));
		Mockito.when(enderecoRep.findById(2L)).thenReturn(Optional.of(endereco2));
		Mockito.when(enderecoRep.findAll()).thenReturn(enderecoList);

		Login login = new Login(1L,"nomeTeste","senhaTeste");
		Login login2 = new Login(2L,"nomeDOIS", "senhaDOIS");
		Login login3 = new Login(3L, "logintes","senhatres");
		loginList = new ArrayList<>();
		loginList.add(login);
		loginList.add(login2);
		loginList.add(login3);

		Mockito.when(loginRep.save(login)).thenReturn(login);
		Mockito.when(loginRep.save(login2)).thenReturn(login2);
		Mockito.when(loginRep.findById(1L)).thenReturn(Optional.of(login));
		Mockito.when(loginRep.findById(2L)).thenReturn(Optional.of(login2));
		Mockito.when(loginRep.findAll()).thenReturn(loginList);

		Funcionario funcionario = new Funcionario(1L,"David");
		Funcionario funcionario2 = new Funcionario(2L,"PAINHO");
		funcionarioList = new ArrayList<>();
		funcionarioList.add(funcionario);
		funcionarioList.add(funcionario2);

		Mockito.when(funcionarioRep.save(funcionario)).thenReturn(funcionario);
		Mockito.when(funcionarioRep.save(funcionario2)).thenReturn(funcionario2);
		Mockito.when(funcionarioRep.findById(1L)).thenReturn(Optional.of(funcionario));
		Mockito.when(funcionarioRep.findById(2L)).thenReturn(Optional.of(funcionario2));
		Mockito.when(funcionarioRep.findAll()).thenReturn(funcionarioList);

		EstoqueProds estoqueProds = new EstoqueProds(1L,10,"PITU");
		EstoqueProds estoqueProds2 = new EstoqueProds(2L,5,"COROTE");
		estoqueProdsList = new ArrayList<>();
		estoqueProdsList.add(estoqueProds);
		estoqueProdsList.add(estoqueProds2);

		Mockito.when(estoqueProdRep.save(estoqueProds)).thenReturn(estoqueProds);
		Mockito.when(estoqueProdRep.save(estoqueProds2)).thenReturn(estoqueProds2);
		Mockito.when(estoqueProdRep.findById(1L)).thenReturn(Optional.of(estoqueProds));
		Mockito.when(estoqueProdRep.findById(2L)).thenReturn(Optional.of(estoqueProds2));
		Mockito.when(estoqueProdRep.findAll()).thenReturn(estoqueProdsList);

		Sabores sabores = new Sabores(1L, "Calabresa");
		Sabores sabores2 = new Sabores(2L, "Linguica");
		saboresList = new ArrayList<>();
		saboresList.add(sabores);
		saboresList.add(sabores2);

		Mockito.when(saboresRep.save(sabores)).thenReturn(sabores);
		Mockito.when(saboresRep.save(sabores2)).thenReturn(sabores2);
		Mockito.when(saboresRep.findById(1L)).thenReturn(Optional.of(sabores));
		Mockito.when(saboresRep.findById(2L)).thenReturn(Optional.of(sabores2));
		Mockito.when(saboresRep.findAll()).thenReturn(saboresList);

		Pizza pizza = new Pizza(1L, saboresList,30,1,Tamanho.P);
		Pizza pizza2 = new Pizza(2L, saboresList, 60,2,Tamanho.M);
		pizzaList = new ArrayList<>();
		pizzaList.add(pizza);
		pizzaList.add(pizza2);

		Mockito.when(pizzaRep.save(pizza)).thenReturn(pizza);
		Mockito.when(pizzaRep.save(pizza2)).thenReturn(pizza2);
		Mockito.when(pizzaRep.findById(1L)).thenReturn(Optional.of(pizza));
		Mockito.when(pizzaRep.findById(2L)).thenReturn(Optional.of(pizza2));
		Mockito.when(pizzaRep.findAll()).thenReturn(pizzaList);

		Produtos produtos = new Produtos(1L, 1,20,estoqueProds);
		Produtos produtos2 = new Produtos(2L, 10, 100, estoqueProds2);
		produtosList = new ArrayList<>();
		produtosList.add(produtos);
		produtosList.add(produtos2);

		Mockito.when(produtosRep.save(produtos)).thenReturn(produtos);
		Mockito.when(produtosRep.save(produtos2)).thenReturn(produtos2);
		Mockito.when(produtosRep.findById(1L)).thenReturn(Optional.of(produtos));
		Mockito.when(produtosRep.findById(2L)).thenReturn(Optional.of(produtos2));
		Mockito.when(produtosRep.findAll()).thenReturn(produtosList);

		Pedido pedido = new Pedido(1L,false,Status.A_CAMINHO,pizzaList,produtosList,20,false,
				true,funcionario,"obs", LocalDateTime.now(),usuario, LocalDate.now(),true);
		Pedido pedido2 = new Pedido(2L,false,Status.A_CAMINHO,pizzaList,produtosList,20,false,
				true,funcionario2,"Obs2",LocalDateTime.now(),usuario2,LocalDate.now(),true);
		pedidoList = new ArrayList<>();
		pedidoList.add(pedido);
		pedidoList.add(pedido2);

		Mockito.when(pedidoRep.save(pedido)).thenReturn(pedido);
		Mockito.when(pedidoRep.save(pedido2)).thenReturn(pedido2);
		Mockito.when(pedidoRep.findById(1L)).thenReturn(Optional.of(pedido));
		Mockito.when(pedidoRep.findById(2L)).thenReturn(Optional.of(pedido2));
		Mockito.when(pedidoRep.findAll()).thenReturn(pedidoList);

		Mockito.when(pedidoRep.findByStatus(Status.ATIVO)).thenReturn(pedidoList);
		Mockito.when(pedidoRep.findByDelivery(true)).thenReturn(pedidoList);
	}
	@Test
	void testCriaUser(){
		var usuario = usuarioController.cadastrarUser(new UsuarioDTO("Lindao","4599999999"));

		Assertions.assertEquals("Usuario cadastrado com sucesso!!", usuario.getBody());
	}
	@Test
	void UsuarioErradoCriarTest(){
		var usuario = usuarioController.cadastrarUser(new UsuarioDTO());
		Assertions.assertEquals("Error: Cannot invoke \"String.length()\" because the return value of \"br.com.uniamerica.pizzariaback.entity.Usuario.getTelefone()\" is null",usuario.getBody());
	}
	@Test
	void testPUTusuario(){
		Usuario usuario = new Usuario(1L,"nome","999999999");
		var editaUser = usuarioController.editarUsuario( usuario);
		Assertions.assertEquals("USUARIO editado com Sucesso",editaUser.getBody());
	}
	@Test
	void testPUTerradoUSuario(){
		Usuario usuario = new Usuario(1L,"nome","999999999");
		var editaUser = usuarioController.editarUsuario( usuario);
		Assertions.assertEquals("Error: Nao foi possivel indentificar o usuario informado",editaUser.getBody());

	}

	@Test
	void testCadastraUserVazio(){
		UsuarioDTO usuarioDTO = new UsuarioDTO("","45998036059");
		ResponseEntity<String> responseEntity = usuarioController.cadastrarUser(usuarioDTO);
		Assertions.assertEquals("Error: Nome nao pode ser nulo!!", responseEntity.getBody());
	}

	@Test
	void testExcluirUsuarioInexistente(){
		ResponseEntity<HttpStatus> responseEntity = usuarioController.deletaUsuario(999L);
		Assertions.assertEquals("Error: Não foi possivel identificar o usuario informado.", responseEntity.getBody());


	}


	@Test
	void DeleteUser(){
		var usuario = usuarioController.deletaUsuario(1L);
		Assertions.assertEquals("usuario Excluido", usuario.getBody());
	}
	@Test
	void DeleteUserErrado(){
		var usuario = usuarioController.deletaUsuario(10L);
		Assertions.assertEquals("Error: Não foi possivel identificar o usuario informado.",usuario.getBody());
	}

	@Test
	void findByIdUser(){
		usuarioController.cadastrarUser(new UsuarioDTO("Louco","45999999999"));
		var user = usuarioController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(user.getBody()).getNomeUsuario(), Objects.requireNonNull(usuarioController.findByIdPath(1L).getBody()).getNomeUsuario());
	}

	@Test
	void findAllUser(){
		ResponseEntity<List<Usuario>> usuarioFuncaoController = usuarioController.listaUsers();
		List<Usuario> usuarioListController = usuarioFuncaoController.getBody();

		Assertions.assertNotNull(usuarioListController);
		for (int i=0; i<usuarioList.size();i ++){
			Assertions.assertEquals(usuarioList.get(i), usuarioListController.get(i));
		}
	}

	@Test
	 void testCriaEndereco(){
		Usuario usuarioTest = new Usuario(3L, "Terceiro","994038950");
		var	endereco = enderecoController.cadastrarEndereco(new EnderecoDTO("RuaTOMA","BairroTOMA",530,usuarioTest));
		Assertions.assertEquals("Endereco cadastrado com sucesso!", endereco.getBody());
	}
	@Test
	void criaErradoEndereco(){
		var endereco = enderecoController.cadastrarEndereco(new EnderecoDTO());
		Assertions.assertEquals("Error: Bairro nao pode ser nulo!!",endereco.getBody());
	}
	@Test
	 void testPUTendereco(){
		Usuario usuarioTestPut = new Usuario(4L, "Quarto", "444444444");
		EnderecoDTO enderecoDTO = new EnderecoDTO("RuaPUT", "BairroPUT", 666, usuarioTestPut);
		enderecoDTO.setId(1L);

		var endereco = enderecoController.editaEnd( enderecoDTO);
		Assertions.assertEquals("Registro EDITADO com sucesso!!", endereco.getBody());
	}
	@Test
	void enderecoPUTerradoTest(){
		Usuario usuarioTestPut = new Usuario(4L, "Quarto", "444444444");
		EnderecoDTO enderecoDTO = new EnderecoDTO("RuaPUT", "BairroPUT", 666, usuarioTestPut);
		enderecoDTO.setId(1L);

		var endereco = enderecoController.editaEnd( enderecoDTO);
		Assertions.assertEquals("Nao foi possivel indentificar o endereco informado",endereco.getBody());
	}
	@Test
	void testDELETEendereco(){
		var endereco = enderecoController.deletaEnd(2L);
		Assertions.assertEquals("Endereco Exluido com Sucesso!", endereco.getBody());
	}
	@Test
	void deleteErradoEnderecoTest(){
		var endereco = enderecoController.deletaEnd(20L);
		Assertions.assertEquals("Error: Não foi possivel identificar o endereco informado.",endereco.getBody());
	}

	@Test
	void testFindByIDendereco(){
		Usuario usuario = new Usuario(5L,"QUINTO", "45999335511");
		enderecoController.cadastrarEndereco(new EnderecoDTO("RuaTOMA","BairroTOMA",530,usuario));
		var endereco = enderecoController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(endereco.getBody()).getRua(), Objects.requireNonNull(enderecoController.findByIdPath(1L).getBody()).getRua());
	}

	@Test
	void testFindAllEndereco(){
		ResponseEntity<List<Endereco>> enderecoFuncaoController = enderecoController.listaEnderecos();
		List<Endereco> enderecoListaController = enderecoFuncaoController.getBody();

		Assertions.assertNotNull(enderecoListaController);
		for (int i = 0; i< enderecoList.size(); i ++){
			Assertions.assertEquals(enderecoList.get(i), enderecoListaController.get(i));
		}
	}

	@Test
	void testCriaFuncionario(){
		var funcionario = funcionarioController.cadastrarFuncionario(new FuncionarioDTO("Morador"));
		Assertions.assertEquals("Registro cadastrado com sucesso",funcionario.getBody());
	}
	@Test
	void funcionarioCriarErrado(){
		var funcionario = funcionarioController.cadastrarFuncionario(new FuncionarioDTO());
		Assertions.assertEquals("Error: Cannot invoke \"String.equals(Object)\" because the return value of \"br.com.uniamerica.pizzariaback.entity.Funcionario.getNomeFunc()\" is null",funcionario.getBody());
	}
	@Test
	void testPUTfuncionario(){
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO("FuncionarioPUT");
		funcionarioDTO.setId(1L);

		var funcionario = funcionarioController.editaFunc( funcionarioDTO);
		Assertions.assertEquals("Registro EDITADO com Sucesso", funcionario.getBody());
	}
	@Test
	void erradoPUTtest(){
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO("FuncionarioPUT");
		funcionarioDTO.setId(1L);

		var funcionario = funcionarioController.editaFunc( funcionarioDTO);
		Assertions.assertEquals("Nao foi possivel indentificar o registro informado", funcionario.getBody());
	}

	@Test
	void testDELETEfunc(){
		var funcionarioDelete = funcionarioController.deletaFuncionario(1L);
		Assertions.assertEquals("Funcionário excluído",funcionarioDelete.getBody());
	}
	@Test
	void deleteErradoFuncTest(){
		var funcionarioDelete = funcionarioController.deletaFuncionario(11L);
		Assertions.assertEquals("Error: Não foi possivel identificar o funcionario informado.", funcionarioDelete.getBody());
	}

	@Test
	void testFindByIdFuncionario(){
		funcionarioController.cadastrarFuncionario(new FuncionarioDTO("Ichigo"));
		var funcionario = funcionarioController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(funcionario.getBody()).getNomeFunc(), Objects.requireNonNull(funcionarioController.findByIdPath(1L).getBody()).getNomeFunc());
	}

	@Test
	void testFindAllFuncionario(){
		ResponseEntity<List<Funcionario>> funcionarioFuncaoController = funcionarioController.listaFuncionarios();
		List<Funcionario> funcionariosListController = funcionarioFuncaoController.getBody();

		Assertions.assertNotNull(funcionariosListController);
		for (int i = 0; i<funcionarioList.size(); i++){
			Assertions.assertEquals(funcionarioList.get(i), funcionariosListController.get(i));
		}
	}

	@Test
	void testCadastraEstoque(){
		var estoque = estoqueProdsController.cadastrarEstoque(new EstoqueDTO(55,"Sorvete"));
		Assertions.assertEquals("Produto cadastrado com sucesso", estoque.getBody());
	}
	@Test
	void cadastraErradoEstoqueTest(){
		var estoque = estoqueProdsController.cadastrarEstoque(new EstoqueDTO());
		Assertions.assertEquals("Error: Cannot invoke \"String.equals(Object)\" because the return value of \"br.com.uniamerica.pizzariaback.entity.EstoqueProds.getNomeProduto()\" is null",estoque.getBody());
	}
	@Test
	void testPUTestoque(){
		EstoqueProds estoqueProds = new EstoqueProds(1L, 20,"DeuCerto");
		var estoque = estoqueProdsController.editarEstoque(estoqueProds);
		Assertions.assertEquals("Produto editado no estoque com Sucesso",estoque.getBody());
	}
	@Test
	void testPUTestoqueErrado(){
		EstoqueProds estoqueProds = new EstoqueProds(1L, 20,"DeuCerto");
		var estoque = estoqueProdsController.editarEstoque(estoqueProds);
		Assertions.assertEquals("Error: Nao foi possivel indentificar o registro informado",estoque.getBody());
	}
	@Test
	void testDELETEestoque(){
		var estoque = estoqueProdsController.deletaNoEstoque(2L);

		Assertions.assertEquals("Produto excluído com sucesso!!", estoque.getBody());
	}
	@Test
	void deleteEstoqueErrado(){
		var estoque = estoqueProdsController.deletaNoEstoque(10L);

		Assertions.assertEquals("Error: Não foi possivel identificar o modelo informado.", estoque.getBody());
	}

	@Test
	void testFindByIdEstoque(){
		estoqueProdsController.cadastrarEstoque(new EstoqueDTO(4,"Danone"));
		var estoque = estoqueProdsController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(estoque.getBody()).getNomeProduto(), Objects.requireNonNull(estoqueProdsController.findByIdPath(1L).getBody()).getNomeProduto());
	}

	@Test
	void testeFindAllEstoque(){
		ResponseEntity<List<EstoqueProds>> estoqueFuncaoController = estoqueProdsController.listaCompleta();
		List<EstoqueProds> estoqueListController = estoqueFuncaoController.getBody();

		Assertions.assertNotNull(estoqueListController);
		for (int i=0; i < estoqueProdsList.size(); i++){
			Assertions.assertEquals(estoqueProdsList.get(i), estoqueListController.get(i));
		}
	}

	@Test
	void testCriarSabores(){
		var sabor = saboresController.cadastrarSabores(new SaboresDTO("CalabresaTeste"));
		Assertions.assertEquals("Registro cadastrado com sucesso", sabor.getBody());
	}
	@Test
	void criaSaboresErrado(){
		var sabor = saboresController.cadastrarSabores(new SaboresDTO());
		Assertions.assertEquals("Error: Cannot invoke \"String.equals(Object)\" because the return value of \"br.com.uniamerica.pizzariaback.entity.Sabores.getSaborPizza()\" is null",sabor.getBody());
	}
	@Test
	void putSabores(){
		Sabores sabores = new Sabores(1L,"Teste");
		var sabor = saboresController.editaSabor( sabores);
		Assertions.assertEquals("Sabor editado com Sucesso",sabor.getBody());
	}
	@Test
	void putSaboresErrado(){
		Sabores sabores = new Sabores(2L,"putErro");
		var sabor = saboresController.editaSabor(sabores);
		Assertions.assertEquals("Error: Nao foi possivel indentificar o registro informado",sabor.getBody());
	}
	@Test
	void testDELETEsabores(){
		var sabor = saboresController.deletaSabor(2L);
		Assertions.assertEquals("Sabor excluído com sucesso!!", sabor.getBody());
	}
	@Test
	void testDeleteErrado(){
		var sabor = saboresController.deletaSabor(27L);
		Assertions.assertEquals("Error: Não foi possivel identificar o sabor informado.",sabor.getBody());
	}
	@Test
	void FindByIdSabores(){
		saboresController.cadastrarSabores(new SaboresDTO("PizzaID"));
		var sabor = saboresController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(sabor.getBody()).getSaborPizza(), Objects.requireNonNull(saboresController.findByIdPath(1L).getBody()).getSaborPizza());
	}
	@Test
	void testFindAllSabores(){
		ResponseEntity<List<Sabores>> saborFuncaoController = saboresController.listaSabores();
		List<Sabores> saboresListController = saborFuncaoController.getBody();
		Assertions.assertNotNull(saboresListController);
		for (int i = 0; i < saboresList.size(); i++){
			Assertions.assertEquals(saboresList.get(i), saboresListController.get(i));
		}
	}

	@Test
	void testCriarLogin(){
		var login = loginController.cadastrarLogin(new LoginDTO("loginTeste", "senhaTeste"));
		Assertions.assertEquals("Login cadastrado com sucesso", login.getBody());
	}
	@Test
	void testCriaLoginErrado(){
		var login = loginController.cadastrarLogin(new LoginDTO());
		Assertions.assertEquals("Error: Cannot invoke \"String.length()\" because the return value of \"br.com.uniamerica.pizzariaback.entity.Login.getNomeLogin()\" is null",login.getBody());
	}
	@Test
	void testPUTLogin(){
		Login login = new Login(1L,"admin","admin");
		var loginPUT = loginController.editarUser(1L, login);
		Assertions.assertEquals("LOGIN editado com Sucesso",loginPUT.getBody());
	}
	@Test
	void testPutLoginErrado(){
		Login login = new Login(2L,"admin","admin");
		var loginError = loginController.editarUser(10L,login);
		Assertions.assertEquals("Error: Nao foi possivel indentificar o login informado",loginError.getBody());
	}
	@Test
	void testDELETElogin(){
		var login = loginController.deleta(2L);
		Assertions.assertEquals("Login Excluído com sucesso!!!", login.getBody());
	}
	@Test
	void testDELETEloginERRADO(){
		var login = loginController.deleta(100L);
		Assertions.assertEquals("Error: Não foi possivel identificar o login informado.",login.getBody());
	}
	@Test
	void testFindByIdLogin(){
		loginController.cadastrarLogin(new LoginDTO("teste","teste"));
		var login = loginController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(login.getBody()).getNomeLogin(), Objects.requireNonNull(loginController.findByIdPath(1L).getBody()).getNomeLogin());
	}
	@Test
	void testFindAllLogin(){
		ResponseEntity<List<Login>> loginFuncaoController = loginController.listaLogin();
		List<Login> loginListController = loginFuncaoController.getBody();

		Assertions.assertNotNull(loginListController);
		for (int i = 0; i<loginList.size(); i++){
			Assertions.assertEquals(loginList.get(i), loginListController.get(i));
		}
	}

	@Test
	void testCriarPizza(){
		var pizza = pizzaController.cadastrar(new PizzaDTO(saboresList,30,2,Tamanho.M));
		Assertions.assertEquals("Pizza cadastrada com sucesso", pizza.getBody());
	}
	@Test
	void testCriarPizzaERRADO(){
		var pizza = pizzaController.cadastrar(new PizzaDTO());
		Assertions.assertEquals("Error: Selecione ao menos um sabor na pizza!!",pizza.getBody());
	}
	@Test
	void testPUTpizza(){
		PizzaDTO pizzaDTO = new PizzaDTO(saboresList,55,1,Tamanho.GG);
		pizzaDTO.setId(1L);
		var pizza = pizzaController.editaPizza( pizzaDTO);
		Assertions.assertEquals("pizza EDITADA com Sucesso", pizza.getBody());
	}
	@Test
	void testPUTpizzaErrado(){
		PizzaDTO pizzaDTO = new PizzaDTO(saboresList,55,1,Tamanho.GG);
		pizzaDTO.setId(1L);
		var pizza = pizzaController.editaPizza(pizzaDTO);
		Assertions.assertEquals("Nao foi possivel indentificar a pizza informado", pizza.getBody());
	}

	@Test
	void testDELETEpizza(){
		var pizza = pizzaController.deleta(2L);
		Assertions.assertEquals("Pizza excluída!!", pizza.getBody());
	}
	@Test
	void testeDeletePizzaErrado(){
		var pizza = pizzaController.deleta(500L);
		Assertions.assertEquals("Error: Não foi possivel identificar o pizza informado.",pizza.getBody());
	}

	@Test
	void testFindByIDpizza(){
		pizzaController.cadastrar(new PizzaDTO(saboresList,20,1, Tamanho.P));
		var pizza = pizzaController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(pizza.getBody()).getSabores(), Objects.requireNonNull(pizzaController.findByIdPath(1L).getBody()).getSabores());
	}
	@Test
	void testFindAllPizza(){
		ResponseEntity<List<Pizza>> pizzaFuncaoController = pizzaController.listaCompleta();
		List<Pizza> pizzaListController = pizzaFuncaoController.getBody();

		Assertions.assertNotNull(pizzaListController);
		for (int i = 0; i< pizzaList.size(); i++){
			Assertions.assertEquals(pizzaList.get(i),pizzaListController.get(i));
		}
	}

	@Test
	void testCriarProduto(){
		EstoqueProds estoqueProds = new EstoqueProds(2L,30,"AcaiTerra");
		var produto = produtosController.cadastrarProdutos(new ProdutosDTO(3,33,estoqueProds));
		Assertions.assertEquals("Produto feito com sucesso", produto.getBody());
	}
	@Test
	void testCriaProdutoERRADO(){
		EstoqueProds estoqueProds = new EstoqueProds(2L,30,"AcaiTerra");
		var produto = produtosController.cadastrarProdutos(new ProdutosDTO());
		Assertions.assertEquals("Error: A quantidade do produto não pode ser nula!!",produto.getBody());
	}

	@Test
	void testPUTproduto(){
		EstoqueProds estoqueProds = new EstoqueProds(5L,55,"NemSei");
		ProdutosDTO produtosDTO =new ProdutosDTO(10,20,estoqueProds);
		produtosDTO.setId(1L);
		var produto = produtosController.edita(1L, produtosDTO);
		Assertions.assertEquals("Produto Editado com Sucesso",produto.getBody());
	}
	@Test
	void testPUTprodutoERRADO(){
		EstoqueProds estoqueProds = new EstoqueProds(5L,55,"NemSei");
		ProdutosDTO produtosDTO =new ProdutosDTO(10,20,estoqueProds);
		produtosDTO.setId(1L);
		var produto = produtosController.edita(6L, produtosDTO);
		Assertions.assertEquals("Nao foi possivel indentificar o registro informado",produto.getBody());
	}

	@Test
	void testDELETEproduto(){
		var produtoDelete = produtosController.deletaProdutos(2L);
		Assertions.assertEquals("Produto Excluido Com Sucesso",produtoDelete.getBody());
	}
	@Test
	void testDELETEprodutoERRADO(){
		var produtoDelete = produtosController.deletaProdutos(221L);
		Assertions.assertEquals("Error: Não foi possivel identificar o produto informado.",produtoDelete.getBody());
	}

	@Test
	void testFindByIdProduto(){
		EstoqueProds estoqueProds = new EstoqueProds(8L,40,"ProdutoX");
		produtosController.cadastrarProdutos(new ProdutosDTO(7,70,estoqueProds));
		var produtoId = produtosController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(produtoId.getBody()).getEstoqueProds(), Objects.requireNonNull(produtosController.findByIdPath(1L).getBody()).getEstoqueProds());
	}
	@Test
	void testFindallProduto(){
		ResponseEntity<List<Produtos>> produtoFuncaoController = produtosController.listaProdutos();
		List<Produtos> produtoListController = produtoFuncaoController.getBody();

		Assertions.assertNotNull(produtoListController);
		for (int i = 0; i< produtosList.size(); i++){
			Assertions.assertEquals(produtosList.get(i),produtoListController.get(i));
		}
	}

	@Test
	void testCriarPedido(){
		Usuario usuario = new Usuario(1L,"Gabriel","45998036059");
		Funcionario funcionarioPed = new Funcionario(1L,"Funcionario");

		var pedido = pedidoController.cadastrarPedido(new PedidoDTO(true,Status.A_CAMINHO,pizzaList,produtosList,20,
				false,false,funcionarioPed,"teste",usuario,false,LocalDate.now()));
		Assertions.assertEquals("Pedido cadastrado com sucesso!!", pedido.getBody());
	}
	@Test
	void testCriarPedidoERRADO(){
		Usuario usuario = new Usuario(1L,"Gabriel","45998036059");
		Funcionario funcionarioPed = new Funcionario(1L,"Funcionario");
		var pedido = pedidoController.cadastrarPedido(new PedidoDTO());
		Assertions.assertEquals("Pedido cadastrado com sucesso!!",pedido.getBody());
	}

	@Test
	void testPUTpedido(){
		Usuario usuario = new Usuario(1L,"usuarioPUT", "45998306020");
		Funcionario funcionarioPut = new Funcionario(1L,"funcionarioPUT");
		PedidoDTO pedidoDTO = new PedidoDTO(true,Status.A_CAMINHO,pizzaList,produtosList,20,
				false,false,funcionarioPut,"teste",usuario,false,LocalDate.now());
		pedidoDTO.setId(1L);

		var pedido = pedidoController.editaPedido(1L, pedidoDTO);
		Assertions.assertEquals("Pedido EDITADO com sucesso!!", pedido.getBody());
	}
	@Test
	void testPUTpedidoErrado(){

		Usuario usuarioErrado = new Usuario(1L, "UsuarioPutErro", "990903333");
		Funcionario funcionarioErrado = new Funcionario(1L, "funcPUTerrado");
		PedidoDTO pedidoDTO = new PedidoDTO(true,Status.A_CAMINHO,pizzaList,produtosList,20,
				false,false,funcionarioErrado,"teste",usuarioErrado,false,LocalDate.now());
		pedidoDTO.setId(1L);

		var pedido = pedidoController.editaPedido(10L, pedidoDTO);
		Assertions.assertEquals("Nao foi possivel indentificar o pedido informado",pedido.getBody());
	}
	@Test
	void testDELETEpedido(){
		var pedido = pedidoController.deletaPedido(2L);
		Assertions.assertEquals("Pedido Excluido com sucesso!!",pedido.getBody());
	}
	@Test
	void testDELETEpedidoERRADO(){
		var pedido = pedidoController.deletaPedido(100L);
		Assertions.assertEquals("Error: Não foi possivel identificar o pedido informado.",pedido.getBody());
	}

	@Test
	void FindByIDpedido(){
		Usuario usuarioTest = new Usuario(1L,"nome","999999999");
		Funcionario funcionarioTest = new Funcionario(1L,"David");
		pedidoController.cadastrarPedido(new PedidoDTO(true,Status.A_CAMINHO,pizzaList,produtosList,20,
				false,false,funcionarioTest,"teste",usuarioTest,false,LocalDate.now()));

		var pedido = pedidoController.findByIdPath(1L);
		Assertions.assertEquals(Objects.requireNonNull(pedido.getBody()).getUsuario(), pedidoController.findByIdPath(1L).getBody().getUsuario());
	}

	@Test
	void FindAllPedidos(){
		ResponseEntity<List<Pedido>> pedidoFuncaoController = pedidoController.listaPedidos();
		List<Pedido> pedidoListController = pedidoFuncaoController.getBody();
		Assertions.assertNotNull(pedidoListController);
		for (int i = 0; i < pedidoList.size(); i++){
			Assertions.assertEquals(pedidoList.get(i),pedidoListController.get(i));
		}
	}

	@Test
	void testFindEmAndamento(){
		ResponseEntity<List<Pedido>> pedidoFuncaoController = pedidoController.solicitados();
		List<Pedido> pedidoListController = pedidoFuncaoController.getBody();
		Assertions.assertNotNull(pedidoListController);
		for (int i = 0; i< pedidoList.size(); i++){
			Assertions.assertEquals(pedidoList.get(i), pedidoListController.get(i));
		}
	}
	@Test
	void testFindDelivery(){
		ResponseEntity<?> responseEntity = pedidoController.delivery(true);
		Assertions.assertNotNull(responseEntity);
		Assertions.assertTrue(responseEntity.getBody() instanceof Map);
	}

}
