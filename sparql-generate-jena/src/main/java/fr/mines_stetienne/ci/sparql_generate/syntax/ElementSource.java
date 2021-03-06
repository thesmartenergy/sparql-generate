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
package fr.mines_stetienne.ci.sparql_generate.syntax;

import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.syntax.Element;
import org.apache.jena.sparql.syntax.ElementVisitor;
import org.apache.jena.sparql.util.NodeIsomorphismMap;

/**
 * The {@code SOURCE} clause.
 *
 * @author Maxime Lefrançois
 */
public class ElementSource extends Element {

    private Var var;

    private final Node source, accept;

    public ElementSource(Node source, Node accept, Var var) {
        this.var = var;
        this.source = source;
        this.accept = accept;
    }

    public Var getVar() {
        return var;
    }
    
    public Node getSource() {
        return source;
    }

    public Node getAccept() {
        return accept;
    }

    @Override
    public void visit(ElementVisitor v) {
        if (v instanceof SPARQLExtElementVisitor) {
            ((SPARQLExtElementVisitor) v).visit(this);
        }
    }

    @Override
    public int hashCode() {
        return getVar().hashCode() ^ source.hashCode() ^ accept.hashCode();
    }

    @Override
    public boolean equalTo(Element el2, NodeIsomorphismMap isoMap) {
        if (el2 == null) {
            return false;
        }
        if (!(el2 instanceof ElementSource)) {
            return false;
        }
        ElementSource s2 = (ElementSource) el2;
        if (!this.getVar().equals(s2.getVar())) {
            return false;
        }
        if (!this.getSource().equals(s2.getSource())) {
            return false;
        }
        if (this.getAccept()== null && s2.getAccept()!=null || this.getAccept()!=null&&!this.getAccept().equals(s2.getAccept())) {
            return false;
        }
        return true;
    }

}