/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsattacker.core.protocol.preparator;

import de.rub.nds.modifiablevariable.util.ArrayConverter;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.constants.ProtocolVersion;
import de.rub.nds.tlsattacker.core.protocol.message.HelloRetryRequestMessage;
import de.rub.nds.tlsattacker.core.workflow.chooser.Chooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloRetryRequestPreparator extends HandshakeMessagePreparator<HelloRetryRequestMessage> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final HelloRetryRequestMessage msg;

    public HelloRetryRequestPreparator(Chooser chooser, HelloRetryRequestMessage message) {
        super(chooser, message);
        this.msg = message;
    }

    @Override
    public void prepareHandshakeMessageContents() {
        LOGGER.debug("Preparing HelloRetryRequestMessage");
        prepareProtocolVersion();
        prepareCipherSuite();
        prepareExtensions();
        prepareExtensionLength();
    }

    private void prepareProtocolVersion() {
        ProtocolVersion ourVersion = chooser.getConfig().getHighestProtocolVersion();
        if (chooser.getConfig().isEnforceSettings()) {
            msg.setProtocolVersion(ourVersion.getValue());
        } else {
            msg.setProtocolVersion(ProtocolVersion.TLS13.getValue());
        }
        LOGGER.debug("ProtocolVersion: " + ArrayConverter.bytesToHexString(msg.getProtocolVersion().getValue()));
    }

    private void prepareCipherSuite() {
        if (chooser.getConfig().isEnforceSettings()) {
            msg.setSelectedCipherSuite(chooser.getConfig().getDefaultSelectedCipherSuite().getByteValue());
        } else {
            CipherSuite selectedSuite = null;
            for (CipherSuite suite : chooser.getConfig().getDefaultServerSupportedCipherSuites()) {
                if (chooser.getClientSupportedCipherSuites().contains(suite)) {
                    selectedSuite = suite;
                    break;
                }
            }
            if (selectedSuite == null) {
                selectedSuite = chooser.getConfig().getDefaultSelectedCipherSuite();
                LOGGER.warn("No CipherSuites in common, falling back to defaultSelectedCipherSuite");
            }
            msg.setSelectedCipherSuite(selectedSuite.getByteValue());
        }
        LOGGER
            .debug("SelectedCipherSuite: " + ArrayConverter.bytesToHexString(msg.getSelectedCipherSuite().getValue()));
    }

}
