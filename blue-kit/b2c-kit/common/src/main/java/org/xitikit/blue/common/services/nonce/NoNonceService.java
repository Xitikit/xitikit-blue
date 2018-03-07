package org.xitikit.blue.common.services.nonce;

public final class NoNonceService implements NonceService{

    @Override
    public Nonce generate(){

        throw new IllegalStateException("Not configured to provide 'nonce' verification");
    }

    @Override
    public boolean isValid(final String nonce){

        return false;
    }

    @Override
    public boolean isDisabled(){

        return true;
    }
}
