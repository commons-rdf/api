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

import java.util.Objects;
import java.util.UUID;

import com.github.commonsrdf.api.BlankNode;

/**
 * A simple implementation of BlankNode.
 *
 */
class BlankNodeImpl implements BlankNode {

	private final UUID id;

	public BlankNodeImpl() {
		this(UUID.randomUUID());
	}

	public BlankNodeImpl(UUID id) {
		this.id = Objects.requireNonNull(id);
	}

	@Override
	public UUID identifier() {
		return id;
	}

	@Override
	public String ntriplesString() {
		return "_:" + identifier();
	}

	@Override
	public String toString() {
		return ntriplesString();
	}

	@Override
	public int hashCode() {
		return identifier().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BlankNode)) {
			return false;
		}
		BlankNode other = (BlankNode) obj;
		return identifier().equals(other.identifier());
	}

}
