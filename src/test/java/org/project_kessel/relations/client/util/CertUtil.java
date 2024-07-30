package org.project_kessel.relations.client.util;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
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

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keystore);
            TrustManager[] trustManagers = tmf.getTrustManagers();

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, null);

            SSLContext.setDefault(sslContext);
        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | IOException |
                 NullPointerException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dumpCerts() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            X509TrustManager manager = (X509TrustManager) trustManagers[0];

            for (java.security.cert.X509Certificate x509Certificate : manager.getAcceptedIssuers()) {
                if ((x509Certificate.getSubjectDN().getName().startsWith("EMAILADDRESS=test@localhost"))) {
                    System.out.println("\n\n>>>>>> " + x509Certificate.getSubjectX500Principal() + "\n\n");
                } else {
                    System.out.println(x509Certificate.getSubjectX500Principal());
                }
            }
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
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

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keystore);
            TrustManager[] trustManagers = tmf.getTrustManagers();

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, null);

            SSLContext.setDefault(sslContext);
        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException |
                 KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }
}
