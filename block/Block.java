package block;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

	String hash;
	String previousHash;
	String data;
	Long timestamp; 
	Integer blockid;
	static int count = 0;
	
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timestamp = System.currentTimeMillis();
		this.hash = this.CalculateHash();
		count = count + 1;
		this.blockid = count;

	}
		
	public String getHash() {
		return this.hash;
	}
	public String getPreviousHash() {
		return this.previousHash;
	}
	public String getData() {
		return this.data;
	}
	public String getBlockId() {
		return this.blockid.toString();
	}
	public Long getTimeStamp() {
		return this.timestamp;
	}

	
	public static String StringUtil(String input) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte[] messageDigest = md.digest(input.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;

		}

		catch (NoSuchAlgorithmException e) {
			System.out.println("Exception thrown"
								+ "for incorrect algroithm: " + e);

			return e.toString();
		}

	}
	
	public String CalculateHash() {
		
		String ts = this.getTimeStamp().toString();
		String vals = this.previousHash + ts + this.data;
		
		
		return StringUtil(vals);
	}
	
}
