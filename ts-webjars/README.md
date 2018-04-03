# ts-webjars

所有第三方css、js等文件都应该放到这个模块webjars文件夹中。  

如果是某个项目的个性化js、css等，最好不要放到这个模块中，因为这个ts-webjars项目可以供所有其他要使用它的模块、项目使用，引入某个项目的个性化js、css，其他模块不一定用到，会占用其他项目的空间。而且也不便维护这些个性化js、css等。

> 使用方法

在需要使用到这些js、css等文件的项目中引入
```xml
<dependency>
    <groupId>cn.ts</groupId>
    <artifactId>ts-webjars</artifactId>
    <version>1.0.0</version>
</dependency>
```
然后在需要使用的页面，如jsp、html等使用如下代码引入（假设项目跟路径是${ctx}=${pageContext.request.contextPath}），图片、字体等同理
```html
<link type="text/css" rel="stylesheet" src="${ctx}/webjars/bootstrap.min.css" />
<script type="text/javascript" src="${ctx}/webjars/jquery.min.js" /> 
```
