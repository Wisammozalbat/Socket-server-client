package com.wamt.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ServidorManager extends Thread{
	final int PUERTO = 1234;
	ServerSocket servidor = null;
	Socket sc = null;
	private int index;
	ArrayList <String> fileArray = new ArrayList<>();
	
	String path = "C:\\Users\\Wisam\\Documents\\2019B\\Programacion Visual\\Carpeta.Segura\\";
	
	public ServidorManager(Socket s, int cont) {
		sc = s;
		index = cont;
		start();
	}
	 
	public void run(){
		try {
			InputStreamReader in = new InputStreamReader(sc.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			String line = bf.readLine();
			while (!line.equals("stop")) {
				fileArray.add(line);
				line = bf.readLine();
			}

			System.out.println(fileArray.get(2));
			
			//createFile(fileArray.get(0), fileArray.get(1));
			
			PrintWriter pr = new PrintWriter(sc.getOutputStream());
			pr.println("Servidor Conected, file created for Cliente: " + index);
			fileArray.clear();
			pr.flush();
			bf.close();
			in.close();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Cliente desconectado");
		}
	
	public void createFile(String nombre, String contenido) throws IOException {
		contenido = contenido.trim().replaceAll("\"", "");
		nombre = nombre.trim().replaceAll("/", "\\\\");
		nombre += index;
		//contenido += " " + index;
		File f = new File(path + nombre + ".txt");
		if (f.exists()) {
			System.out.println("Ya el documento esta creado para el Cliente: " + index);
		}
		else {
			String data = contenido;
			Path pathToFile = Paths.get(path + nombre + ".txt");
			if (!Files.exists(pathToFile.getParent())) {
				Files.createDirectories(pathToFile.getParent());
			}
			Files.createFile(pathToFile);
			FileWriter writer = new FileWriter(f);
				writer.write(data);
				writer.close();
			}
	}
}
