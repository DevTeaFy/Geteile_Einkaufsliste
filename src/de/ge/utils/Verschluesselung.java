package de.ge.utils;

public class Verschluesselung {

	char[] buchstaben = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '!', '#', '$', '%', '&', '(', ')', '*', '+', '?', '=', '\'', '"', 'ß', 'ö', 'Ö', 'ü', 'Ü', 'ä', 'Ä',
			'<', '>', '^', '°', '\\', '/', '{', '}', '[', ']','´','.',',',':',';','-','~','²','³','|' };

	public String hash(String Password) {
		String hashPassword = "";
		String p = "";
		long b = 1;
		int d = 1;
		int wert = 15;
		int passwortbuchstaben = 11;
		for (int i = 0; i < Password.length(); i++) {
			b = b * d;
			for (int j = 0; j < buchstaben.length; j++) {

				if (Password.charAt(i) == buchstaben[j]) {
					d = j + wert;
					if (j + passwortbuchstaben >= buchstaben.length) {
						passwortbuchstaben = buchstaben.length - (j + passwortbuchstaben);
						if (passwortbuchstaben < 0) {
							passwortbuchstaben += j;
							p = p + buchstaben[passwortbuchstaben];
						} else {
							p = p + buchstaben[passwortbuchstaben];
						}

					} else {
						p = p + buchstaben[j + passwortbuchstaben];
					}

					j = buchstaben.length;
				}
			}
		}

		char[] v = Long.toString(b).toCharArray();
		if (v[0] == '-') {
			v[0] = Long.toString(b).charAt(5);
		}
		byte[] byt = Long.toString(b).getBytes();
		int x = 0;
		for (int i = 0; i < byt.length; i++) {
			hashPassword = hashPassword + p.charAt(x) + byt[i];
			x++;
			if (x >= p.length()) {
				x = 0;
			}
		}
		for (int i = 0; i < v.length; i++) {
			int m = Integer.valueOf(Character.toString(v[i]));
			hashPassword = hashPassword + p.charAt(x) + (m + x);
			x++;
			if (x >= p.length()) {
				x = 0;
			}
		}

		return hashPassword;

	}

	public boolean checkPassword(String password, String hash) {
		String pas = hash(password);
		return pas.equals(hash);
	}
}
