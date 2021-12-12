import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.file.*;

class brute_force{

    public static int count = 0;
    public static String inputfile="ex02_pride.enc";
    public static String outputfile="output_pride.txt";

        static public synchronized boolean Decrypt(String inFileName, String outFileName, String password)
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
            System.out.println("Password is: "+password);
            return true;
        }


        public static void main(String args[]){
            S1 s1 = new S1();
            S2 s2 = new S2();
            S3 s3 = new S3();
            S4 s4 = new S4();
            s1.start();
            s2.start();
            s3.start();
            s4.start();
        }
        private static final String checker = "correct header";
    }
    class S1 extends Thread{
        public void run(){
            
            for(int i=0;i<25000;i++){
                //I can't find a good solution.
                brute_force.Decrypt(brute_force.inputfile,brute_force.outputfile,String.format("%02d",i));
                brute_force.Decrypt(brute_force.inputfile,brute_force.outputfile,String.format("%03d",i));
                brute_force.Decrypt(brute_force.inputfile,brute_force.outputfile,String.format("%04d",i));
                brute_force.Decrypt(brute_force.inputfile,brute_force.outputfile,String.format("%05d",i));
            }
        }
    }
    class S2 extends Thread{
        public void run(){
            for(int j=25000;j<50000;j++){
                brute_force.Decrypt(brute_force.inputfile,brute_force.outputfile,String.format("%05d",j));
            }
        }
    }
    class S3 extends Thread{
        public void run(){
            for(int k=50000;k<75000;k++){
                brute_force.Decrypt(brute_force.inputfile,brute_force.outputfile,String.format("%05d",k));
            }
        }
    }
    class S4 extends Thread{
        public void run(){
            for(int l=75000;l<100000;l++){
                brute_force.Decrypt(brute_force.inputfile,brute_force.outputfile,String.format("%05d",l));
            }
        }

}