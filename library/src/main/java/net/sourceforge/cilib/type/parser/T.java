/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.cilib.type.parser;

import net.sourceforge.cilib.type.types.Bounds;
import net.sourceforge.cilib.type.types.StringType;
import net.sourceforge.cilib.type.types.Type;

/**
 *
 *
 */
final class T implements TypeCreator {
    private static final long serialVersionUID = 1198714503772193216L;

    /**
     * {@inheritDoc}
     */
    public Type create() {
        return new StringType("");
    }

    /**
     * {@inheritDoc}
     */
    public Type create(double value) {
        throw new UnsupportedOperationException("StringTypes with single values do not exist");
    }

    /**
     * {@inheritDoc}
     */
    public Type create(final Bounds bounds) {
        throw new UnsupportedOperationException("StringTypes with bounds do not exist");
    }

}
