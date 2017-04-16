/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Based on ValueFactory from OpenRDF Sesame which is provided with the 
 * following license information:
 * 
 * Licensed to Aduna under one or more contributor license agreements.  
 * See the NOTICE.txt file distributed with this work for additional 
 * information regarding copyright ownership. 
 *
 * Aduna licenses this file to you under the terms of the Aduna BSD 
 * License (the "License"); you may not use this file except in compliance 
 * with the License.
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * 
 * The Aduna BSD License is: 
 * 
 *  Copyright Aduna (http://www.aduna-software.com/) 2001-2013
 *  All rights reserved.
 *  Redistribution and use in source and binary forms, with or without modification, 
 *  are permitted provided that the following conditions are met:
 *    Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *    Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *    Neither the name of the copyright holder nor the names of its contributors
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package org.apache.commons.rdf;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * A factory pattern interface that creates {@link RDFTerm}s from Java objects.
 * 
 * @author Peter Ansell p_ansell@yahoo.com
 */
public interface RDFTermFactory {

	/**
	 * Creates a new IRI from the supplied string-representation.
	 * 
	 * @param IRI
	 *            A string-representation of a IRI.
	 * @return An object representing the IRI.
	 * @throws IlllegalArgumentException
	 *             If the supplied string does not resolve to a legal (absolute)
	 *             IRI.
	 */
	public IRI createIRI(String IRI);

	/**
	 * Creates a new IRI from the supplied namespace and local name.
	 * 
	 * @param namespace
	 *            The IRI's namespace.
	 * @param localName
	 *            The IRI's local name.
	 * @throws IllegalArgumentException
	 *             If the supplied namespace and localname do not resolve to a
	 *             legal (absolute) IRI.
	 */
	public IRI createIRI(String namespace, String localName);

	/**
	 * Creates a new blank node, with a unique internal identifier.
	 * 
	 * @return An object representing the blank node.
	 */
	public BlankNode createBlankNode();

	/**
	 * Creates a new blank node with the given internal identifier.
	 * 
	 * Note, the RDFTermFactory may map the internal identifier to another value
	 * in the resulting BlankNode, but will always map a given internal
	 * identifier consistently for the lifetime of this RDFTermFactory.
	 * 
	 * @param internalIdentifier
	 *            The blank node internal identifier.
	 * @return An object representing the blank node.
	 * @throws IllegalArgumentException
	 *             If the internal identifier is null.
	 */
	public BlankNode createBlankNode(String internalIdentifier);

	/**
	 * Creates a new <tt>xsd:string</tt> typed literal with the supplied lexical
	 * form.
	 * 
	 * @param lexicalForm
	 *            The lexical form for the literal.
	 * @return A Literal with the given lexical form and XML Schema String as
	 *         its datatype.
	 * @throws IllegalArgumentException
	 *             If the lexical form is null.
	 */
	public Literal createLiteral(String lexicalForm);

	/**
	 * Creates a new RDF LangString typed literal with the supplied lexical form
	 * and language tag.
	 * 
	 * @param lexicalForm
	 *            The lexical form for the literal.
	 * @param languageTag
	 *            The literal's language tag. Must not be null. Must be a valid
	 *            BCP47 Language Tag.
	 * @return A Literal with the given lexical form and language tag.
	 * @throws IllegalArgumentException
	 *             If the lexical form or language tag are null or the language
	 *             tag is an invalid BCP47 Language Tag.
	 */
	public Literal createLiteral(String lexicalForm, String languageTag);

	/**
	 * Creates a new literal with the supplied label and datatype.
	 * 
	 * @param lexicalForm
	 *            The lexical form for the literal.
	 * @param datatype
	 *            The datatype IRI for the literal.
	 * @throws IllegalArgumentException
	 *             If the lexical form or datatype IRI are null.
	 */
	public Literal createLiteral(String lexicalForm, IRI datatype);

	/**
	 * Creates a new <tt>xsd:boolean</tt>-typed literal representing the
	 * specified value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:boolean</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(boolean value);

	/**
	 * Creates a new <tt>xsd:byte</tt>-typed literal representing the specified
	 * value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:byte</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(byte value);

	/**
	 * Creates a new <tt>xsd:short</tt>-typed literal representing the specified
	 * value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:short</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(short value);

	/**
	 * Creates a new <tt>xsd:int</tt>-typed literal representing the specified
	 * value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:int</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(int value);

	/**
	 * Creates a new <tt>xsd:long</tt>-typed literal representing the specified
	 * value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:long</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(long value);

	/**
	 * Creates a new <tt>xsd:float</tt>-typed literal representing the specified
	 * value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:float</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(float value);

	/**
	 * Creates a new <tt>xsd:double</tt>-typed literal representing the
	 * specified value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:double</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(double value);

	/**
	 * Creates a new <tt>xsd:integer</tt>-typed literal representing the
	 * specified value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:integer</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(BigInteger value);

	/**
	 * Creates a new <tt>xsd:decimal</tt>-typed literal representing the
	 * specified value.
	 * 
	 * @param value
	 *            The lexical value for the literal, after mapping to XML
	 *            Schema.
	 * @return An <tt>xsd:decimal</tt>-typed literal for the specified value.
	 */
	public Literal createLiteral(BigDecimal value);

	/**
	 * Creates a new literal representing the specified date that is typed using
	 * the appropriate XML Schema date/time datatype.
	 * 
	 * @param calendar
	 *            The date value for the literal, after mapping to XML Schema
	 *            date/time.
	 * @return An typed literal for the specified date.
	 * @throws IllegalArgumentException
	 *             If the calendar object is null.
	 */
	public Literal createLiteral(XMLGregorianCalendar calendar);

	/**
	 * Creates a new literal representing the specified date that is typed using
	 * the appropriate XML Schema date/time datatype.
	 * 
	 * @param date
	 *            The date value for the literal, after mapping to XML Schema
	 *            date/time.
	 * @return An typed literal for the specified date.
	 * @throws IllegalArgumentException
	 *             If the date object is null.
	 */
	public Literal createLiteral(Date date);

	/**
	 * Creates a new literal representing the specified date that is typed using
	 * the appropriate XML Schema date/time datatype.
	 * 
	 * @param instant
	 *            The date value for the literal, after mapping to XML Schema
	 *            date/time.
	 * @return An typed literal for the specified date.
	 * @throws IllegalArgumentException
	 *             If the instant object is null.
	 */
	public Literal createLiteral(Instant instant);

	/**
	 * Creates a new {@link Triple} with the supplied subject, predicate and
	 * object.
	 * 
	 * @param subject
	 *            The subject of the triple.
	 * @param predicate
	 *            The predicate of the triple.
	 * @param object
	 *            The object of the triple.
	 * @return A Triple with the given subject predicate and object.
	 */
	public Triple createTriple(BlankNodeOrIRI subject, IRI predicate,
			RDFTerm object);
}
