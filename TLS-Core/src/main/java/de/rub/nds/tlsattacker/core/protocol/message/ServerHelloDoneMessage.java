/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsattacker.core.protocol.message;

import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.constants.HandshakeMessageType;
import de.rub.nds.tlsattacker.core.protocol.handler.ServerHelloDoneHandler;
import de.rub.nds.tlsattacker.core.protocol.handler.TlsMessageHandler;
import de.rub.nds.tlsattacker.core.state.TlsContext;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServerHelloDoneMessage extends HandshakeMessage {

    public ServerHelloDoneMessage(Config tlsConfig) {
        super(tlsConfig, HandshakeMessageType.SERVER_HELLO_DONE);
    }

    public ServerHelloDoneMessage() {
        super(HandshakeMessageType.SERVER_HELLO_DONE);
    }

    @Override
    public TlsMessageHandler getHandler(TlsContext context) {
        return new ServerHelloDoneHandler(context);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServerHelloDoneMessage:");
        return sb.toString();
    }
}
