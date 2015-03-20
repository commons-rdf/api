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

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

import com.github.commonsrdf.api.BlankNode;

/**
 * A simple implementation of BlankNode.
 *
 */
class BlankNodeImpl implements BlankNode {

	private static UUID BLANK_NODE_NS = 
			UUID.fromString("7482d5ca-5e77-4dfa-92b5-85348e26061c");
	
	private final String id;

	public BlankNodeImpl() {
		this(UUID.randomUUID().toString());
	}

	public BlankNodeImpl(String id) {
		this.id = Objects.requireNonNull(id);
	}

	@Override
	public String identifier() {
		return id;
	}

	private static UUID nameUUID(UUID ns, String name) { 
		byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8); 
		ByteBuffer buffer = ByteBuffer.allocate(nameBytes.length + 2*Long.BYTES);
		buffer.putLong(ns.getMostSignificantBits());
		buffer.putLong(ns.getLeastSignificantBits());
		buffer.put(nameBytes);
		return UUID.nameUUIDFromBytes(buffer.array());
	}

	@Override
	public String ntriplesString() {
		try {
			return "_:" + UUID.fromString(id); 
		} catch (IllegalArgumentException ex) {
			return "_:" + nameUUID(BLANK_NODE_NS, identifier());
		}
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
