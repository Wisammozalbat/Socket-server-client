package com.wamt.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{

	public static void main(String[] args) throws IOException {
		int PUERTO = 1234;
		ServerSocket servidor = new ServerSocket(PUERTO);
		Socket sc = null;
		int cont = 0;
		System.out.println("Servidor conectado");
	while (true) {
		sc = servidor.accept();
		cont++;
		ServidorManager scManag = new ServidorManager(sc, cont - 1);
	}
		
	}
		
}