/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.digimono.mybatis.generator.plugins;

import static io.digimono.mybatis.generator.constants.Constants.STATEMENT_ID_SELECT_BY_ENTITY;

import io.digimono.mybatis.generator.codegen.mybatis3.xmlmapper.elements.FindByEntityClauseElementGenerator;
import io.digimono.mybatis.generator.codegen.mybatis3.xmlmapper.elements.FindByEntityElementGenerator;
import io.digimono.mybatis.generator.plugins.base.BasePlugin;
import io.digimono.mybatis.generator.utils.Utils;
import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 * @author yangyanju
 */
public class FindByEntityPlugin extends BasePlugin {

  private String findByEntityStatementId;

  @Override
  public void initialized(IntrospectedTable introspectedTable) {
    super.initialized(introspectedTable);
    this.findByEntityStatementId = STATEMENT_ID_SELECT_BY_ENTITY;
  }

  @Override
  public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
    if (Utils.generateEmptyJavaMapper(context, introspectedTable)) {
      return true;
    }

    FullyQualifiedJavaType parameterType;
    if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
      parameterType = new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType());
    } else {
      parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
    }

    FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
    returnType.addTypeArgument(parameterType);

    Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
    importedTypes.add(returnType);
    importedTypes.add(parameterType);

    Method method = new Method(findByEntityStatementId);
    method.setVisibility(JavaVisibility.PUBLIC);
    method.setAbstract(true);
    method.addParameter(new Parameter(parameterType, "row"));
    method.setReturnType(returnType);

    context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

    addMapperAnnotations(interfaze, method);
    addExtraImports(interfaze);
    interfaze.addImportedTypes(importedTypes);
    interfaze.addMethod(method);

    return true;
  }

  @Override
  public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
    initializeAndExecuteGenerator(
        new FindByEntityClauseElementGenerator(), document.getRootElement(), introspectedTable);
    initializeAndExecuteGenerator(
        new FindByEntityElementGenerator(), document.getRootElement(), introspectedTable);

    return super.sqlMapDocumentGenerated(document, introspectedTable);
  }

  public void addMapperAnnotations(Interface interfaze, Method method) {}

  public void addExtraImports(Interface interfaze) {}

  protected void initializeAndExecuteGenerator(
      AbstractXmlElementGenerator elementGenerator,
      XmlElement parentElement,
      IntrospectedTable introspectedTable) {
    elementGenerator.setContext(context);
    elementGenerator.setIntrospectedTable(introspectedTable);
    elementGenerator.addElements(parentElement);
  }
}
