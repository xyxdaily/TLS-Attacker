/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsattacker.core.layer;

import de.rub.nds.tlsattacker.core.layer.constant.LayerType;
import de.rub.nds.tlsattacker.core.layer.data.DataContainer;

import java.util.List;

/**
 * Contains information about a layers actions, both after sending and receiving data.
 *
 * @param <T>
 */
public class LayerProcessingResult<T extends DataContainer> {

    /**
     * List of containers that were sent or received
     */
    private List<T> usedContainers;

    /**
     * Type of layer that produced this result.
     */
    private LayerType layerType;

    /**
     * Whether the layer could send or receive bytes as planned.
     */
    private boolean executedAsPlanned;

    public LayerProcessingResult(List<T> usedContainers, LayerType layerType, boolean executedAsPlanned) {
        this.usedContainers = usedContainers;
        this.layerType = layerType;
        this.executedAsPlanned = executedAsPlanned;
    }

    public List<T> getUsedContainers() {
        return usedContainers;
    }

    public void setUsedContainers(List<T> usedContainers) {
        this.usedContainers = usedContainers;
    }

    public LayerType getLayerType() {
        return layerType;
    }

    public boolean isExecutedAsPlanned() {
        return executedAsPlanned;
    }

    public void setExecutedAsPlanned(boolean executedAsPlanned) {
        this.executedAsPlanned = executedAsPlanned;
    }

    public void setLayerType(LayerType layerType) {
        this.layerType = layerType;
    }
}
