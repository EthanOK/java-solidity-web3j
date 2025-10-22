package org.web3.utils;

import org.web3j.crypto.Keys;

public class ChecksumAddress {

    public static String getChecksumAddress(String address) {
        return Keys.toChecksumAddress(address);
    }

    public static boolean equalsAddress(String address1, String address2) {
        return Keys.toChecksumAddress(address1).equals(Keys.toChecksumAddress(address2));
    }

}
