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
package com.github.commonsrdf.simple;

import java.util.UUID;

import com.github.commonsrdf.api.AbstractBlankNodeTest;
import com.github.commonsrdf.api.BlankNode;

/**
 * Concrete implementation of BlankNodeImpl test.
 * 
 * @author Peter Ansell p_ansell@yahoo.com
 */
public class BlankNodeImplTest extends AbstractBlankNodeTest {

	@Override
	protected BlankNode getBlankNode() {
		return new BlankNodeImpl();
	}

	@Override
	protected BlankNode getBlankNode(String identifier) {
		return new BlankNodeImpl(identifier);
	}

}
