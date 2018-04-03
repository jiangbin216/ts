mybatis-generator
===================================

mybatis-generator-core的自定义插件集. mybatis-generator是一个帮助自动生成代码的工具。可以生成mybatis的mapper类、example类、mapper.xml配置文件。  

支持jdk版本为1.6或者1.6+  

* 注意：使用次插件时，最好保证数据表有primary key字段，否则不会生成ByPrimaryKey等操作方法

> 自定义生成代码插件集

本插件集解决了mybatis-generator-core
```
1、不能查询动态语句（查询所有数据表字段，这样对内存消耗比较大）
- 自定义了MyQueryFieldsPlugin插件解决了这个问题
2、不支持分页
- 自定义了MySQLPaginationPlugin、OraclePaginationPlugin插件解决了这个问题
3、不能根据数据表对字段的注释生成javaBean的注释
- 自定义了MyCommentGenerator插件解决了这个问题
4、生成的Mapper，不符合我们一贯的dao风格
- 自定义了RenameJavaMapperTypePlugin、RenameXmlMapperFileNamePlugin插件解决了这个问题
5、序列化（官方已有序列化插件）
```
分页插件，推荐可以使用[PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)插件替换，这样不用修改xml中的sql语句，此插件使用上有一些要注意的问题。

配置请参考官方文档。

### 如何使用mybatis-generator插件集

* 查看关键配置文件

mybatis-generator.xml  
mysql.properties  
oracle.properties  
默认这3个文件都是要放到类路径下面，然后根据需求修改配置中对应参数即可。

* 运行Generator类

运行Generator（class Generator）类就可以自动生成mybatis需要的代码了。

> mybatis generator maven代码生成插件
```xml
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.3.2</version>
    <configuration>
        <configurationFile>src/test/resources/mybatis-generator.xml</configurationFile>
        <verbose>true</verbose>
        <overwrite>true</overwrite>
    </configuration>
    <executions>
        <execution>
            <id>Generate MyBatis Artifacts</id>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
    <dependencies>
        <dependency>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.3.2</version>
        </dependency>
    </dependencies>
</plugin>
```
然后执行
```sh
mvn  mybatis-generator:generate
```
