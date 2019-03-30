package io.lollipok.mybatis.generator.codegen.mybatis3;

import io.lollipok.mybatis.generator.codegen.mybatis3.javamapper.CustomizedJavaMapperGenerator;
import io.lollipok.mybatis.generator.codegen.mybatis3.xmlmapper.CustomizedXMLMapperGenerator;
import io.lollipok.mybatis.generator.constants.Constants;
import java.util.List;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @author yangyanju
 * @version 1.0
 * @date 2019-03-29
 */
public class CustomizedIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl {

  public CustomizedIntrospectedTableMyBatis3Impl() {
    super();
  }

  // region Set StatementId

  @Override
  public void setDeleteByPrimaryKeyStatementId(String s) {
    internalAttributes.put(InternalAttribute.ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID, "deleteById");
  }

  @Override
  public void setSelectAllStatementId(String s) {
    super.setSelectAllStatementId("findAll");
  }

  @Override
  public void setSelectByExampleStatementId(String s) {
    super.setSelectByExampleStatementId("findByExample");
  }

  @Override
  public void setSelectByExampleWithBLOBsStatementId(String s) {
    super.setSelectByExampleWithBLOBsStatementId("findByExampleWithBLOBs");
  }

  @Override
  public void setSelectByPrimaryKeyStatementId(String s) {
    super.setSelectByPrimaryKeyStatementId("findById");
  }

  @Override
  public void setUpdateByPrimaryKeyStatementId(String s) {
    super.setUpdateByPrimaryKeyStatementId("updateById");
  }

  @Override
  public void setUpdateByPrimaryKeySelectiveStatementId(String s) {
    super.setUpdateByPrimaryKeySelectiveStatementId("updateByIdSelective");
  }

  @Override
  public void setUpdateByPrimaryKeyWithBLOBsStatementId(String s) {
    super.setUpdateByPrimaryKeyWithBLOBsStatementId("updateByIdWithBLOBs");
  }

  // endregion

  @Override
  protected String calculateMyBatis3XmlMapperFileName() {
    StringBuilder sb = new StringBuilder();
    if (StringUtility.stringHasValue(tableConfiguration.getMapperName())) {
      String mapperName = tableConfiguration.getMapperName();
      int ind = mapperName.lastIndexOf('.');
      if (ind == -1) {
        sb.append(mapperName);
      } else {
        sb.append(mapperName.substring(ind + 1));
      }
      sb.append(Constants.CUSTOMIZED_XML_MAPPER_SUFFIX);
    } else {
      sb.append(fullyQualifiedTable.getDomainObjectName());
      sb.append(Constants.DEFAULT_XML_MAPPER_SUFFIX);
    }
    return sb.toString();
  }

  @Override
  protected AbstractJavaClientGenerator createJavaClientGenerator() {
    if (context.getJavaClientGeneratorConfiguration() == null) {
      return null;
    }

    String type = context.getJavaClientGeneratorConfiguration().getConfigurationType();

    AbstractJavaClientGenerator javaGenerator;

    if ("XMLMAPPER".equalsIgnoreCase(type)) {
      javaGenerator = new CustomizedJavaMapperGenerator();
    } else if ("MAPPER".equalsIgnoreCase(type)) {
      javaGenerator = new CustomizedJavaMapperGenerator();
    } else {
      javaGenerator = super.createJavaClientGenerator();
    }

    return javaGenerator;
  }

  @Override
  protected void calculateXmlMapperGenerator(
      AbstractJavaClientGenerator javaClientGenerator,
      List<String> warnings,
      ProgressCallback progressCallback) {
    if (javaClientGenerator == null) {
      if (context.getSqlMapGeneratorConfiguration() != null) {
        xmlMapperGenerator = new CustomizedXMLMapperGenerator();
      }
    } else {
      xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
    }

    initializeAbstractGenerator(xmlMapperGenerator, warnings, progressCallback);
  }
}
