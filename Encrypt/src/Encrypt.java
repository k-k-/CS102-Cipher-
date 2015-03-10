import java.nio.charset.StandardCharsets;
import java.util.*;
import java.security.SecureRandom;
public class Encrypt {
	public static void main(String[] args) {
		String s = "aφや館л�";
		System.out.println(s);
		s = in(s, "やsл");
		System.out.println(s);
		s = out(s, "やsл");
		System.out.println(s);
	}
	public static String in(String s, String key) {
		s = addTrash(s, key);
		s = toBinary(s);
		s = scat(s, key);
		return s;
	}
	public static String out(String s, String key) {
		s = sort(s, key);
		s = fromBinary(s);
		s = cutTrash(s, key);
		return s;
	}
	private static String genKey() {
		String key = randomChar() + randomChar() + randomChar();
		return key;
	}
	private static String toBinary(String s) {
		String bin = "";
		for(int i = 0; i < s.length(); i++) {
			byte[] b = s.substring(i, i + 1).getBytes(StandardCharsets.UTF_8);
			for(byte a : b) {
				bin += String.format("%32s", Integer.toBinaryString(a)).replace(' ', '0').substring(24);
			}
		}
		return bin;
	}
	private static String fromBinary(String s) {
		ArrayList<Byte> l = new ArrayList<Byte>();
		for(int i = 0; i < s.length(); i += 8) {
			String bin = s.substring(i, i + 8);
			short a = (short) Integer.parseInt(bin, 2);
			byte v = (byte) a;
			l.add(new Byte(v));
		}
		byte[] b = byteArray(l);
		return new String(b, StandardCharsets.UTF_8);
	}
	private static byte[] byteArray(ArrayList<Byte> l) {
		byte[] b = new byte[l.size()];
		for(int i = 0; i < l.size(); i++) {
			b[i] = l.get(i).byteValue();
		}
		return b;
	}
	private static String addTrash(String s, String key) {
		int g = Math.abs((33 - key.charAt(0) * key.charAt(1) + key.charAt(2)) % 16);
		String buried = "";
		for(int i = 0; i < s.length(); i++) {
			buried += s.substring(i, i + 1);
			for(int t = 0; t < g; t++) {
				buried += randomChar();
			}
		}
		return buried;
	}
	private static String cutTrash(String s, String key) {
		int g = Math.abs((33 - key.charAt(0) * key.charAt(1) + key.charAt(2)) % 16);
		String cleaned = "";
		for(int i = 0; i < s.length(); i += g + 1) {
			cleaned += s.substring(i, i + 1);
		}
		return cleaned;
	}
	private static String scat(String s, String key) {
		int m = Math.abs((int) (Math.E * (key.charAt(0) / Math.abs(key.charAt(1)) + key.charAt(2) + 1)) % 20 + 2);
		int a = Math.abs((key.charAt(0) % key.charAt(2) - key.charAt(2) + 4) % s.length()) + 1;
		int t = Math.abs((key.charAt(0) * 24 % key.charAt(2) * key.charAt(1)) % 30 + 10) * s.length();
		if(s.length() % m == 0) m++;
		if(a == m) a--;
		for(int i = 0; i < t; i ++) {
			int j = i % s.length();
			if(i % m != 0) s = swap(s, j, (j + a) % s.length());
		}
		return s;
	}
	private static String sort(String s, String key) {
		int m = Math.abs((int) (Math.E * (key.charAt(0) / Math.abs(key.charAt(1)) + key.charAt(2) + 1)) % 20 + 2);
		int a = Math.abs((key.charAt(0) % key.charAt(2) - key.charAt(2) + 4) % s.length()) + 1;
		int t = Math.abs((key.charAt(0) * 24 % key.charAt(2) * key.charAt(1)) % 30 + 10) * s.length();
		if(s.length() % m == 0) m++;
		if(a == m) a--;
		for(int i = t - 1; i >= 0; i--) {
			int j = i % s.length();
			if(i % m != 0) s = swap(s, j, (j + a) % s.length());
		}		
		return s;
	}
	private static String swap(String s, int x, int y) {
		String r = "";
		int g = Math.max(x, y);
		int l = Math.min(x, y);
		for(int i = 0; i < s.length(); i++) {
			if(i == g) r += s.substring(l, l + 1);
			else if(i == l) r += s.substring(g, g + 1);
			else r += s.substring(i, i + 1);
		}
		return r;
	}
	private static String randomChar() {
		String s = "";
		SecureRandom r = new SecureRandom();
		s += (char) r.nextInt(Character.MAX_CODE_POINT);
		return s;
	}
}