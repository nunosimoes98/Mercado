package view;

import model.produto;
import java.util.Scanner;

import helper.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Mercado {
	
	private static Scanner teclado = new Scanner(System.in);
	private static ArrayList<produto> produtos;
	private static Map<produto, Integer> carrinho;
	
	public static void main(String[] args) {
		produtos = new ArrayList<produto>();
		carrinho = new HashMap<produto, Integer>();
		Mercado.menu();
		
	}
	
	private static void menu() {
		System.out.println("=================================");
		System.out.println("=======   Bem vindo(a)  =========");
		System.out.println("========   Nuno Shop  ===========");
		System.out.println("Selecione uma opção abaixo: ");
		System.out.println("1 - Criar produto");
		System.out.println("2 - Listar produtos");
		System.out.println("3 - Comprar produto");
		System.out.println("4 - Visualizar carrinho");
		System.out.println("5 - Sair do sistema");
		
		int opcao = 0;
		
		try {
			opcao = Integer.parseInt(Mercado.teclado.nextLine());
		}catch(InputMismatchException e) {
			Mercado.menu();
		}catch(NumberFormatException e) {
			Mercado.menu();
		}
		
		switch(opcao) {
		case 1:
			Mercado.criarProduto();
			break;
		case 2:
			Mercado.listarProdutos();
			break;
		case 3:
			Mercado.comprarProduto();
			break;
		case 4:
			Mercado.visualizarCarrinho();
			break;
		case 5:
			System.out.println("Volte sempre!");
			utils.pausar(2);
			System.exit(0);
			break;
		default:
			System.out.println("Opcao Invalida.");
			utils.pausar(2);
			Mercado.menu();
			break; 
		}
		
	}
	
	private static void criarProduto() {
		System.out.println("Criando produto...");
		System.out.println("==================");
		
		System.out.println("Nome do produto: ");
		String nome = Mercado.teclado.nextLine();
		
		System.out.println("Preço do produto: ");
		Double preco = Mercado.teclado.nextDouble();
		
		produto produto = new produto(nome,preco);
		
		Mercado.produtos.add(produto);
		
		System.out.println("O produto " + produto.getNome() + " foi criado com sucesso!");
		utils.pausar(2);
		Mercado.menu();
	}
	
	private static void listarProdutos() {
		if(Mercado.produtos.size() > 0) {
			System.out.println("Listagem de produtos:");
			System.out.println();
			
			for(produto p : Mercado.produtos) {
				System.out.println(p);
				System.out.println();
			}
		} else {
			System.out.println("Ainda não existem produtos criados");
		}
		
		utils.pausar(2);
		Mercado.menu();
	}
	
	private static void comprarProduto() {
		if(Mercado.produtos.size() > 0) {
			System.out.println("Informe o cód do produto que deseja comprar:");
			System.out.println();
			
			System.out.println("Produtos disponiveis:");
			System.out.println();
			for(produto p : Mercado.produtos) {
				System.out.println(p);
				System.out.println("----------------------");
			}
			int codigo = Integer.parseInt(Mercado.teclado.nextLine());
			boolean temNoCarrinho = false;
			
			for(produto p : Mercado.produtos) {
				if(p.getCodigo() == codigo) {
					int quant = 0;
					try {
						quant = Mercado.carrinho.get(p);
						Mercado.carrinho.put(p, quant+1);
					} catch(NullPointerException e) {
						Mercado.carrinho.put(p, 1);
					}
					
				
					System.out.println("O produto " + p.getNome() + " foi adicionado ao carrinho");
					temNoCarrinho = true;
				}
				
				if(temNoCarrinho) {
					System.out.println("Deseja adicionar outros produtos ao carrinho?:");
					System.out.println("Informe 2 para sim ou 0 para não:");
					int op = Integer.parseInt(Mercado.teclado.nextLine());
					
					if(op==1) {
						Mercado.comprarProduto();
					}else {
						System.out.println("Por favor, aguarde enquanto fechamos o seu pedido");
						utils.pausar(2);
						Mercado.fecharPedido();
					}
				} else {
					System.out.println("Não foi encontrado o produto com o codigo " + codigo);
					utils.pausar(2);
					Mercado.menu();
				}
			}

		} else {
			System.out.println("Ainda não existem produtos criados no mercado");
			utils.pausar(2);
			Mercado.menu();
		}
	}
	
	private static void visualizarCarrinho() {
		if(Mercado.carrinho.size() > 0) {
			System.out.println("Produtos do carrinho:");
			System.out.println();
			
			for(produto p : Mercado.carrinho.keySet()) {
				System.out.println("Produto: " + p + "\nQuantidade: " + Mercado.carrinho.get(p));
				System.out.println();
			}
		} else {
			System.out.println("Ainda não existem produtos no carrinho");
		}
		
		utils.pausar(2);
		Mercado.menu();
	}

	private static void fecharpedido() {
		Double valorTotal = 0.0;
		System.out.println("Produtos do carrinho:");
		System.out.println();
		
		for(produto p : Mercado.carrinho.keySet()) {
			int quant = Mercado.carrinho.get(p);
			valorTotal += p.getPreco() * quant;
			System.out.println(p);
			System.out.println("Quantidade: " + quant);
			System.out.println("--------------------");
		}
		System.out.println("Sua fatura é " + utils.doubleParaString(valorTotal));
		Mercado.carrinho.clear();
		System.out.println("Obrigado pela preferencia.");
		utils.pausar(5);
		Mercado.menu();
	}
}
