package org.apache.commons.rdf;

import com.google.common.base.Optional;

public interface Literal extends RDFTerm {
	String getLexicalForm() ;

	IRI getDatatype() ;

    Optional<String> getLanguageTag() ;
}
