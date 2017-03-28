package iop.org.iop_sdk_android.core.crypto;

import iop_sdk.crypto.CryptoWrapper;

/**
 * Created by mati on 07/02/17.
 */

public class CryptoWrapperAndroid implements CryptoWrapper {
    @Override
    public void random(byte[] dest, int size) {
        CryptoImp.random(dest,size);
    }
}
