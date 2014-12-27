/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.commonsrdf.dummyimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.commonsrdf.api.BlankNodeOrIRI;
import com.github.commonsrdf.api.Graph;
import com.github.commonsrdf.api.IRI;
import com.github.commonsrdf.api.RDFTerm;
import com.github.commonsrdf.api.Triple;

public class GraphImpl implements Graph {

	private static final int TO_STRING_MAX = 10;
	protected List<Triple> triples = new ArrayList<Triple>();

	@Override
	public void add(BlankNodeOrIRI subject, IRI predicate, RDFTerm object) {
		add(new TripleImpl(subject, predicate, object));

	}

	@Override
	public void add(Triple triple) {
		if (triple == null) {
			throw new NullPointerException("triple can't be null");
		}
		triples.add(triple);
	}

	@Override
	public void clear() {
		triples.clear();
	}

	@Override
	public boolean contains(BlankNodeOrIRI subject, IRI predicate,
			RDFTerm object) {
		return getTriples(subject, predicate, object).findFirst().isPresent();
	}

	@Override
	public boolean contains(Triple triple) {
		if (triple == null) {
			throw new NullPointerException("triple can't be null");
		}
		return triples.contains(triple);
	}

	@Override
	public Stream<Triple> getTriples() {
		return triples.parallelStream();
	}

	@Override
	public Stream<Triple> getTriples(final BlankNodeOrIRI subject,
			final IRI predicate, final RDFTerm object) {
		Predicate<Triple> match = new Predicate<Triple>() {
			@Override
			public boolean test(Triple t) {
				// Lacking the requirement for .equals() we have to be silly
				// and test ntriples string equivalance
				if (subject != null
						&& !t.getSubject().ntriplesString()
								.equals(subject.ntriplesString())) {
					return false;
				}
				if (predicate != null
						&& !t.getPredicate().ntriplesString()
								.equals(predicate.ntriplesString())) {
					return false;
				}
				if (object != null
						&& !t.getObject().ntriplesString()
								.equals(object.ntriplesString())) {
					return false;
				}
				return true;
			}
		};
		return getTriples().unordered().filter(match);
	}

	@Override
	public void remove(BlankNodeOrIRI subject, IRI predicate, RDFTerm object) {
		Stream<Triple> toRemove = getTriples(subject, predicate, object);
		for (Triple t : toRemove.collect(Collectors.toList())) {
			// Avoid ConcurrentModificationException in ArrayList
			remove(t);
		}
	}

	@Override
	public void remove(Triple triple) {
		if (triple == null) {
			throw new NullPointerException("triple can't be null");
		}
		triples.remove(triple);
	}

	@Override
	public long size() {
		return triples.size();
	}

	@Override
	public String toString() {
		String s = getTriples().limit(TO_STRING_MAX).map(Object::toString)
				.collect(Collectors.joining("\n"));
		if (size() > TO_STRING_MAX) {
			return s + "\n# ... +" + (size() - TO_STRING_MAX) + " more";
		} else {
			return s;
		}
	}

}
