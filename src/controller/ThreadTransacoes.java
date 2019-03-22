package controller;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class ThreadTransacoes extends Thread 
{
	
	private int idTransacao;
	private double valor;
	private double saldo;
	private static int [] transacao = new int [2];
	private Semaphore semaforo;
	
	public ThreadTransacoes(int idTransacao, double valor, double saldo, Semaphore semaforo)
	{
		
		this.idTransacao = idTransacao;
		this.valor = valor;
		this.saldo = saldo;
		this.semaforo = semaforo;
		
	}
	@Override
	public void run() 
	{
		try 
		{
			semaforo.acquire();
			if(transacao[idTransacao] == 0) 
			{
				transacao[idTransacao]++;
				processaTransacao();
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		finally	
		{
			transacao[idTransacao]--;
			semaforo.release();
		}
	}
	private void processaTransacao() 
	{
		Locale local = new Locale("pt", "BR");
		NumberFormat nf = NumberFormat.getCurrencyInstance(local);
		switch(idTransacao) 
		{
			case 1: if(saldo > valor) 
					{
						saldo -= valor;
						System.out.println("Novo Balanço da Conta = " + nf.format(saldo) + " após o saque de " + nf.format(valor));
					}
					else 
					{
						System.out.println("Saldo insuficiente!!!");
					}
				
					break;
			case 2: saldo += valor;
					System.out.println("Novo Balanço da Conta = " + nf.format(saldo) + " após o depósito de " + nf.format(valor));
					break;
		}
	}
}
