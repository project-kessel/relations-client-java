package org.project_kessel.relations.client.util;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class CertUtil {
    private static final char[] passphrase = "changeit".toCharArray();
    private static final String selfSignedAlias = "selfsigned";
    private static final String certFileName = "certs/test.crt";

    public static void addTestCACertToTrustStore() {
        final char sep = File.separatorChar;
        File dir = new File(System.getProperty("java.home") + sep + "lib" + sep + "security");
        File file = new File(dir, "cacerts");
        try {
            InputStream localCertIn = new FileInputStream(file);
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(localCertIn, passphrase);
            localCertIn.close();
            if (keystore.containsAlias(selfSignedAlias)) {
                return;
            }

            InputStream certIn = Thread.currentThread().getContextClassLoader().getResourceAsStream(certFileName);
            BufferedInputStream bis = new BufferedInputStream(certIn);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            while (bis.available() > 0) {
                Certificate cert = cf.generateCertificate(bis);
                keystore.setCertificateEntry(selfSignedAlias, cert);
            }
            certIn.close();

            OutputStream out = new FileOutputStream(file);
            keystore.store(out, passphrase);
            out.close();
        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | IOException |
                 NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeTestCACertFromKeystore() {
        final char sep = File.separatorChar;
        File dir = new File(System.getProperty("java.home") + sep + "lib" + sep + "security");
        File file = new File(dir, "cacerts");
        try {
            InputStream localCertIn = new FileInputStream(file);
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(localCertIn, passphrase);
            keystore.deleteEntry(selfSignedAlias);
            OutputStream out = new FileOutputStream(file);
            keystore.store(out, passphrase);
            out.close();
        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
