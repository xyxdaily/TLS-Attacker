/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.tlsattacker.core.quic.preparator.frame;

import de.rub.nds.tlsattacker.core.quic.frame.DataBlockedFrame;
import de.rub.nds.tlsattacker.core.workflow.chooser.Chooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataBlockedFramePreparator extends QuicFramePreparator<DataBlockedFrame> {

    private static final Logger LOGGER = LogManager.getLogger();

    public DataBlockedFramePreparator(Chooser chooser, DataBlockedFrame object) {
        super(chooser, object);
    }

    @Override
    public void prepare() {
        LOGGER.debug("DATA BLOCKED Frame");
        prepareMaximumData(getObject());
    }

    protected void prepareMaximumData(DataBlockedFrame frame) {
        frame.setMaximumData(frame.getMaximumDataConfig());
        LOGGER.debug("Maximum Data: {}", frame.getMaximumData().getValue());
    }
}
