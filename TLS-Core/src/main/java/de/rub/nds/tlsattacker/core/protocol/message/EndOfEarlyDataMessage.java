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
import de.rub.nds.tlsattacker.core.protocol.handler.EndOfEarlyDataHandler;
import de.rub.nds.tlsattacker.core.protocol.handler.TlsMessageHandler;
import de.rub.nds.tlsattacker.core.state.TlsContext;

/**
 * RFC draft-ietf-tls-tls13-21
 */
public class EndOfEarlyDataMessage extends HandshakeMessage {

    public EndOfEarlyDataMessage() {
        super(HandshakeMessageType.END_OF_EARLY_DATA);
    }

    public EndOfEarlyDataMessage(Config config) {
        super(config, HandshakeMessageType.END_OF_EARLY_DATA);
    }

    @Override
    public TlsMessageHandler getHandler(TlsContext context) {
        return new EndOfEarlyDataHandler(context);
    }

}
