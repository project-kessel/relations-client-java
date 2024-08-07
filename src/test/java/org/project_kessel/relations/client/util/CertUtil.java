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
        try {
            var keystore = loadKeystoreFromJdk();
            if (keystore.containsAlias(selfSignedAlias)) {
                return;
            }

            try(InputStream certIn = Thread.currentThread().getContextClassLoader().getResourceAsStream(certFileName);
                BufferedInputStream bis = new BufferedInputStream(certIn)) {

                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                while (bis.available() > 0) {
                    Certificate cert = cf.generateCertificate(bis);
                    keystore.setCertificateEntry(selfSignedAlias, cert);
                }

                saveKeystoreToJdk(keystore);
            }
        } catch (CertificateException | KeyStoreException | IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeTestCACertFromKeystore() {
        var keystore = loadKeystoreFromJdk();
        try {
            keystore.deleteEntry(selfSignedAlias);
            saveKeystoreToJdk(keystore);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

    public static KeyStore loadKeystoreFromJdk() {
        try (InputStream localCertIn = new FileInputStream(getCertFile())) {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(localCertIn, passphrase);
            return keystore;
        } catch (IOException | CertificateException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveKeystoreToJdk(KeyStore keystore) {
        try (OutputStream out = new FileOutputStream(getCertFile())) {
            keystore.store(out, passphrase);
        } catch (IOException | CertificateException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getCertFile() {
        final char sep = File.separatorChar;
        File dir = new File(System.getProperty("java.home") + sep + "lib" + sep + "security");
        return new File(dir, "cacerts");
    }
}
