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
package com.github.commonsrdf.api;

import java.util.UUID;

/**
 * A <a href= "http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node" >RDF-1.1
 * Blank Node</a>, as defined by <a href=
 * "http://www.w3.org/TR/rdf11-concepts/#section-blank-nodes" >RDF-1.1 Concepts
 * and Abstract Syntax</a>, a W3C Recommendation published on 25 February 2014.<br>
 * <p>
 * Note: 
 * <blockquote cite="http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node">
 *   <a href="http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node">Blank nodes</a>
 *   are disjoint from IRIs and literals. Otherwise, the
 *   set of possible blank nodes is arbitrary. RDF makes no reference to any
 *   internal structure of blank nodes.
 * </blockquote>
 * <p>
 * Also note that: 
 * <blockquote cite="http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node-identifier">
 * <a href="http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node-identifier">Blank node identifiers</a>
 * are local identifiers that are used in
 * some concrete RDF syntaxes or RDF store implementations. They are always
 * <em>locally scoped</em> to the file or RDF store, and are <em>not</em> persistent 
 * or portable
 * identifiers for blank nodes. Blank node identifiers are <em>not</em> 
 * part of the RDF
 * abstract syntax, but are entirely dependent on the concrete syntax or
 * implementation.
 * The syntactic restrictions on blank node identifiers, if any,
 * therefore also depend on the concrete RDF syntax or implementation.
 * <p>
 * Implementations that handle blank node identifiers in concrete syntaxes need
 * to be careful not to create the same blank node from multiple occurrences of
 * the same blank node identifier except in situations where this is supported
 * by the syntax.
 * </blockquote>
 *
 * @see <a href="http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node">RDF-1.1
 *      Blank Node</a>
 */
public interface BlankNode extends BlankNodeOrIRI {

	/**
	 * Return a <a href=
	 * "http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node-identifier"
	 * >identifier</a> for the blank node as an {@link UUID}. 
	 * This is not a serialization/syntax label.
	 * It should be uniquely identifying the blank node, but does not
	 * come with any guarantees for persistence or resolution.
	 * <p>
	 * Two objects of the type <code>BlankNode</code> with the same
	 * value returned from {@link #identifier()} are equivalent, 
	 * and thus are in the same local scope.
	 * (see {@link #equals(Object)}).
	 * <p>
	 * On the other hand, two <code>BlankNode</code> objects
	 * with different <code>identifier()</code> values are not 
	 * necessarily different.
	 * <p>
	 * It is not a requirement for this UUID <code>identifier</code> to form part of the
	 * {@link #ntriplesString()}. 
	 *
	 * @see #equals(Object)
	 *
	 * @return An unique identifier for the {@link BlankNode}.
	 */
	UUID identifier();

	/**
	 * Check it this BlankNode is equal to another BlankNode.
	 * <p>
	 * Two BlankNodes MUST be equal if they have the same {@link #identifier()},
	 * and MAY be equal if their {@link #identifier()} differ.
	 * <p>
	 * Implementations MUST also override {@link #hashCode()} so that two equal
	 * <code>BlankNode</code> instances produce the same hash code.
	 * <p>
	 * Note: 
	 * <blockquote
	 *   cite="http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node">
	 * Implementations that handle <a
	 * href="http://www.w3.org/TR/rdf11-concepts/#dfn-blank-node">blank node
	 * identifiers</a> in concrete syntaxes need to be careful not to create the
	 * same blank node from multiple occurrences of the same blank node
	 * identifier except in situations where this is supported by the syntax.
	 * </blockquote>
	 * 
	 * @see #identifier()
	 * @see Object#equals(Object)
	 * @see #hashCode()
	 * 
	 * @param other
	 *            Another object
	 * @return true if other is a BlankNode and is equal to this BlankNode
	 */
	@Override
	public boolean equals(Object other);

	/**
	 * Calculate a hash code for this BlankNode.
	 * <p>
	 * This method MUST be implemented when implementing {@link #equals(Object)}
	 * so that two equal BlankNodes produce the same hash code.
	 * 
	 * @see Object#hashCode()
	 * 
	 * @return a hash code value for this BlankNode.
	 */
	@Override
	public int hashCode();

}
