/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0.  If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2008-2015 MonetDB B.V.
 */

package nl.cwi.monetdb.mcl.embedded.result.column;

import nl.cwi.monetdb.mcl.embedded.result.EmbeddedQueryResult;

import java.util.UUID;

/**
 * Mapping for MonetDB UUID data type
 */
public class UUIDColumn extends Column<UUID> {

    private final UUID[] values;

    public UUIDColumn(EmbeddedQueryResult result, int index, UUID[] values, boolean[] nullIndex) {
        super(result, index, nullIndex);
        this.values = values;
    }

    @Override
    public UUID[] getAllValues() {
        return this.values;
    }

    @Override
    protected UUID getValueImplementation(int index) {
        return this.values[index];
    }
}
