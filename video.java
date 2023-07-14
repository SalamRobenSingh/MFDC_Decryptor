
import java.util.*;
import java.io.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author ROBEN
 */
public class video{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        // TODO code application logic here
        FileInputStream fis = new FileInputStream(new File("E:/thamoi Ani@pre.mp4"));
        File outfile = new File("E:/MFDC/encVideo.mp4");
        int read;
        if(!outfile.exists())
            outfile.createNewFile();
        File decfile = new File("E:/MFDC/decVideo.mp4");
        if(!decfile.exists())
            decfile.createNewFile();
        FileOutputStream fos = new FileOutputStream(outfile);
        FileInputStream encfis = new FileInputStream(outfile);
        FileOutputStream decfos = new FileOutputStream(decfile);
        
        
        // KEY GENERATION
        String filmName="thamoi Ani";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(filmName.toString().substring(0, 4).toLowerCase());
        stringBuilder.append("567890123456");
        SecretKeySpec mSecretKeySpec = new SecretKeySpec(stringBuilder.toString().getBytes(), "AES");
        
        
        
        stringBuilder = new StringBuilder();
        stringBuilder.append("123456789012");
        stringBuilder.append(filmName.toString().substring(0, 4).toLowerCase());
        IvParameterSpec mIvParameterSpec = new IvParameterSpec(stringBuilder.toString().getBytes());
        // IvParameterSpec mIvParameterSpec=new IvParameterSpec(stringBuilder.toString().getBytes(), "AES");
        
        // CIPHER CREATION 
        Cipher encipher, decipher = Cipher.getInstance("AES/CTR/NoPadding");
        try {
        decipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec, mIvParameterSpec);
        } catch (Exception exception) {
            exception.printStackTrace();
        } 

        //KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //byte key[] = {0x00,0x32,0x22,0x11,0x00,0x00,0x00,0x00,0x00,0x23,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
        //SecretKey skey = kgen.generateKey();
        //Lgo
        //encipher.init(Cipher.ENCRYPT_MODE, skey);
        //CipherInputStream cis = new CipherInputStream(fis, encipher);
        //decipher.init(Cipher.DECRYPT_MODE, skey);
        CipherOutputStream cos = new CipherOutputStream(decfos,decipher);
//        while((read = cis.read())!=-1)
//                {
//                    fos.write((char)read);
//                    fos.flush();
//                }   
//        fos.close();
        while((read=encfis.read())!=-1)
        {
            cos.write(read);
            cos.flush();
        }
    cos.close(); 
    }
}
