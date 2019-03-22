package view;

import java.util.concurrent.Semaphore;

import java.util.Random;

import controller.ThreadTransacoes;

public class Main 
{
	public static void main(String[] args) 
	{
		int permissoes = 2;
		Random R = new Random();
		Semaphore semaforo = new Semaphore(permissoes);
		for(int CTA = 0; CTA<20;CTA++) 
		{
			Thread t = new ThreadTransacoes((R.nextInt(1)+ 1), (R.nextDouble()*10000), (R.nextDouble()), semaforo);
		}
	}
}
