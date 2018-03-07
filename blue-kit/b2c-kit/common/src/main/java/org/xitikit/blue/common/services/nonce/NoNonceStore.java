package org.xitikit.blue.common.services.nonce;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NoNonceStore implements NonceStore{

    @Override
    public void put(@Nonnull final Nonce nonce){

    }

    @Nullable
    @Override
    public Nonce get(final String nonceValue){

        return null;
    }

    @Override
    public void purge(){

    }
}
