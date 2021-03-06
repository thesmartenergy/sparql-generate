/*
 * Copyright 2020 MINES Saint-Étienne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.mines_stetienne.ci.sparql_generate.cli;

import java.io.PrintStream;
import org.apache.jena.graph.Triple;
import org.apache.jena.riot.system.StreamRDF;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.sparql.serializer.SerializationContext;
import org.apache.jena.sparql.util.FmtUtils;

/**
 * Outputs Stream RDF as N3 to a PrintStream with a PrefixMapping
 * 
 * @author Maxime Lefrançois
 */
public class ConsoleStreamRDF implements StreamRDF {

	private final PrefixMapping pm;
	private final SerializationContext context;

	private PrintStream out;

	private int i = 0;
	private final int MAX = 10000;

	public ConsoleStreamRDF(PrintStream out, PrefixMapping pm) {
		this.out = out;
		this.pm = pm;
		context = new SerializationContext(pm);
	}

	@Override
	public void start() {
	}

	@Override
	public void base(String string) {
		out.append("@base <").append(string).append("> .\n");
	}

	@Override
	public void prefix(String prefix, String uri) {
		out.append("@prefix ").append(prefix).append(": <").append(uri).append("> .\n");
	}

	@Override
	public void triple(Triple triple) {
		out.append(FmtUtils.stringForTriple(triple, context)).append(" .\n");
		i++;
		if (i >= MAX) {
			i = 0;
			out.flush();
		}
	}

	@Override
	public void quad(Quad quad) {
	}

	@Override
	public void finish() {
	}
}
