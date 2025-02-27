/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0.  If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 1997 - July 2008 CWI, August 2008 - 2022 MonetDB B.V.
 */

package nl.cwi.monetdb.jdbc;

/**
 * a wrapper class for old programs who still depend on
 * class nl.cwi.monetdb.jdbc.MonetDriver to work.
 * This class is deprecated since nov 2020 and will be removed in a future release.
 */
public final class MonetDriver extends org.monetdb.jdbc.MonetDriver {
}
