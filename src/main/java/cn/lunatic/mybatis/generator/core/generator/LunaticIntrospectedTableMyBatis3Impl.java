package cn.lunatic.mybatis.generator.core.generator;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @author ganfeng
 * @date 2018/10/17
 */
public class LunaticIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl {

    private String getTableClassName() {
        String tableName = fullyQualifiedTable.getDomainObjectName();
        if(tableName.startsWith("T")){
            return tableName.substring(1);
        }else{
            return tableName;
        }
    }

    /**
     * 自定义Dao类名
     */
    @Override
    protected void calculateJavaClientAttributes() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(calculateJavaClientImplementationPackage());
        sb.append('.');
        sb.append(getTableClassName());
        sb.append("DAOImpl");
        setDAOImplementationType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(getTableClassName());
        sb.append("DAO");
        setDAOInterfaceType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        if (stringHasValue(tableConfiguration.getMapperName())) {
            sb.append(tableConfiguration.getMapperName());
        } else {
            if (stringHasValue(fullyQualifiedTable.getDomainObjectSubPackage())) {
                sb.append(fullyQualifiedTable.getDomainObjectSubPackage());
                sb.append('.');
            }
            sb.append(getTableClassName());
            sb.append("Dao");
        }
        setMyBatis3JavaMapperType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        if (stringHasValue(tableConfiguration.getSqlProviderName())) {
            sb.append(tableConfiguration.getSqlProviderName());
        } else {
            if (stringHasValue(fullyQualifiedTable.getDomainObjectSubPackage())) {
                sb.append(fullyQualifiedTable.getDomainObjectSubPackage());
                sb.append('.');
            }
            sb.append(getTableClassName());
            sb.append("SqlProvider");
        }
        setMyBatis3SqlProviderType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(getTableClassName());
        sb.append("DynamicSqlSupport");
        setMyBatisDynamicSqlSupportType(sb.toString());
    }

    /**
     * 自定义Mapper类名
     *
     * @return
     */
    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        if (stringHasValue(tableConfiguration.getMapperName())) {
            String mapperName = tableConfiguration.getMapperName();
            int ind = mapperName.lastIndexOf('.');
            if (ind == -1) {
                sb.append(mapperName);
            } else {
                sb.append(mapperName.substring(ind + 1));
            }
            sb.append(".xml");
        } else {
            sb.append(getTableClassName());
            sb.append("Dao.xml");
        }
        return sb.toString();
    }

    /**
     * 自定义Model类名
     *
     * @return
     */
    @Override
    protected void calculateModelAttributes() {
        String pakkage = calculateJavaModelPackage();

        StringBuilder sb = new StringBuilder();
        sb.append(pakkage);
        sb.append('.');
        sb.append(getTableClassName());
        sb.append("Key");
        setPrimaryKeyType(sb.toString());

        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(getTableClassName() + "PO");
        setBaseRecordType(sb.toString());

        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(getTableClassName());
        sb.append("WithBLOBs");
        setRecordWithBLOBsType(sb.toString());

        sb.setLength(0);
        sb.append(pakkage);
        sb.append('.');
        sb.append(getTableClassName());
        sb.append("Example");
        setExampleType(sb.toString());
    }



}
