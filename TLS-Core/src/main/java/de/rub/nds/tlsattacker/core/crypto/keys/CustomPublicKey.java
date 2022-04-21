/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsattacker.core.crypto.keys;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.layer.context.TlsContext;
import de.rub.nds.tlsattacker.transport.ConnectionEndType;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class CustomPublicKey implements Serializable {

    public abstract void adjustInContext(TlsContext tlsContext, ConnectionEndType ownerOfKey);

    public abstract void adjustInConfig(Config config, ConnectionEndType ownerOfKey);

    public abstract int keySize();
}
