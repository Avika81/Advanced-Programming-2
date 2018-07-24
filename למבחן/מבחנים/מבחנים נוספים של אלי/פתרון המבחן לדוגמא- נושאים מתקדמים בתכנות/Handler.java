package model;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler {

	Map<String,Integer> wordsCount;
	
	public Handler() {
		wordsCount=new HashMap<>();
	}
	
	public void start(int port) throws Exception{ // 15 points
		PrintWriter errorFile=new PrintWriter(new FileWriter("errors.txt",true));
		
		ServerSocket server=new ServerSocket(port);
		Socket client = server.accept();
		BufferedReader inFromClient=new BufferedReader(new InputStreamReader(client.getInputStream()));
		String line;
		while(!(line=inFromClient.readLine()).equals("done")){
			if(line.startsWith("ERROR:"))
				errorFile.println(line);
			else{
				countWords(line);
			}
		}
		inFromClient.close();
		client.close();
		server.close();
		errorFile.close();
		
		sendTop10Words();
	}

	// send the top 10 referenced words to the server
	private void sendTop10Words() throws Exception{ // 10 points
		Socket server=new Socket("10.0.0.139", 6400);
		PrintWriter outToServer=new PrintWriter(server.getOutputStream());
		List<String> top10=new ArrayList<>();
		top10.addAll(wordsCount.keySet());
		Collections.sort(top10,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return wordsCount.get(o2)-wordsCount.get(o1);
			}
		});
		for(int i=0;i<top10.size() && i<10;i++){
			outToServer.println(top10.get(i));
		}
		outToServer.close();
		server.close();
		
	}

	// adds the count for each word given a line 
	private void countWords(String line) { // 10 points
		for(String word : line.split(",")){
			if(wordsCount.get(word)!=null){
				int c=wordsCount.get(word);
				wordsCount.put(word, ++c);
			}else{
				wordsCount.put(word,1);
			}
		}
	}
	
}
