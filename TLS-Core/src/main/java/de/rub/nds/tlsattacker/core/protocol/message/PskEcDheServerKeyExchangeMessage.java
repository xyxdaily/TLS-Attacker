/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2017 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.core.protocol.message;

import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.ModifiableVariableProperty;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.constants.EllipticCurveType;
import de.rub.nds.tlsattacker.core.constants.NamedCurve;
import de.rub.nds.tlsattacker.core.protocol.handler.PskEcDheServerKeyExchangeHandler;
import de.rub.nds.tlsattacker.core.protocol.handler.ProtocolMessageHandler;
import de.rub.nds.modifiablevariable.integer.ModifiableInteger;
import de.rub.nds.tlsattacker.core.state.TlsContext;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Florian Linsner - florian.linsner@rub.de
 */
@XmlRootElement
public class PskEcDheServerKeyExchangeMessage extends ECDHEServerKeyExchangeMessage {

    @ModifiableVariableProperty(type = ModifiableVariableProperty.Type.PUBLIC_KEY)
    private ModifiableByteArray identityHint;

    @ModifiableVariableProperty(type = ModifiableVariableProperty.Type.LENGTH)
    private ModifiableInteger identityHintLength;

    public PskEcDheServerKeyExchangeMessage() {
        super();
    }

    public PskEcDheServerKeyExchangeMessage(Config tlsConfig) {
        super(tlsConfig);
    }

    public ModifiableByteArray getIdentityHint() {
        return identityHint;
    }

    public void setIdentityHint(ModifiableByteArray identityHint) {
        this.identityHint = identityHint;
    }

    public void setIdentityHint(byte[] identity) {
        this.identityHint = ModifiableVariableFactory.safelySetValue(this.identityHint, identity);
    }

    public ModifiableInteger getIdentityHintLength() {
        return identityHintLength;
    }

    public void setIdentityHintLength(ModifiableInteger identityHintLength) {
        this.identityHintLength = identityHintLength;
    }

    public void setIdentityHintLength(int identityHintLength) {
        this.identityHintLength = ModifiableVariableFactory.safelySetValue(this.identityHintLength, identityHintLength);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n  Curve Type: ");
        sb.append(EllipticCurveType.getCurveType(this.curveType.getValue()));
        sb.append("\n  Named Curve: ");
        if (namedCurve != null) {
            sb.append(NamedCurve.getNamedCurve(this.namedCurve.getValue()));
        } else {
            sb.append("null");
        }
        sb.append("\n  Public Key: ");
        sb.append(ArrayConverter.bytesToHexString(getPublicKey().getValue()));
        return sb.toString();
    }

    @Override
    public ProtocolMessageHandler getHandler(TlsContext context) {
        return new PskEcDheServerKeyExchangeHandler(context);
    }

    @Override
    public String toCompactString() {
        return "ECDHE_PSK_SERVER_KEY_EXCHANGE";
    }
}
