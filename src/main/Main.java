package main;

import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	
	private static final String REGEX_NUMBER = "\\d+";
	static final String Cancel="cancel";
//	static final String AddMoney="addmoney";
	
	static final String Biskuit="biskuit";
	static final String Chips="chip";
	static final String Oreo="oreo";
	static final String Tango="tango";
	static final String Cokelat="cokelat";
	
	static final long BiskuitPrice = 6000;
	static final long ChipsPrice = 8000;
	static final long OreoPrice = 10000;
	static final long TangoPrice = 12000;
	static final long CokelatPrice = 15000;
	
	public static void main(String[] args) {
		mainMenu();
	}
	
	//main menu
	public static void mainMenu() {
		System.out.println("----------selamat datang di vending machine------------");
		System.out.println("Menu");
		System.out.printf("%-15s%-15s%-15s\n","NO","Item","Harga");
		System.out.printf("%-15s%-15s%-15s\n","--","----","-----");
		System.out.printf("%-15s%-15s%-15s\n","1","Biskuit","6000");
		System.out.printf("%-15s%-15s%-15s\n","2","Chips","8000");
		System.out.printf("%-15s%-15s%-15s\n","3","Oreo","10000");
		System.out.printf("%-15s%-15s%-15s\n","4","Tango","12000");
		System.out.printf("%-15s%-15s%-15s\n","5","Cokelat","15000");
		System.out.println("-------------------------------------------------------");
		
		long cash= 0;
		
		long balance =0;
		//input money
		cash = inputMoney();
		
		balance=balance+cash;
		
		System.out.println("----------------------########--------------------------");
		System.out.println("Saldo Anda="+balance);
		
		//select item
		String itemSelected =choseItem();
		
		//check stock
		int stockItem = getStock(itemSelected);
		if(stockItem==0) {			
			do {
				System.out.println("Maaf Item yang Anda Pilih Habis, Silahkan pilih Item lainnya");
				itemSelected =choseItem();
				stockItem = getStock(itemSelected);
			} while (stockItem==0);
		}
		
		//get price
		long price = getPrice(itemSelected);
		if(price>balance) {			
			do {
				System.out.println("Maaf Saldo Anda Tidak Cukup");
				itemSelected =choseItem();
				stockItem = getStock(itemSelected);
				price = getPrice(itemSelected);
			} while (price>balance);
		}
		
		//display result
		displayResult(itemSelected, price, balance);
		
	}

	/*
	 * input money with Scanner class
	 */
	public static Long inputMoney() {
		Scanner scanner = new Scanner(System.in);
		long result =0;
//		Pattern p = Pattern.compile(REGEX_NUMBER);
//		List<Long> item = new ArrayList<Long>();
		int inputTemp = 0;
		long cash = 0;
		try {
			
			do {
				
				System.out.println("Silahkan Masukan Uang");
				System.out.print(">>");
				String temp = scanner.next();
				
				boolean rexNumn=temp.matches(REGEX_NUMBER);
				if(rexNumn) {
					cash=Long.parseLong(temp);
				}else {
					int rex =0;
					do {
						System.out.println("invalid Input");
						System.out.println(">>");
						temp = scanner.next();
						boolean rexNumn2=temp.matches(REGEX_NUMBER);
						rex = rexNumn2 ? 1 : 0;
						System.out.println("rex="+rex);
					}while(rex==0);
					cash=Long.parseLong(temp);
				}
				
				boolean val = validationCash(cash);

				if (val) {
					result=result+cash;
				} else {

					int valid = 0;
					do {
						System.out.println("Maaf Hanya menerima pecahan 2000, 5000, 10000, 20000, 50000");
						System.out.println("Masukan Uang");
						System.out.print(">>");
						cash = scanner.nextLong();
						boolean val2 = validationCash(cash);
						valid = val2 ? 1 : 0;

					} while (valid == 0);
					result=result+cash;
				}

				System.out.println("Apakah Anda ingn Memasukan Uang lagi ?(yes/no)");
				System.out.print(">>");
				int answerTemp=0;
				String answer = scanner.next();
				if (answer.equalsIgnoreCase("ya") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
					inputTemp = 1;
				} else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
					inputTemp = 0;
				}else {					
					do {
						System.out.println("Invalid Options");
						System.out.println("-------------------------------------------------------");
						System.out.println("Apakah Anda ingn Memasukan Uang lagi ?(yes/no)");
						System.out.print(">>");
						answer = scanner.next();
						if (answer.equalsIgnoreCase("ya") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
							inputTemp = 1;
							answerTemp=1;
						} else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
							inputTemp = 0;
							answerTemp=1;
						}else {
							answerTemp=0;
						}
						
					}while(answerTemp==0);
				}

			} while (inputTemp != 0);

		} finally {
			
		}
		
		return result;
	}
	
	public static String choseItem() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("-----------------Silahkan Pilih Item-------------------");
		System.out.printf("%-15s%-15s%-15s\n","NO","Item","Harga");
		System.out.printf("%-15s%-15s%-15s\n","--","----","-----");
		System.out.printf("%-15s%-15s%-15s\n","1","Biskuit","6000");
		System.out.printf("%-15s%-15s%-15s\n","2","Chips","8000");
		System.out.printf("%-15s%-15s%-15s\n","3","Oreo","10000");
		System.out.printf("%-15s%-15s%-15s\n","4","Tango","12000");
		System.out.printf("%-15s%-15s%-15s\n","5","Cokelat","15000");
		System.out.println("9. *Untuk Pembatalan transaksi*");
		String result="";
		int selected=0;
	      do {
	    	  System.out.print(">>");
	    	  String select= scanner.next();
	  
	    	  boolean rexNumn=select.matches(REGEX_NUMBER);
	    	  if(rexNumn) {
	    		  selected=Integer.parseInt(select);
				}else {
					int rex =0;
					do {
						System.out.println("invalid Input");
						System.out.println(">>");
						select = scanner.next();
						boolean rexNumn2=select.matches(REGEX_NUMBER);
						rex = rexNumn2 ? 1 : 0;
						System.out.println("rex="+rex);
					}while(rex==0);
					selected=Integer.parseInt(select);
				}
	    	  
	          // select item
	          switch (selected) {
	          case 1: // Biskuit
	        	  result= Biskuit;
	            break;
	          case 2: // Chip
	        	  result = Chips;
	        	  break;
	          case 3: // Oreo
	        	  result = Oreo;
	            break;
	          case 4: // Tango
	        	  result = Tango;
	            break;
	          case 5: // Coklat
	        	  result = Cokelat;
	            break;
	          case 9: // Cancel
	        	  result = Cancel;
	        	  break;
	          default:
	            System.out
	                .println("Invalid Option");
	          }
	        } while (selected == 0);
		if(selected==9) {
			mainMenu();
		}
		return result;
	}

	public static boolean validationCash(long uang) {
		boolean result = false;
		if (uang == 2000 || uang == 5000 || uang == 10000 || uang == 20000 || uang == 50000) {
			result = true;
		}
		return result;
	}
	public static int getStock(String item) {
		int result=0;
		if (item.equalsIgnoreCase(Biskuit)) {
			result = 1;
		}
		if (item.equalsIgnoreCase(Chips)) {
			result = 1;
		}
		if (item.equalsIgnoreCase(Oreo)) {
			result = 0;
		}
		if (item.equalsIgnoreCase(Tango)) {
			result = 3;
		}
		if (item.equalsIgnoreCase(Cokelat)) {
			result = 3;
		}
		
		return result;
	}

	public static long getPrice(String item) {
		long result = 0;
		if (item.equalsIgnoreCase(Biskuit)) {
			result = BiskuitPrice;
		}
		if (item.equalsIgnoreCase(Chips)) {
			result = ChipsPrice;
		}
		if (item.equalsIgnoreCase(Oreo)) {
			result = OreoPrice;
		}
		if (item.equalsIgnoreCase(Tango)) {
			result = TangoPrice;
		}
		if (item.equalsIgnoreCase(Cokelat)) {
			result = CokelatPrice;
		}

		return result;

	}

	public static long getCalculate(long price, long saldo) {
		long result = 0;
		result = saldo - price;

		return result;
	}
	
	public static void displayResult(String itemSelected, long price, long balance) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("#######################################################");
		System.out.println("---------Pembelian berhasil-------------");
		System.out.println("Item="+itemSelected);
		System.out.println("Harga="+price);
		System.out.println("Uang="+balance);
		long endingBalance = getCalculate(price, balance);
		System.out.println("Kembalian="+endingBalance);
		System.out.println("silahkan Ambil Barang dan Kembalian Anda");
		System.out.println("#######################################################");
		System.out.println(".");
		System.out.println(".");
		System.out.println(".");
		System.out.println(".");
		System.out.println("tekan y untuk transaksi kembali");
		System.out.print(">>");
		String answer = scanner.next();
		if (answer.equalsIgnoreCase("ya") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
			mainMenu();
		} else {	
			int answerTemp=0;
			do {
				System.out.println("Invalid Options");
				System.out.println("tekan y untuk transaksi kembali");
				System.out.print(">>");
				answer = scanner.next();
				if (answer.equalsIgnoreCase("ya") || answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
					mainMenu();
				} else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
					
					answerTemp=1;
				}else {
					answerTemp=0;
				}
				
			}while(answerTemp==0);
		}
		
	}
	

}
