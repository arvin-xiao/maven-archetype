# maven-archetype

通过maven自定义maven项目骨架,创建archetype后在idea中添加骨架，构建新项目使用maven骨架并一键生成固定项目结构及初始文件

#生成archetype

打开cmd窗口，在刚才的maven项目的根目录中运行maven命令：

mvn archetype:create-from-project

#发布

进入 target/generated-sources/archetype 目录。执行 mvn install,当然也可以发布到私服，没有私服，就只安装到本地

到此，我们自己的archetype 模板已经创建成功。

# Archetype 使用
使用很简单，我们要指定我们archetype信息

mvn archetype:generate -DarchetypeGroupId=cn.medsci.archetype -DarchetypeArtifactId=medsci-archetype-archetype -DarchetypeVersion=1.0.0 -DgroupId=com.arvin.productName -DartifactId=projectName -Dpackage=com.arvin.productName.projectName -Dversion=1.0.0 -DappName=projectName

##注意事项

上面语句是一条完整语句，不能有空格
-D 前面都有个空格
-DarchetypeArtifactId 注意后面有archetype

其中最后的5个参数根据实际的情况进行修改，基本规范如下：

groupId：项目工程的groupId；
artifactId：项目工程的artifactId；
package：项目工程的顶级package；
version：项目工程的版本号；
appName：项目工程打成包时的名字，当基于tomcat插件进行调试时，此名称也作为ContextPath名称。

##总结

使用 archetype 构建项目，不用让我们在添加各种 pom 文件或者 copy 代码,构建项目骨架简单迅速。

