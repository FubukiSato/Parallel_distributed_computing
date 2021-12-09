public class thread{
	public static void main(String[] args){
		S1 s1 = new S1();
		S2 s2 = new S2();
        S3 s3 = new S3();
        S4 s4 = new S4();
		s1.start();
		s2.start();
        s3.start();
        s4.start();
	}
}
class S1 extends Thread{
	public void run(){
		for(int i=0;i<25000;i++){
            String pass = String.valueOf(i);
            boolean cmp = Decrypt("ex02_longtail.enc","output.txt",pass);
        }
	}
}
class S2 extends Thread{
	public void run(){
		for(int j=25000;j<50000;j++){

        }
	}
}
class S3 extends Thread{
	public void run(){
		for(int k=50000;k<75000;k++){

        }
	}
}
class S4 extends Thread{
	public void run(){
		for(int k=75000;k<100000;k++){

        }
	}
}

class Decrypt{
static public boolean Decrypt(String inFileName, String outFileName, String password)
{
    try
    {
        byte[] checkedFile = Files.readAllBytes(Paths.get(inFileName));
        
        MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(password.getBytes());
        SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aes.init(Cipher.DECRYPT_MODE, key);
        String cleartext = new String(aes.doFinal(checkedFile));
        
        if(!cleartext.substring(0, checker.length()).equals(checker))
            throw new Exception();
    
        Files.write(Paths.get(outFileName), cleartext.substring(checker.length()).getBytes());

    }
    catch(Exception e)
    {
        return false;
    }
    
    return true;
}
}