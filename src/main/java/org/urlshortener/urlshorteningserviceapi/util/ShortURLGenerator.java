package org.urlshortener.urlshorteningserviceapi.util;

public class ShortURLGenerator {
    private static final String BASE62_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = 62;

    public static String encode(Integer id) {
        StringBuilder sb = new StringBuilder();

        while (id > 0) {
            int remainder = id % BASE;
            id /= BASE;
            sb.append(BASE62_ALPHABET.charAt(remainder));
        }

        return sb.reverse().toString();
    }

    public static int decode(String url) {
        int num = 0;

        for (char c : url.toCharArray()) {
            num = num * 62 + BASE62_ALPHABET.indexOf(c);
        }

        return num;
    }
}
