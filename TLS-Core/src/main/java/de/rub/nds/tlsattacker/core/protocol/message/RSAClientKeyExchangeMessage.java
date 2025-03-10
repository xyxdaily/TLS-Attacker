/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.tlsattacker.core.protocol.message;

import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import de.rub.nds.modifiablevariable.ModifiableVariableHolder;
import de.rub.nds.tlsattacker.core.protocol.handler.RSAClientKeyExchangeHandler;
import de.rub.nds.tlsattacker.core.protocol.message.computations.RSAClientComputations;
import de.rub.nds.tlsattacker.core.protocol.parser.RSAClientKeyExchangeParser;
import de.rub.nds.tlsattacker.core.protocol.preparator.RSAClientKeyExchangePreparator;
import de.rub.nds.tlsattacker.core.protocol.serializer.RSAClientKeyExchangeSerializer;
import de.rub.nds.tlsattacker.core.state.Context;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;
import java.util.List;

@XmlRootElement(name = "RSAClientKeyExchange")
public class RSAClientKeyExchangeMessage extends ClientKeyExchangeMessage {

    @HoldsModifiableVariable @XmlElement protected RSAClientComputations computations;

    public RSAClientKeyExchangeMessage() {
        super();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RSAClientKeyExchangeMessage:");
        return sb.toString();
    }

    @Override
    public RSAClientComputations getComputations() {
        return computations;
    }

    @Override
    public RSAClientKeyExchangeHandler<? extends RSAClientKeyExchangeMessage> getHandler(
            Context context) {
        return new RSAClientKeyExchangeHandler<>(context.getTlsContext());
    }

    @Override
    public RSAClientKeyExchangeParser<? extends RSAClientKeyExchangeMessage> getParser(
            Context context, InputStream stream) {
        return new RSAClientKeyExchangeParser<>(stream, context.getTlsContext());
    }

    @Override
    public RSAClientKeyExchangePreparator<? extends RSAClientKeyExchangeMessage> getPreparator(
            Context context) {
        return new RSAClientKeyExchangePreparator<>(context.getChooser(), this);
    }

    @Override
    public RSAClientKeyExchangeSerializer<? extends RSAClientKeyExchangeMessage> getSerializer(
            Context context) {
        return new RSAClientKeyExchangeSerializer<>(
                this, context.getChooser().getSelectedProtocolVersion());
    }

    @Override
    public String toCompactString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RSA_CLIENT_KEY_EXCHANGE");
        if (isRetransmission()) {
            sb.append(" (ret.)");
        }
        return sb.toString();
    }

    @Override
    public void prepareComputations() {
        if (computations == null) {
            computations = new RSAClientComputations();
        }
    }

    @Override
    public List<ModifiableVariableHolder> getAllModifiableVariableHolders() {
        List<ModifiableVariableHolder> holders = super.getAllModifiableVariableHolders();
        if (computations != null) {
            holders.add(computations);
        }
        return holders;
    }

    @Override
    public String toShortString() {
        return "RSA_CKE";
    }
}
