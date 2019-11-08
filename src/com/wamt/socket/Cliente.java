package com.wamt.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Cliente {

	public static void main(String[] args) throws IOException{

		final String HOST = "localhost";
		final int PUERTO = 1234;
		String fileName = "Cliente", content = "Hola, soy el cliente ", msg = "Cliente conectado";
		ArrayList <String> aMsg = new ArrayList<>();
		aMsg.add(fileName);
		aMsg.add(content);
		aMsg.add(msg);
		aMsg.add("stop");
		
			Socket socket = new Socket(HOST,PUERTO);
			PrintWriter pr = new PrintWriter(socket.getOutputStream());
			for (int i = 0; i < aMsg.size(); i++) {
				pr.println(aMsg.get(i));
				pr.flush();
			}
			String str = null;
			
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			while (str == null) {
				str = bf.readLine();
			}
			System.out.println("server: "+ str);
			pr.close();
			socket.close();
	}
}
