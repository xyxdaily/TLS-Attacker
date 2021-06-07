/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsattacker.core.starttls;

import de.rub.nds.tlsattacker.core.constants.StarttlsType;
import de.rub.nds.tlsattacker.core.exceptions.UnsupportedStarttlsTypeException;
import de.rub.nds.tlsattacker.core.state.TlsContext;

public class StarttlsProtocolFactory {
    public static StarttlsProtocolHandler getProtocol(StarttlsType type) {
        switch (type) {
            case FTP:
                return new FTPHandler();
            case IMAP:
                return new IMAPHandler();
            case POP3:
                return new POP3Handler();
            case SMTP:
                return new SMTPHandler();
            default:
                throw new UnsupportedStarttlsTypeException("StarttlsType \"" + type + "\" not supported");
        }
    }
}
