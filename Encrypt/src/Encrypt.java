import java.nio.charset.StandardCharsets;
import java.util.*;
import java.security.SecureRandom;
public class Encrypt {
	public static void main(String[] args) {
		String s = "aφや館л�";
		s = addTrash(s, "{5Z");
		System.out.println(s);
		s = cutTrash(s, "{5Z");
		System.out.println(s);
	}
	public String in(String s, String key) {
		String code;
		return code;
	}
	public String out(String code, String key) {
		String s;
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
		int g = key.charAt(1) % 16;
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
		int g = key.charAt(1) % 16;
		String cleaned = "";
		for(int i = 0; i < s.length(); i += g + 1) {
			cleaned += s.substring(i, i + 1);
		}
		return cleaned;
	}
	private String scat(String s, String key) {
		
	}
	private String sort(String s, String key) {
		
	}
	private static String randomChar() {
		String s = "";
		SecureRandom r = new SecureRandom();
		s += (char) r.nextInt(Character.MAX_CODE_POINT);
		return s;
	}
}