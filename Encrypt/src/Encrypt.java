import java.nio.charset.StandardCharsets;
import java.util.*;
public class Encrypt {
	public static void main(String[] args) {
		String s = "aφや館";
		s = toBinary(s);
		System.out.println(s);
		s = fromBinary(s);
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
		byte[] b = new byte[l.size()];
		for(int i = 0; i < l.size(); i++) {
			b[i] = l.get(i).byteValue();
		}
		return new String(b, StandardCharsets.UTF_8);
	}
	private String addTrash(String s, String key) {
		
	}
	private String cutTrash(String s, String key) {
		
	}
	private String scat(String s, String key) {
		
	}
	private String sort(String s, String key) {
		
	}
}