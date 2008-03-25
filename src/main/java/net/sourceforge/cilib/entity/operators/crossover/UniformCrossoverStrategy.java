/*
 * UniformCrossoverStrategy.java
 * 
 * Created on Apr 1, 2006
 *
 * Copyright (C) 2003, 2004 - CIRG@UP 
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science 
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package net.sourceforge.cilib.entity.operators.crossover;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.cilib.entity.Entity;
import net.sourceforge.cilib.entity.Topology;
import net.sourceforge.cilib.entity.operators.selection.RandomSelectionStrategy;
import net.sourceforge.cilib.entity.operators.selection.SelectionStrategy;
import net.sourceforge.cilib.type.types.container.Vector;

/**
*
* @author  Andries Engelbrecht
*/

public class UniformCrossoverStrategy extends CrossoverStrategy {
	
	private SelectionStrategy selectionStrategy;
	
	public UniformCrossoverStrategy() {
		super();
		this.selectionStrategy = new RandomSelectionStrategy();
	}
	
	public UniformCrossoverStrategy(UniformCrossoverStrategy copy) {
		super(copy);
		this.selectionStrategy = copy.selectionStrategy.getClone();
	}
	
	public UniformCrossoverStrategy getClone() {
		return new UniformCrossoverStrategy(this);
	}

	@Override
	public List<Entity> crossover(List<Entity> parentCollection) {
		List<Entity> offspring = new ArrayList<Entity>(parentCollection.size());
		
		//How do we handle variable sizes? Resizing the entities?
		Entity offspring1 = parentCollection.get(0).getClone();
		Entity offspring2 = parentCollection.get(1).getClone();
		
		if (this.getCrossoverProbability().getParameter() >= this.getRandomNumber().getUniform()) {
			
			Vector parentChromosome1 = (Vector) parentCollection.get(0).getContents();
			Vector parentChromosome2 = (Vector) parentCollection.get(1).getContents();
			Vector offspringChromosome1 = (Vector) offspring1.getContents();
			Vector offspringChromosome2 = (Vector) offspring2.getContents();
			
			int sizeParent1 = parentChromosome1.getDimension();
			int sizeParent2 = parentChromosome2.getDimension();
		
			int minDimension = Math.min(sizeParent1, sizeParent2);
									
			for (int i = 0; i < minDimension; i++) {
				if (i%2 == 0) {
					offspringChromosome1.set(i,parentChromosome1.get(i));
					offspringChromosome2.set(i,parentChromosome2.get(i));
				}
				else {
					offspringChromosome1.set(i,parentChromosome2.get(i));
					offspringChromosome2.set(i,parentChromosome1.get(i));	
				}
			}
			
			offspring1.calculateFitness(false);
			offspring2.calculateFitness(false);
			
			offspring.add(offspring1);
			offspring.add(offspring2);
		}
		
		return offspring;
	}
	

	public void performOperation(Topology<? extends Entity> topology, Topology<Entity> offspring) {
		List<Entity> parentCollection = new ArrayList<Entity>();
		
		parentCollection.add(this.selectionStrategy.select(topology));
		parentCollection.add(this.selectionStrategy.select(topology));
		
		offspring.addAll(this.crossover(parentCollection));
	}
		
}
