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
package net.sourceforge.cilib.functions.continuous.moo.wfg;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.util.List;
import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.problem.FunctionOptimisationProblem;
import net.sourceforge.cilib.problem.MOOptimisationProblem;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 *
 */
public class WFG8 extends MOOptimisationProblem {

    private static final long serialVersionUID = 385737378832520022L;

    private static final int M = 3;
    private static final int k = 2 * (M - 1);
    private static final int l = 20;

    public WFG8() {

        for (int i = 0; i < M; ++i) {
            final int index = i;
            ContinuousFunction function = new ContinuousFunction() {

                @Override
                public Double apply(Vector input) {
                    Vector y = Problems.WFG8(input, k, M);
                    return y.doubleValueOf(index);
                }
            };
            FunctionOptimisationProblem wfg8_fm = new FunctionOptimisationProblem();
            wfg8_fm.setFunction(function);
            List<String> domain = Lists.newArrayList();
            for (int j = 0; j < k + l; ++j) {
                domain.add("R(0, " + 2 * (j + 1) + ")");
            }
            wfg8_fm.setDomain(Joiner.on(", ").join(domain));
            add(wfg8_fm);
        }

    }

    public WFG8(WFG8 copy) {
        super(copy);
    }

    @Override
    public WFG8 getClone() {
        return new WFG8(this);
    }
}
