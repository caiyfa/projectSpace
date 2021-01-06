package com.infinitusint.sdk.oa.sso;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 证书管理类
 */
public class SSLTrustManager implements X509TrustManager, HostnameVerifier {

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }

    public boolean isServerTrusted(
            X509Certificate[] certs) {
        return true;
    }

    public boolean isClientTrusted(
            X509Certificate[] certs) {
        return true;
    }
}
