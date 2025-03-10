/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.tlsattacker.core.workflow.action;

import de.rub.nds.tlsattacker.core.state.State;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.Arrays;
import java.util.List;

/**
 * This action allows the declaration of multiple actions, the right one will selected at runtime.
 * The usage of two actions with the same Messages is forbidden.
 */
@XmlRootElement(name = "MultiReceive")
public class MultiReceiveAction extends GenericReceiveAction {

    private List<ReceiveAction> expectedActionCandidates;
    @XmlTransient private ReceiveAction selectedAction;

    public MultiReceiveAction() {}

    public MultiReceiveAction(ReceiveAction... receiveActions) {
        this.expectedActionCandidates = Arrays.asList(receiveActions);
    }

    public MultiReceiveAction(String connectionAlias, ReceiveAction... receiveActions) {
        super(connectionAlias);
        this.expectedActionCandidates = Arrays.asList(receiveActions);
    }

    public MultiReceiveAction(
            String connectionAlias, List<ReceiveAction> expectedActionCandidates) {
        super(connectionAlias);
        this.expectedActionCandidates = expectedActionCandidates;
    }

    @Override
    public boolean executedAsPlanned() {
        return selectedAction.executedAsPlanned();
    }

    public ReceiveAction getSelectedAction() {
        return selectedAction;
    }

    @Override
    public void execute(State state) {
        super.execute(state);
        selectedAction = new ReceiveAction();
        for (ReceiveAction receiveAction : expectedActionCandidates) {
            if (compareExpectedActionsWithReceivedActions2(receiveAction)) {
                selectedAction = receiveAction;
                break;
            }
        }
        selectedAction.setExecuted(super.isExecuted());
    }

    public List<ReceiveAction> getExpectedActionCandidates() {
        return expectedActionCandidates;
    }

    private boolean compareExpectedActionsWithReceivedActions2(ReceiveAction actionCandidate) {
        actionCandidate.setLayerStackProcessingResult(super.getLayerStackProcessingResult());
        return actionCandidate.executedAsPlanned();
    }
}
