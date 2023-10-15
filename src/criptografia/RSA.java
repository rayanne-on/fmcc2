package criptografia;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();

        BigInteger n, d, e;

        // 1. Escolhe de forma aleatória dois números primos grandes p e q
        SecureRandom secureRandom = new SecureRandom();
        BigInteger p = new BigInteger(1024, 100, secureRandom);
        BigInteger q = new BigInteger(1024, 100, secureRandom);

        // 2. Calcula: n = p * q
        n = p.multiply(q);

        // 3. Calcula Totiente de Euler: φ(n) = (p - 1)(q - 1)
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // 4. Escolhe um inteiro "e", tal que "e" e φ(n) sejam relativamente primos entre si
        e = new BigInteger("19");
        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        // 5. Calcula "d", o inverso multiplicativo de "e": e . d ≡ 1 mod φ(n)
        d = e.modInverse(m);

        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("n: " + n);
        System.out.println("e: " + e);
        System.out.println("d: " + d);

        // Encriptação: c ≡ m^e mod n
        String msgCifrada = new BigInteger(msg.getBytes()).modPow(e, n).toString();
        System.out.println("Mensagem cifrada: " + msgCifrada);

        // Decriptação: m ≡ c^d mod n
        String msgDecifrada = new String(new BigInteger(msgCifrada).modPow(d, n).toByteArray());
        System.out.println("Mensagem decifrada: " + msgDecifrada);
    }
}