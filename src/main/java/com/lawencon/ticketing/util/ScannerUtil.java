package com.lawencon.ticketing.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScannerUtil {
		public static float getScannerNumber(String desc, int range) {
			float choice = 0;
			String choiceString = "";
			Scanner scan = new Scanner(System.in);
			System.out.print(desc);

			if (range == 0) {
				choiceString = scan.nextLine().trim();
				while (!isValidNumber(choiceString)) {
					System.out.println("Input tidak valid mengandung spasi/huruf");
					System.out.print("Masukkan input baru : ");
					choiceString = scan.nextLine().trim();
				}
				choice = Float.parseFloat(choiceString);
			} else {
				choiceString = scan.nextLine().trim();
				while (!isValidNumber(choiceString)) {
					System.out.println("Input tidak valid mengandung spasi/huruf");
					System.out.print("Masukkan input baru : ");
					choiceString = scan.nextLine().trim();
				}
				choice = Float.parseFloat(choiceString);
				while (choice < 1 || choice > range) {
					System.out.println("Menu out of Range !! ");
					System.out.print("Masukkan input baru : ");
					choice = scan.nextInt();
				}
			}
			return choice;
		}

		public static boolean isValidNumber(String choiceString) {
			return !choiceString.isEmpty() && choiceString.matches("[-+]?[0-9]*\\.?[0-9]+");
		}

		public static String getScannerString(String desc) {
			String choiceString = "";
			Scanner scan = new Scanner(System.in);
			System.out.print(desc);
			choiceString = scan.nextLine().trim();
			while (!isValidString(choiceString)) {
				System.out.println("Input tidak valid mengandung spasi/karakter selain huruf");
				System.out.print("Masukkan input baru : ");
				choiceString = scan.nextLine().trim();
			}
			return choiceString;
		}

		public static boolean isValidString(String choiceString) {
			return !choiceString.isEmpty();
		}

		public static String getAlphaNumericString() {
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuilder sb = new StringBuilder(5);

			for (int i = 0; i < 5; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());
				sb.append(AlphaNumericString.charAt(index));
			}

			return sb.toString();
		}

		public static String getCurrentDateStamp() {
			SimpleDateFormat curDate = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String strDate = curDate.format(now);
			return strDate;
		}

		public static String getCurrentTimeStamp() {
			SimpleDateFormat curDate = new SimpleDateFormat("HH:mm a");
			Date now = new Date();
			String strDate = curDate.format(now);
			return strDate;
		}
	}