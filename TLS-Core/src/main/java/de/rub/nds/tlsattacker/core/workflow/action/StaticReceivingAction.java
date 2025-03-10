/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.tlsattacker.core.workflow.action;

import de.rub.nds.tlsattacker.core.layer.data.DataContainer;
import java.util.List;

public interface StaticReceivingAction {
    List<List<DataContainer>> getExpectedDataContainerLists();

    /**
     * Returns a list of the specified class from the expected DataContainers. Always returns the
     * first list if multiple lists have the same type
     *
     * @param <T>
     * @param clazz
     * @return
     */
    default <T> List<T> getExpectedList(Class<T> clazz) {
        for (List<DataContainer> containerList : getExpectedDataContainerLists()) {
            if (!containerList.isEmpty() && clazz.isInstance(containerList.get(0))) {
                return (List<T>) containerList;
            }
        }
        return null;
    }
}
