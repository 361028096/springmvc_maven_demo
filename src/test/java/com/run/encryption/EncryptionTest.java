package com.run.encryption;

import com.bkjk.encryption.AsymmetricEncryption;
import com.bkjk.encryption.Constants;
import com.bkjk.encryption.SymmetricEncryption;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import static com.bkjk.encryption.AsymmetricEncryption.readFileToStr;

/**
 * @author : hewei
 * @date : 2018/1/10
 */
public class EncryptionTest {

    // 算法公共常量
    public static final String defaultCharset = "utf-8";

    public static final String SLASH = "/";

// RSA算法常量

    public static final String RSA = "RSA"; // 字符串进行加密算法的名称
    public static final String PROVIDER = "BC"; // 字符串持有安全提供者的名称
    //RSA支持的padding
    public static final String RSA_PADDING_NONE = "NONE/NoPadding"; // bc默认字符串进行加密填充的名称
    public static final String RSA_PADDING_ECB="ECB/PKCS1Padding";//java默认
    public static final String RSA_PADDING_OAEP = "OAEP/PKCS1Padding";


    // 非对称算法
    public static final String ASYMMETRIC_ALGORITHM_DH = "DH";
    public static final String ASYMMETRIC_ALGORITHM_RSA = "RSA";
    public static final String ASYMMETRIC_ALGORITHM_ELGAMAL = "ElGamal";
    public static final String ASYMMETRIC_ALGORITHM_ECC = "ECC";
    // 对称算法
    public static final String SYMMETRIC_ALGORITHM_AES = "AES";
    public static final String SYMMETRIC_ALGORITHM_DES = "DES";
    public static final String SYMMETRIC_ALGORITHM_BLOWFISH = "Blowfish";
    public static final String SYMMETRIC_ALGORITHM_DESede = "DESede";
    public static final String SYMMETRIC_ALGORITHM_CTR = "CTR";
    public static final String SYMMETRIC_ALGORITHM_RIJNDAEL = "Rijndael";
    public static final String SYMMETRIC_ALGORITHM_RC4 = "RC4";

    //算法模式
    public static final String ALGORITHM_MODE_ECB = "ECB";
    public static final String ALGORITHM_MODE_CBC = "CBC";
    public static final String ALGORITHM_MODE_CFB = "CFB";
    public static final String ALGORITHM_MODE_OFB = "OFB";
    public static final String ALGORITHM_MODE_PCBC = "PCBC";
    public static final String ALGORITHM_MODE_CTR = "CTR";

    //算法key长度
    public static final int ALGORITHM_KEY_BIT_SIZE_64 = 64;
    public static final int ALGORITHM_KEY_BIT_SIZE_128 = 128;
    public static final int ALGORITHM_KEY_BIT_SIZE_192 = 192;
    public static final int ALGORITHM_KEY_BIT_SIZE_256 = 256;

    public static final int ALGORITHM_KEY_BIT_SIZE_1024 = 1024;


    //算法模式
    public static final String ALGORITHM_PADDING_NOPADDING = "NoPadding";
    public static final String ALGORITHM_PADDING_PKCS5PADDING = "PKCS5Padding";
    public static final String ALGORITHM_PADDING_ISO10126PADDING = "ISO10126Padding";
    public static final String ALGORITHM_PADDING_PKCS7PADDING = "PKCS7Padding";
    public static final String ALGORITHM_PADDING_SSL3PADDING = "SSL3Padding";
    public static final String ALGORITHM_PADDING_NONE = "NONE";


    /**
     * 对称加密
     * @param source:待加密文本
     * @param key:密码
     * @param algorithm:加密算法
     * @param mode:加密模式
     * @param padding:填充模式
     * @param ivParam：iv向量
     * @param keySize:key位数
     */
    @Test
    public void symmetry() {
        String source = "1234567812345678";
        String key = "12345678123456781234567812345678";
        String ivParam = "1234567812345678";
        String encrypted = null;
        System.out.println("source = " + source + " legth = "
                + source.length());
        String algorithm = Constants.SYMMETRIC_ALGORITHM_AES;
        String mode = Constants.ALGORITHM_MODE_CBC;
        String padding = Constants.ALGORITHM_PADDING_NOPADDING;
        int keySize = Constants.ALGORITHM_KEY_BIT_SIZE_128;
        try {
            encrypted = SymmetricEncryption.encrypt(source, key, algorithm ,mode,padding,
                    ivParam, keySize);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("encrpted=" + encrypted + "\nlegth = "
                + encrypted.length());

        System.out.println("----------------------------------------------------------");

        String decrypted = null;
        try {
            decrypted = SymmetricEncryption.decrypt(encrypted, key, algorithm ,mode,padding,ivParam,
                    keySize);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(decrypted);
    }

    @Test
    public void asymmetricRSAGenerate(){
        try {
            //根据算法生产公私钥对
            AsymmetricEncryption.generateKey("d:\\encryption\\", Constants.ASYMMETRIC_ALGORITHM_RSA,Constants.ALGORITHM_KEY_BIT_SIZE_1024);
            System.out.println("生成秘钥完成。success...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void asymmetricRSA() {
        try {
            String source = "1234567812345678";

            String strPubkey1 = readFileToStr("d:\\encryption\\public.key");

            String strPrikey2 = readFileToStr("d:\\encryption\\private.key");

            //用公钥加密，私钥参数为null，相反，私钥加密时，公约参数为null

            String rsaEncoded = AsymmetricEncryption.encrypt(Constants.ASYMMETRIC_ALGORITHM_RSA,
                    strPubkey1, null, source, null, Constants.RSA_PADDING_NONE, Constants.SYMMETRIC_ALGORITHM_AES);
            System.out.println("rsaEncoded=" + rsaEncoded);
            //用私钥解密，公钥参数为null，相反，公钥解密时，私钥参数为null
            String rsadecoded = AsymmetricEncryption.decrypt(Constants.ASYMMETRIC_ALGORITHM_RSA,
                    null,strPrikey2, rsaEncoded, null, Constants.RSA_PADDING_NONE, Constants.SYMMETRIC_ALGORITHM_AES);
            System.out.println("rsadecoded=" + rsadecoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void asymmetricDHGenerate() {


        try {
            //根据算法生产公私钥对
            AsymmetricEncryption.generateKey("d:\\encryption_dh\\",
                    Constants.ASYMMETRIC_ALGORITHM_DH,
                    Constants.ALGORITHM_KEY_BIT_SIZE_1024);

            //DH算法，根据一方公钥，生产对方公私钥
            AsymmetricEncryption.generateKeyByPublicKey("d:\\encryption_dh\\",
                    "d:\\encryption_dh\\public.key", Constants.ALGORITHM_KEY_BIT_SIZE_1024);
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void asymmetricDH() {
        try {

            String source = "1234567812345678";

            String strPubkey1 = readFileToStr("d:\\encryption_dh\\public.key");
            String strPrikey1 = readFileToStr("d:\\encryption_dh\\dhprivate.key");
            String strPubkey2 = readFileToStr("d:\\encryption_dh\\dhpublic.key");
            String strPrikey2 = readFileToStr("d:\\encryption_dh\\private.key");

            String dhEncoded = AsymmetricEncryption.encrypt(Constants.ASYMMETRIC_ALGORITHM_DH,
                    strPubkey1, strPrikey1, source, null, Constants.RSA_PADDING_NONE, Constants.SYMMETRIC_ALGORITHM_AES);
            System.out.println("dhEncoded =" + dhEncoded );

            String dgdecoded = AsymmetricEncryption.decrypt(Constants.ASYMMETRIC_ALGORITHM_DH,
                    strPubkey2,strPrikey2, dhEncoded, null, Constants.RSA_PADDING_NONE, Constants.SYMMETRIC_ALGORITHM_AES);
            System.out.println("dgdecoded =" + dgdecoded );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLog() {
        String id = "LS" + System.currentTimeMillis() + (new Random().nextInt(90000) + 10000);
        System.out.println(id);
    }





}
