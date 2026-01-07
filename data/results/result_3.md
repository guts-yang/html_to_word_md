# JavaBean


# Presenter Notes


 Source: [ch6.md](ch6.md) 1/78 

# JavaBean

- 
在软件开发时，一些数据和功能需要在很多地方使用，为了方便将他们进行移植，可以利用 **JavaBean** 技术对这些数据和功能进行封装，做到一次编写，到处运行。


- 
**JavaBean** 是用Java语言书写的Java类，并且遵守 **JavaBeans API** 规范:

- 
提供一个默认的`public`修饰的无参构造方法。 （为了实例化对象）


- 
需要被序列化并且实现了`java.io.Serializable`接口。 （为了保存对象的状态）


- 
有一系列可读写属性。


- 
有一系列的`"getter"`或`"setter"`方法。 （为了获取和设置字段的值）




- 
`JavaBean`是一种规范，而不是一种技术或工具。




# Presenter Notes


 Source: [ch6.md](ch6.md) 2/78 

# JavaBeans属性

- 
一个`JavaBean`对象的属性应该是可访问的。 这个属性可以是任意合法的`Java`数据类型，包括自定义`Java`类。


- 
一个`JavaBean`对象的属性可以是**可读写**，或**只读**，或**只写**。

- 一个**只读**的属性只提供 **getPropertyName()** 方法，
- 一个**只写**的属性只提供 **setPropertyName()** 方法。




# Presenter Notes


 Source: [ch6.md](ch6.md) 3/78 

# JavaBeans属性


`JavaBean`对象的属性通过`JavaBean`实现类中提供的两个方法来访问：

**方法****描述**`getPropertyName()`这个方法也称为访问器。如果属性的名称为`myName`，那么这个方法的名字就要写成`getMyName()`来读取这个属性。`setPropertyName()`这个方法也称为写入器。如果属性的名称为`myName`，那么这个方法的名字就要写成`setMyName()`来写入这个属性。
# Presenter Notes


 Source: [ch6.md](ch6.md) 4/78 

# JavaBean程序示例

 1publicclassStudentsBeanimplementsjava.io.Serializable 2{ 3privateStringfirstName=null; 4privateStringlastName=null; 5privateintage=0; 6 7publicStudentsBean(){ 8} 910publicStringgetFirstName(){11returnfirstName;12}1314publicStringgetLastName(){15returnlastName;16}1718publicintgetAge(){19returnage;20}2122publicvoidsetFirstName(StringfirstName){23this.firstName=firstName;24}2526publicvoidsetLastName(StringlastName){27this.lastName=lastName;28}2930publicvoidsetAge(Integerage){31this.age=age;32}33}

# Presenter Notes


 Source: [ch6.md](ch6.md) 5/78 

# 访问JavaBeans

- `<jsp:useBean>`标签可以在`JSP`中声明一个`JavaBean`，然后使用。- 声明后，`JavaBean`对象就成了脚本变量，可以通过脚本元素或其他自定义标签来访问。




<jsp:useBean>标签的语法格式如下：

1<jsp:useBean id="bean's name" scope="bean's scope" typeSpec/>

其中，根据具体情况，**scope** 的值可以是 **page**，**request**，**session** 或 **application**。 **id** 值可任意只要不和同一`JSP`文件中其它 `<jsp:useBean>` 中 **id** 值一样就行了。


# Presenter Notes


 Source: [ch6.md](ch6.md) 6/78 

# JavaBean程序示例


<jsp:useBean>标签的一个简单的用法：

 1<html> 2<head> 3<title>useBeanExample</title> 4</head> 5<body> 6 7<jsp:useBean id="date" class="java.util.Date" /> 8<p>Thedate/timeis<%=date%> 910</body>11</html>

它将会产生如下结果：


The date/time is Thu Sep 30 11:18:11 GST 2013


# Presenter Notes


 Source: [ch6.md](ch6.md) 7/78 

# 访问JavaBeans对象的属性

- 在`<jsp:useBean>`标签主体中:- 使用`<jsp:getProperty>`标签来调用`getter`方法
- 使用`<jsp:setProperty>`标签来调用`setter`方法




语法格式如下：

1<jsp:useBean id="id" class="bean's class" scope="bean's scope">2<jsp:setPropertyname="bean's id"property="property name"3value="value"/>4<jsp:getProperty name="bean's id" property="property name"/>5........... 6</jsp:useBean>
- **name** 属性指的是 **Bean** 的 **id** 属性。
- **property** 属性指的是想要调用的 **getter** 或 **setter** 方法。


# Presenter Notes


 Source: [ch6.md](ch6.md) 8/78 

# JavaBean的属性访问的程序示例

 1<html> 2<head> 3<title>getandsetpropertiesExample</title> 4</head> 5<body> 6 7<jsp:useBeanid="students" 8class="com.tutorialspoint.StudentsBean"> 9<jsp:setPropertyname="students"property="firstName"10value="Zara"/>11<jsp:setPropertyname="students"property="lastName"12value="Ali"/>13<jsp:setPropertyname="students"property="age"14value="10"/>15</jsp:useBean>1617<p>StudentFirstName: 18<jsp:getProperty name="students" property="firstName"/>19</p>20<p>StudentLastName: 21<jsp:getProperty name="students" property="lastName"/>22</p>23<p>StudentAge: 24<jsp:getProperty name="students" property="age"/>25</p>2627</body>28</html>

# Presenter Notes


 Source: [ch6.md](ch6.md) 9/78 

# Expression Language（EL）


# Presenter Notes


 Source: [ch6.md](ch6.md) 10/78 

# Expression Language（EL）

- 
`Expression Language`（`EL`）又称`JSP`表达式语言，它主要用于替换`JSP`页面中的脚本表达式`<%= %>`，从各种类型的`Web`域中检索`Java`对象、获取数据。

- 灵感来自于`ECMAScript`和`XPath`表达式语言，借鉴了`JavaScript`多类型转换无关性的特点，提供了在`JSP`中简化表达式的方法。
- 基于可用的命名空间（`PageContext` 属性） 嵌套属性和对集合 操作符（算术型 关系型和逻辑型）的访问符 映射到 `Java` 类中静态方法的可扩展函数以及一组隐式对象。




# Presenter Notes


 Source: [ch6.md](ch6.md) 11/78 

# Expression Language（EL）

- `Web`服务器对于`request`域中的属性是以`Object`类型来存储的，在得到该属性时使用的`Java`语言脚本就应该是`(Object)request.getAttribute("xxx")`，它对于实际应用还必须进行强制类型转换。- 采用`EL`可以将用户从这种类型转换的繁琐工作脱离出来，允许用户直接使用`EL`表达式取得的值，而不用关心它是什么类型的。
- 可以很方便地访问`JavaBean`属性，访问数组，访问`List`集合和`Map`集合等。
- 从`scope`中得到参数时可以自动转换类型，因此对于类型的限制更加宽松。




# Presenter Notes


 Source: [ch6.md](ch6.md) 12/78 

# JSP 表达式语言

- 
`JSP`表达式语言（`EL`）使得访问存储在`JavaBean`中的数据变得非常简单。

- 
`JSP EL`既可以用来创建算术表达式也可以用来创建逻辑表达式。


- 
在`JSP EL`表达式内可以使用整型数，浮点数，字符串，常量`true`、`false`，还有`null`。






当需要在`JSP`标签中指定一个属性值时，只需要简单地使用字符串即可：

1<jsp:setProperty name="box" property="perimeter" value="100"/>

也允许指定一个表达式来表示属性值。

1${expr} 

其中，**expr** 指的是表达式。


# Presenter Notes


 Source: [ch6.md](ch6.md) 13/78 

# 通用操作符

- 
在`JSP EL`中通用的操作符是"."和"[]"。

- 这两个操作符允许通过内嵌的`JSP`对象访问各种各样的`JavaBean`属性。




举例来说，上面的`<jsp:setProperty>`标签可以使用表达式语言改写成如下形式：

1<jsp:setPropertyname="box"property="perimeter"2value="${2*box.width+2*box.height}"/>
- 
当`JSP`编译器在属性中见到"${}"格式后，它会产生代码来计算这个表达式，并且产生一个替代品来代替表达式的值。

- 当要存取的属性名称中包含一些特殊字符，如.或?等并非字母或数字的符号，就一定要使用`[ ]`。
- 如果要动态取值时，就可以用`[ ]`来做，而.无法做到动态取值。




# Presenter Notes


 Source: [ch6.md](ch6.md) 14/78 

# 通用操作符


可以在标签的模板文本中使用表达式语言。

1<jsp:text>2BoxPerimeteris:${2*box.width+2*box.height} 3</jsp:text>

在`EL`表达式中可以使用圆括号来组织子表达式。


比如 `${(1 + 2) * 3}`等于9，但是`${1 + (2 * 3)}` 等于7。

- 停用对`EL`表达式，


需要使用 **page-- 指令将 **isELIgnored** 属性值设为 **true**

1<%@pageisELIgnored="true"%>

这样，`EL`表达式就会被忽略。

- 若设为 **false**，则容器将会计算`EL`表达式。


# Presenter Notes


 Source: [ch6.md](ch6.md) 15/78 

# 基础操作符


`EL`表达式支持大部分`Java`所提供的算术和逻辑操作符：

**操作符****描述**`.`访问一个Bean属性或者一个映射条目`[]`访问一个数组或者链表的元素`( )`组织一个子表达式以改变优先级`+`加`-`减或负`*`乘`/` or `div`除`%` or `mod`取模`==` or `eq`测试是否相等`!=` or `ne`测试是否不等`<` or `lt`测试是否小于`>` or `gt`测试是否大于`<=` or `le`测试是否小于等于`>=` or `gt`测试是否大于等于`&&` or `and`测试逻辑与`||` or `or`测试逻辑或`!` or `not`测试取反`empty`测试是否空值
# Presenter Notes


 Source: [ch6.md](ch6.md) 16/78 

# JSP EL中的函数

- 
`JSP EL`允许您在表达式中使用函数。

- 这些函数必须被定义在自定义标签库中。




函数的使用语法如下：

1${ns:func(param1,param2,...)} 
- **ns**指的是命名空间（`namespace`），**func**指的是函数的名称
- **param1**指的是第一个参数
- **param2** 指的是第二个参数，以此类推


# Presenter Notes


 Source: [ch6.md](ch6.md) 17/78 

# JSP EL中的函数

- 例子


有函数 **fn:length**，在`JSTL`库中定义，可以像下面这样来获取一个字符串的长度：

1${fn:length("Getmylength")} 
- 要使用任何标签库中的函数，需要将这些库安装在服务器中
- 然后使用`<taglib>`标签在`JSP`文件中包含这些库


# Presenter Notes


 Source: [ch6.md](ch6.md) 18/78 

# JSP EL隐含对象

**隐含对象****描述**`pageScope``page` 作用域`requestScope``request` 作用域`sessionScope``session` 作用域`applicationScope``application` 作用域`param Request`对象的参数，字符串`paramValues``Request`对象的参数，字符串集合`header``HTTP` 信息头，字符串`headerValues``HTTP` 信息头，字符串集合`initParam`上下文初始化参数`cookie``Cookie`值`pageContext`当前页面的`pageContext`
可以在表达式中直接使用这些对象，就像使用变量一样。


# Presenter Notes


 Source: [ch6.md](ch6.md) 19/78 

# JSP EL隐含对象

- `pageContext`对象- `pageContext`对象是`JSP`中`pageContext`对象的引用
- 通过`pageContext`对象, 可以访问`request`对象




比如, 访问`request`对象传入的查询字符串, 就像这样：

1${pageContext.request.queryString} 
- Scope对象- `pageScope`, `requestScope``sessionScope``applicationScope`变量用来访问存储在各个作用域层次的变量。
- 举例来说, 如果您需要显式访问在`applicationScope`层的`box`变量，可以这样来访问：**applicationScope.box**。




# Presenter Notes


 Source: [ch6.md](ch6.md) 20/78 

# JSP EL隐含对象

- `param`和`paramValues`对象- **param** 和 **paramValues**对象用来访问参数值，通过使用 **request.getParameter** 方法和 **request.getParameterValues** 方法。
- 
例如


访问一个名为 **order** 的参数，可以这样使用表达式：`${param.order}`，或者`${param["order"]}`。






# Presenter Notes


 Source: [ch6.md](ch6.md) 21/78 

# JSP EL隐含对象

- 例子:


访问`request`中的`username`参数：

 1<%@pageimport="java.io.*,java.util.*"%> 2<%Stringtitle="Accessing Request Param";%> 3<html> 4<head> 5<title><%out.print(title);%></title> 6</head> 7<body> 8<center> 9<h1><%out.print(title);%></h1>10</center>11<divalign="center">12<p>${param["username"]}</p>13</div>14</body>15</html>

**param** 对象返回单一的字符串，而 **paramValues** 对象则返回一个字符串数组。


# Presenter Notes


 Source: [ch6.md](ch6.md) 22/78 

# header和headerValues对象

- 
`header`和`headerValues`对象用来访问信息头，通过使用 `request.getHeader`方法和`request.getHeaders`方法。


- 
例如


要访问一个名为`user-agent`的信息头，可以这样使用表达式：`\${header.user-agent}，或者\${header["user-agent"]}`。




# Presenter Notes


 Source: [ch6.md](ch6.md) 23/78 

# header和headerValues对象

- 例子:


访问`user-agent`信息头：

 1<%@pageimport="java.io.*,java.util.*"%> 2<%Stringtitle="User Agent Example";%> 3<html> 4<head> 5<title><%out.print(title);%></title> 6</head> 7<body> 8<center> 9<h1><%out.print(title);%></h1>10</center>11<divalign="center">12<p>${header["user-agent"]}</p>13</div>14</body>15</html>

# Presenter Notes


 Source: [ch6.md](ch6.md) 24/78 

# JavaServer Pages Standard Tag Library


# Presenter Notes


 Source: [ch6.md](ch6.md) 25/78 

# JSP 标准标签库（JSTL）

- 
`JSP`标准标签库（`JSTL`：`JavaServer Pages Standard Tag Library`）是一个JSP标签集合，它封装了`JSP`应用的通用核心功能。


- 
`JSTL`支持通用的、结构化的任务，比如迭代，条件判断，`XML`文档操作，国际化标签，`SQL`标签。 除了这些，它还提供了一个框架来使用集成`JSTL`的自定义标签。


- 
根据`JSTL`标签所提供的功能，可以将其分为5个类别

- 核心标签 : `Core`
- 格式化标签 : `l18N`
- `SQL` 标签 : `SQL`
- `XML` 标签 : `XML`
- `JSTL` 函数 : `Functions`




# Presenter Notes


 Source: [ch6.md](ch6.md) 26/78 

# JSTL 库安装

- 
`Apache Tomcat`安装`JSTL` 库步骤如下：

- 
从`Apache`的标准标签库中下载的二进包(`jakarta-taglibs-standard-current.zip`)。 下载地址：[http://archive.apache.org/dist/jakarta/taglibs/standard/binaries/](http://archive.apache.org/dist/jakarta/taglibs/standard/binaries/)


- 
下载`jakarta-taglibs-standard-1.1.1.zip` 包并解压，


- 
将`jakarta-taglibs-standard-1.1.1/lib/`下的两个`jar`文件： **standard.jar** 和 **jstl.jar** 文件拷贝到 **/WEB-INF/lib/** 下。


- 
使用任何库，你必须在每个JSP文件中的头部包含`<taglib`>。






.

1<%@tagliburi="http://java.sun.com/jsp/jstl/core"prefix="c"%>2<%@tagliburi="http://java.sun.com/jsp/jstl/functions"prefix="fn"%>3<%@tagliburi="http://java.sun.com/jsp/jstl/fmt"prefix="fmt"%>4<%@tagliburi="http://java.sun.com/jsp/jstl/xml"prefix="x"%>5<%@tagliburi="http://java.sun.com/jsp/jstl/sql"prefix="sql"%>

# Presenter Notes


 Source: [ch6.md](ch6.md) 27/78 

# 核心标签


引用核心标签库的语法：

1<%@taglibprefix="c"2uri="http://java.sun.com/jsp/jstl/core"%\>
- 核心标签是最常用的JSTL标签。

**标签****描述**<c:out>用于在JSP中显示数据，就像<%= ... ><c:set>用于保存数据<c:remove>用于删除数据<c:catch>用来处理产生错误的异常状况，并且将错误信息储存起来<c:if>与我们在一般程序中用的if一样<c:choose>本身只当做和的父标签<c:when>的子标签，用来判断条件是否成立<c:otherwise>的子标签，接在标签后，当标签判断为false时被执行<c:import>检索一个绝对或相对 URL，然后将其内容暴露给页面<c:forEach>基础迭代标签，接受多种集合类型<c:forTokens>根据指定的分隔符来分隔内容并迭代输出<c:param>用来给包含或重定向的页面传递参数<c:redirect>重定向至一个新的URL.<c:url>使用可选的查询参数来创造一个URL
# Presenter Notes


 Source: [ch6.md](ch6.md) 28/78 

# 表达式控制标签

## <c:out>标签：

- 用来显示数据对象（字符串 表达式）的内容或结果。
- 相当于 <% out.println(“字符串”)%> 或 <%=表达式%>。 


语法：

1<c:outvalue=”要显示的数据对象”[escapeXml=”true|false”][default=”默认值”]>2<c:outvalue=”要显示的数据对象”[escapeXml=”true|false”]>默认值</c:out>

其中， `value`：指定要输出的变量或表达式。 `escapeXml`：设定是否转换特殊字符。 `default`：为默认输出结果。如果使用表达式得到的结果为null（注意与空区别），将会输出默认结果。


# Presenter Notes


 Source: [ch6.md](ch6.md) 29/78 

# 表达式控制标签

## <c:set>标签：

- 主要用于将变量存取于JSP范围中或`JavaBean`属性中。


语法：

1<c:setvalue="值1"var="name1"[scope="page|request|session|application"]>2<c:setvar=”name2”[scope=”page|request|session|application”]>3<c:setvalue=”值3”target=”JavaBean对象”property=”属性名”/>4<c:settarget=”JavaBean对象”property=”属性名”>值4</c:set>

# Presenter Notes


 Source: [ch6.md](ch6.md) 30/78 

# 表达式控制标签

## <c:remove>标签：

- 主要用来从指定的`JSP`范围内移除指定的变量。


语法：

1<c:removevar=”变量名”[scope=”page|request|session|application”]/>

其中`var`属性是必须的，scope可以以省略。

## <c:catch>标签：

- 用来处理`JSP`页面中产生的异常，并将异常信息存储。 


语法：

1<c:catchvar=”name1”>容易产生异常的代码</c:catch>

`var`表示由用户定义存取异常信息的变量的名称。省略后也可以实现异常的捕获，当就不能显示的输出异常信息。


# Presenter Notes


 Source: [ch6.md](ch6.md) 31/78 

# 流程控制标签

### 流程控制标签主要用于对页面简单业务逻辑进行控制。

#### 流程控制标签包含有4个：

- <c:if>标签 
- <c:choose>标签 
- <c:when>标签和
- <c:otherwise>标签。


# Presenter Notes


 Source: [ch6.md](ch6.md) 32/78 

# 流程控制标签

## <c:if>标签：

- 同程序中的`if`作用相同，用来实现条件控制。


语法：

1<c:iftest=”条件1”var=”name”[scope=”page|request|session|application”]>2<c:iftest=”条件2”var=”name”[scope=”page|request|session|application”]>结果2</c:if>

参数说明： 

- test属性用于存放判断的条件，一般使用EL表达式来编写。 
- var指定名称用来存放判断的结果类型为true或false。 
- scope用来存放var属性存放的范围。


# Presenter Notes


 Source: [ch6.md](ch6.md) 33/78 

# 流程控制标签

### <c:choose> <c:when>和<c:otherwise>标签：

- 这3个标签通常情况下是一起使用的，<c:choose>标签作为<c:when>和<c:otherwise>标签的父标签来使用。

### <c:choose>标签只能和<c:when>标签共同使用。

 1<c:choose> 2<c:whentest="条件1"> 3…..//业务逻辑1 4</c:when> 5<c:whentest="条件2"> 6…..//业务逻辑2 7</c:when> 8<spanstyle="white-space: pre;"></span><c:otherwise> 9…..//业务逻辑310</c:otherwise>11</c:choose>

# Presenter Notes


 Source: [ch6.md](ch6.md) 34/78 

# 流程控制标签

### <c:when>标签的使用方式，该标签都条件进行判断，一般情况下和<c:choose>共同使用。

1<c:whentext=”条件”>2表达式3</c:when>
### <c:otherwise>不含有参数，只能跟<c:when>共同使用，并且在嵌套中只允许出现一次。

1<c:otherwise>2表达式3</c:otherwise>

# Presenter Notes


 Source: [ch6.md](ch6.md) 35/78 

# 循环标签

### 循环标签主要实现迭代操作。

- 主要包含两个标签：<c:forEach>和<c:forTokens>标签。

### <c:forEach>标签：


该标签根据循环条件遍历集合（Collection）中的元素。

1<c:forEachvar=”name”items=”Collection”varStatus=”StatusName”begin=”begin”end=”end”step=”step”>2所有内容3</c:forEach>

# Presenter Notes


 Source: [ch6.md](ch6.md) 36/78 

# 循环标签

- var设定变量名用于存储从集合中取出元素。 
- items指定要遍历的集合。 
- varStatus设定变量名，该变量用于存放集合中元素的信息。
- begin end用于指定遍历的起始位置和终止位置（可选）。 
- step 指定循环的步长。


# Presenter Notes


 Source: [ch6.md](ch6.md) 37/78 

# <c:forEach>标签

### 循环标签属性说明

名称EL类型是否必须默认值varNString是无itemsYArrays是无beginYint否0endYint否集合中最后一个元素stepYint否1varStatusNString否无
# Presenter Notes


 Source: [ch6.md](ch6.md) 38/78 

# <c:forEach>标签

### 其中varStatus有4个状态属性：

属性名类型说明indexint当前循环的索引值countint循环的次数fristboolean是否为第一个位置lastboolean是否为第二个位置
# Presenter Notes


 Source: [ch6.md](ch6.md) 39/78 

# 循环标签

## <c:forTokens> 标签：


该标签用于浏览字符串，并根据指定的字符将字符串截取。

1<c:forTokensitems=”strigOfTokens”delims=””delimiters[var=”name”begin=”begin”end=”end”step=”len”varStatus=”statusName”]>
- items指定被迭代的字符串。 
- delims指定使用的分隔符。 
- var指定用来存放遍历到的成员。 
- begin指定遍历的开始位置（int型从取值0开始）。 
- end指定遍历结束的位置（int型，默认集合中最后一个元素）。 
- step遍历的步长（大于0的整型）。 
- varStatus存放遍历到的成员的状态信息。


注：<c:forToken>的属性varStatus的使用同<c:forEach>的使用方法相同。


# Presenter Notes


 Source: [ch6.md](ch6.md) 40/78 

# URL操作标签

## JSTL包含3个与URL操作有关的标签：

#### <c:import> 显示其他文件的内容

#### <c:redirect> 网页导向

#### <c:url> 产生`URL`


# Presenter Notes


 Source: [ch6.md](ch6.md) 41/78 

# URL操作标签

## <c:import>标签

- 
该标签可以把其他静态或动态文件包含到本`JSP`页面。


- 
同<jsp:include>的区别为：

- <jsp:include>只能包含同一个`web`应用中的文件
- <c:import>可以包含其他`web`应用中的文件，甚至是网络上的资源。




.

1<c:importurl=”url”[context=”context”][value=”value”][scope=”page|request|session|application”][charEncoding=”encoding”]>23<c:importurl=”url”varReader=”name”[context=”context”][charEncoding=”encoding”]>

# Presenter Notes


 Source: [ch6.md](ch6.md) 42/78 

# URL操作标签

- `<c:import>`标签


参数说明

名称说明EL类型必须默认值`url`被导入资源的URL路径YString是无`context`相同服务器下其他的web工程，必须以“””开头YString否无`var`以String类型存入被包含文件的内容。NString否无`Scope`var变量的JSP范围NString否page`charEncoding`被导入文件的编码格式YString否无`varReader`以Reader类型存储被包含文件内容NString否无
# Presenter Notes


 Source: [ch6.md](ch6.md) 43/78 

# URL操作标签

- 
`<c:import>`标签

- `URL`为资源的路径，当应用的资源不存在时系统会抛出异常，因此该语句应该放在`<c:catch>``</c:catch>`语句块中捕获。
- 应用资源有两种方式：绝对路径和相对路径。
- 如果访问`webapps`管理文件夹中其他`web`应用就要用`context`属性。




# Presenter Notes


 Source: [ch6.md](ch6.md) 44/78 

# URL操作标签

- <c:redirect>标签- 用来实现了请求的重定向。
- 同时可以在`url`中加入指定的参数。




例如：对用户输入的用户名和密码进行验证，如果验证不成功重定向到登录页面； 或者实现`web`应用不同模块之间的衔接。 

1<c:redirecturl=”url”[context=”context”]>23<c:redirecturl=”url”[context=”context”]>4<c:paramname=”name1”value=”value1”>5</c:redirect>
- `url` 指定重定向页面的地址，可以是一个`string`类型的绝对地址或相对地址。 
- `context` 用于导入其他`web`应用中的页面。


# Presenter Notes


 Source: [ch6.md](ch6.md) 45/78 

# URL操作标签

- 
`<c:url>`标签 该标签用于动态生成一个`String`类型的URL，可以同`<c:redirect>`标签共同使用，也可以使用`html`的标签实现超链接。


!jsp 




指定一个`url`不做修改，可以选择把该`url`存储在`JSP`不同的范围中。

1<c:urlvalue=”value”[var=”name”][scope=”page|request|session|application”][context=”context”]>2<c:paramname=”参数名”value=”值”>3</c:url>

给`url`加上指定参数及参数值，可以选择以`name`存储该`url`。


# Presenter Notes


 Source: [ch6.md](ch6.md) 46/78 

# 格式化标签

- `JSTL`标签提供了对国际化（`I18N`）的支持，它可以根据发出请求的客户端地域的不同来显示不同的语言。同时还提供了格式化数据和日期的方法。
- 实现这些功能需要I18N格式标签库（`I18N-capable formation tags liberary`）。


引入该标签库的方法为：

1<%@taglibprefix="fmt"2uri="http://java.sun.com/jsp/jstl/fmt"%>
- `I18N`格式标签库提供了11个标签，这些 标签从功能上可以划分为3类如下：


# Presenter Notes


 Source: [ch6.md](ch6.md) 47/78 

# 格式化标签

**标签****描述**<fmt:formatNumber>使用指定的格式或精度格式化数字<fmt:parseNumber>解析一个代表着数字，货币或百分比的字符串<fmt:formatDate>使用指定的风格或模式格式化日期和时间<fmt:parseDate>解析一个代表着日期或时间的字符串<fmt:bundle>绑定资源<fmt:setLocale>指定地区<fmt:setBundle>绑定资源<fmt:timeZone>指定时区<fmt:setTimeZone>指定时区<fmt:message>显示资源配置文件信息<fmt:requestEncoding>设置request的字符编码
# Presenter Notes


 Source: [ch6.md](ch6.md) 48/78 

# 数字日期格式化标签


数字日期格式化标签共有6个，用来将数字或日期转换成设定的格式。

 1<frm:formatNumbervalue=”被格式化的数据”[type=”number|currency|percent”] 2[pattern=”pattern”] 3[currencyCode=”code”] 4[currencySymbol=”symbol”] 5[groupingUsed=”true|false”] 6[maxIntergerDigits=”maxDigits”] 7[minIntergerDigits=”minDigits”] 8[maxFractionDigits=”maxDigits”] 9[minFractionDigits=”minDigits”]10[var=”name”]11[scope=page|request|session|application]12/>1314<frm:formatNumber[type=”number|currency|percent”]15[pattern=”pattern”]16[currencyCode=”code”]17[currencySymbol=”symbol”]18[groupingUsed=”true|false”]19[maxIntergerDigits=”maxDigits”]20[minIntergerDigits=”minDigits”]21[maxFractionDigits=”maxDigits”]22[minFractionDigits=”minDigits”]23[var=”name”][scope=page|request|session|application]24/>

# Presenter Notes


 Source: [ch6.md](ch6.md) 49/78 

# <frm:parseNumber>标签


将格式化后的**数字****货币****百分比**都转化为数字类型。

 1<fmt:parseNumbervalue="number"[type=”number|currency|percent”] 2[pattern=”pattern”] 3[parseLocale=”locale”] 4[intergerOnly=”true|false”] 5[scope=page|request|session|application”] 6/> 7 8<fmt:parseNumber[type=”number|currency|percent”] 9[pattern=”pattern”]10[parseLocale=”locale”]11[intergerOnly=”true|false”]12[scope=”page|request|session|application”]>13Number 14</fmt:parseNumber>

# Presenter Notes


 Source: [ch6.md](ch6.md) 50/78 

# <fmt:formatDate>标签


该标签主要用来格式化日期和时间。

1<fmt:formatDatevalue=”date”[type=”time|date|both”]2[pattern=”pattern”]3[dateStyle=”default|short|medium|long|full”]4[timeStyle=”default|short|medium|long|full”]5[timeZone=”timeZone”]6[var=”name”]7[scope=”page|request|session|application”]8/>

# Presenter Notes


 Source: [ch6.md](ch6.md) 51/78 

# <fmt:parseDate>标签


主要将字符串类型的时间或日期转化为时间或日期对象。

 1<fmt:parseDatevalue=”date”[type=”time|date|both”] 2[pattern=”pattern”] 3[parseLocale=”locale”] 4[dateStyle=”default|short|medium|long|full”] 5[timeStyle=”default|short|medium|long|full”] 6[timeZone=”timezone”] 7[var=”name”] 8[scope=”page|request|session|application”] 9/>1011<fmt:parseDate[type=”time|date|both”]12[pattern=”pattern”]13[parseLocale=”locale”]14[dateStyle=”default|short|medium|long|full”]15[timeStyle=”default|short|medium|long|full”]16[timeZone=”timezone”]17[var=”name”]18[scope=”page|request|session|application”]>19Date20</fmt:parseDate>

# Presenter Notes


 Source: [ch6.md](ch6.md) 52/78 

# SQL标签

- `JSTL SQL`标签库提供了与关系型数据库进行交互的标签。- `Oracle`
- `MySQL`
- `SQL Server`




引用`SQL`标签库的语法如下：

1<%@taglibprefix="sql"2uri="http://java.sun.com/jsp/jstl/sql"%>

# Presenter Notes


 Source: [ch6.md](ch6.md) 53/78 

# SQL标签

**标签****描述**`<sql:setDataSource>`指定数据源`<sql:query>`运行`SQL`查询语句`<sql:update>`运行`SQL`更新语句`<sql:param>`将`SQL`语句中的参数设为指定值`<sql:dateParam>`将`SQL`语句中的日期参数设为指定的`java.util.Date` 对象值`<sql:transaction>`在共享数据库连接中提供嵌套的数据库行为元素，将所有语句以一个事务的形式来运行
# Presenter Notes


 Source: [ch6.md](ch6.md) 54/78 

# XML 标签

- `JSTL XML`标签库提供了创建和操作`XML`文档的标签。


引用`XML`标签库的语法如下：

1<%@taglibprefix="x"2uri="http://java.sun.com/jsp/jstl/xml"%>
- 
在使用`xml`标签前，你必须将 **XML** 和 **XPath** 的相关包拷贝至你的 **/WEB-INF/lib/** 下:

- `XercesImpl.jar`: 下载地址： [http://www.apache.org/dist/xerces/j/](http://www.apache.org/dist/xerces/j/)
- `xalan.jar`: 下载地址： [http://xml.apache.org/xalan-j/index.html](http://xml.apache.org/xalan-j/index.html)




# Presenter Notes


 Source: [ch6.md](ch6.md) 55/78 

# XML 标签

**标签****描述**`<x:out>`与`<%= ... >`,类似，不过只用于XPath表达式`<x:parse>`解析 XML 数据`<x:set>`设置`XPath`表达式`<x:if>`判断`XPath`表达式，若为真，则执行本体中的内容，否则跳过本体`<x:forEach>`迭代`XML`文档中的节点`<x:choose>``<x:when>`和`<x:otherwise>`的父标签`<x:when>``<x:choose>`的子标签，用来进行条件判断`<x:otherwise>``<x:choose>子标签，当`断为false时被执行`<x:transform>`将`XSL`转换应用在`XML`文档中`<x:param>`与`<x:transform>`共同使用，用于设置`XSL`样式表
# Presenter Notes


 Source: [ch6.md](ch6.md) 56/78 

# JSTL函数

- `JSTL`包含一系列标准函数，大部分是通用的字符串处理函数。


引用`JSTL`函数库的语法如下：

1<%@taglibprefix="fn"2uri="http://java.sun.com/jsp/jstl/functions"%>
**函数****描述**`fn:contains()`测试输入的字符串是否包含指定的子串`fn:containsIgnoreCase()`测试输入的字符串是否包含指定的子串，大小写不敏感`fn:endsWith()`测试输入的字符串是否以指定的后缀结尾`fn:escapeXml()`跳过可以作为XML标记的字符`fn:indexOf()`返回指定字符串在输入字符串中出现的位置`fn:join()`将数组中的元素合成一个字符串然后输出`fn:length()`返回字符串长度`fn:replace()`将输入字符串中指定的位置替换为指定的字符串然后返回`fn:split()`将字符串用指定的分隔符分隔然后组成一个子字符串数组并返回`fn:startsWith()`测试输入字符串是否以指定的前缀开始`fn:substring()`返回字符串的子集`fn:substringAfter()`返回字符串在指定子串之后的子集`fn:substringBefore()`返回字符串在指定子串之前的子集`fn:toLowerCase()`将字符串中的字符转为小写`fn:toUpperCase()`将字符串中的字符转为大写`fn:trim()`移除首位的空白符
# Presenter Notes


 Source: [ch6.md](ch6.md) 57/78 

# JSP XML 数据处理


# Presenter Notes


 Source: [ch6.md](ch6.md) 58/78 

# JSP XML 数据处理

- 当通过`HTTP`发送XML数据时，就有必要使用`JSP`来处理传入和流出的`XML`文档了，比如`RSS`文档。

- `JSTL XML`标签库提供了创建和操作`XML`文档的标签。


引用`XML`标签库的语法如下：

1<%@taglibprefix="x"2uri="http://java.sun.com/jsp/jstl/xml"%>
- 
在使用`xml`标签前，你必须将 **XML** 和 **XPath** 的相关包拷贝至你的 **/WEB-INF/lib/** 下:

- **XercesImpl.jar** : 下载地址： [http://www.apache.org/dist/xerces/j/](http://www.apache.org/dist/xerces/j/)
- **xalan.jar** : 下载地址： [http://xml.apache.org/xalan-j/index.html](http://xml.apache.org/xalan-j/index.html)




# Presenter Notes


 Source: [ch6.md](ch6.md) 59/78 

# 在JSP中处理XML


`JSTL XML`标签库提供的创建和操作XML文档的标签。

**标签****描述**`<x:out>`与`<%= ... >`,类似，不过只用于`XPath`表达式`<x:parse>`解析 `XML` 数据`<x:set>`设置`XPath`表达式`<x:if>`判断`XPath`表达式，若为真，则执行本体中的内容，否则跳过本体`<x:forEach>`迭代`XML`文档中的节点`<x:choose>``<x:when>`和`<x:otherwise>`的父标签`<x:when>``<x:choose>`的子标签，用来进行条件判断`<x:otherwise>``<x:choose>`的子标签，当`<x:when>`判断为`false`时被执行`<x:transform>`将`XSL`转换应用在`XML`文档中`<x:param>`与`<x:transform>`共同使用，用于设置`XSL`样式表
# Presenter Notes


 Source: [ch6.md](ch6.md) 60/78 

# 使用JSP发送XML


使用`JSP`发送XML内容就和发送`HTML`内容一样。 唯一的不同就是您需要把页面的context属性设置为`text/xml`。 要设置 **context** 属性，使用`<%@page%>`命令，就像这样：

1<%@pagecontentType="text/xml"%>
- 例子：


向浏览器发送如下`books.xml`文件内容：

 1<%@pagecontentType="text/xml"%>  2 3<books> 4<book> 5<name>PadamHistory</name> 6<author>ZARA</author> 7<price>100</price> 8</book> 9<book>10<name>GreatMistry</name>11<author>NUHA</author>12<price>2000</price>13</book>14</books>

# Presenter Notes


 Source: [ch6.md](ch6.md) 61/78 

# main.jsp文件

 1<%@taglibprefix="c"uri="http://java.sun.com/jsp/jstl/core"%> 2<%@taglibprefix="x"uri="http://java.sun.com/jsp/jstl/xml"%> 3 4<html> 5<head> 6<title>JSTLx:parseTags</title> 7</head> 8<body> 9<h3>BooksInfo:</h3>10<c:importvar="bookInfo"url="http://localhost:8080/books.xml"/>1112<x:parsexml="${bookInfo}"var="output"/>13<b>Thetitleofthefirstbookis</b>:14<x:outselect="$output/books/book[1]/name"/>15<br>16<b>Thepriceofthesecondbook</b>:17<x:outselect="$output/books/book[2]/price"/>1819</body>20</html>

# Presenter Notes


 Source: [ch6.md](ch6.md) 62/78 

# 使用JSP格式化XML


`XSLT`样式表`style.xsl`文件

 1<?xml version="1.0"?> 2<xsl:stylesheetxmlns:xsl= 3"http://www.w3.org/1999/XSL/Transform"version="1.0"> 4 5<xsl:outputmethod="html"indent="yes"/> 6 7<xsl:templatematch="/"> 8<html> 9<body>10<xsl:apply-templates/>11</body>12</html>13</xsl:template>1415<xsl:templatematch="books">16<tableborder="1"width="100%">17<xsl:for-eachselect="book">18<tr>19<td>20<i><xsl:value-ofselect="name"/></i>21</td>22<td>23<xsl:value-ofselect="author"/>24</td>25<td>26<xsl:value-ofselect="price"/>27</td>28</tr>29</xsl:for-each>30</table>31</xsl:template>32</xsl:stylesheet>

# Presenter Notes


 Source: [ch6.md](ch6.md) 63/78 

## main.jsp文件

 1<%@taglibprefix="c"uri="http://java.sun.com/jsp/jstl/core"%> 2<%@taglibprefix="x"uri="http://java.sun.com/jsp/jstl/xml"%> 3 4<html> 5<head> 6<title>JSTLx:transformTags</title> 7</head> 8<body> 9<h3>BooksInfo:</h3>10<c:setvar="xmltext">11<books>12<book>13<name>PadamHistory</name>14<author>ZARA</author>15<price>100</price>16</book>17<book>18<name>GreatMistry</name>19<author>NUHA</author>20<price>2000</price>21</book>22</books>23</c:set>2425<c:importurl="http://localhost:8080/style.xsl"var="xslt"/>26<x:transformxml="${xmltext}"xslt="${xslt}"/>2728</body>29</html>

# Presenter Notes


 Source: [ch6.md](ch6.md) 64/78 

# JSP 自定义标签


# Presenter Notes


 Source: [ch6.md](ch6.md) 65/78 

# JSP 自定义标签

- 
自定义标签是用户定义的JSP语言元素。

- 
当JSP页面包含一个自定义标签时将被转化为**servlet**，标签转化为对被称为 **tag handler** 的对象的操作，即当 **servlet** 执行时 **Web container** 调用那些操作。


- 
`JSP`标签扩展可以让你创建新的标签并且可以直接插入到一个`JSP`页面。 


- 
`JSP 2.0`规范中引入`Simple Tag Handlers`来编写这些自定义标记。




- 
你可以继承`SimpleTagSupport`类并重写的 **doTag()** 方法来开发一个最简单的自定义标签。




# Presenter Notes


 Source: [ch6.md](ch6.md) 66/78 

# 创建"Hello"标签

- 创建一个自定义标签叫作`<ex:Hello>`


标签格式为：

1<ex:Hello/>

要创建自定义的`JSP`标签，你首先必须创建处理标签的`Java`类。 所以，让我们创建一个 **HelloTag** 类，如下所示：

 1importjavax.servlet.jsp.tagext.*; 2importjavax.servlet.jsp.*; 3importjava.io.*; 4 5publicclassHelloTagextendsSimpleTagSupport{ 6 7publicvoiddoTag()throwsJspException,IOException{ 8JspWriterout=getJspContext().getOut(); 9out.println("Hello Custom Tag!");10}11}

重写了 **doTag()** 方法，方法中使用了 **getJspContext()** 方法来获取当前的 **JspContext** 对象，并将"Hello Custom Tag!"传递给 **JspWriter** 对象。


# Presenter Notes


 Source: [ch6.md](ch6.md) 67/78 

# 创建"Hello"标签

### 创建标签库


`<Tomcat安装目录>webapps\ROOT\WEB-INF\custom.tld`

 1<taglib> 2<tlib-version>1.0</tlib-version> 3<jsp-version>2.0</jsp-version> 4<short-name>ExampleTLD</short-name> 5<tag> 6<name>Hello</name> 7<tag-class>com.tutorialspoint.HelloTag</tag-class> 8<body-content>empty</body-content> 9</tag>10</taglib>

# Presenter Notes


 Source: [ch6.md](ch6.md) 68/78 

# 创建"Hello"标签

### 在JSP文件中使用Hello标签：

1<%@taglibprefix="ex"uri="WEB-INF/custom.tld"%>2<html>3<head>4<title>Asamplecustomtag</title>5</head>6<body>7<ex:Hello/>8</body>9</html>

以上程序输出结果为：


Hello Custom Tag!


# Presenter Notes


 Source: [ch6.md](ch6.md) 69/78 

# 访问标签体

- 可以像标准标签库一样在标签中包含消息内容。


要在自定义的`Hello`中包含内容：

1<ex:Hello>2Thisismessagebody 3</ex:Hello>

修改标签处理类文件：

 1packagecom.tutorialspoint; 2 3importjavax.servlet.jsp.tagext.*; 4importjavax.servlet.jsp.*; 5importjava.io.*; 6 7publicclassHelloTagextendsSimpleTagSupport{ 8 9StringWritersw=newStringWriter();10publicvoiddoTag()11throwsJspException,IOException12{13getJspBody().invoke(sw);14getJspContext().getOut().println(sw.toString());15}16}

# Presenter Notes


 Source: [ch6.md](ch6.md) 70/78 

# 访问标签体

### 修改TLD文件：

 1<taglib> 2<tlib-version>1.0</tlib-version> 3<jsp-version>2.0</jsp-version> 4<short-name>ExampleTLDwithBody</short-name> 5<tag> 6<name>Hello</name> 7<tag-class>com.tutorialspoint.HelloTag</tag-class> 8<body-content>scriptless</body-content> 9</tag>10</taglib>

# Presenter Notes


 Source: [ch6.md](ch6.md) 71/78 

# 访问标签体

### 在JSP使用修改后的标签:

 1<%@taglibprefix="ex"uri="WEB-INF/custom.tld"%> 2<html> 3<head> 4<title>Asamplecustomtag</title> 5</head> 6<body> 7<ex:Hello> 8Thisismessagebody  9</ex:Hello>10</body>11</html>

以上程序输出结果如下所示：


This is message body


# Presenter Notes


 Source: [ch6.md](ch6.md) 72/78 

# 自定义标签属性


你可以在自定义标准中设置各种属性，要接收属性，值自定义标签类必须实现 **setter** 方法， `JavaBean` 中的 **setter** 方法如下所示：

 1packagecom.tutorialspoint; 2 3importjavax.servlet.jsp.tagext.*; 4importjavax.servlet.jsp.*; 5importjava.io.*; 6 7publicclassHelloTagextendsSimpleTagSupport{ 8privateStringmessage; 910publicvoidsetMessage(Stringmsg){11this.message=msg;12}1314StringWritersw=newStringWriter();1516publicvoiddoTag()17throwsJspException,IOException18{19if(message!=null){20/* 从属性中使用消息 */21JspWriterout=getJspContext().getOut();22out.println(message);23}24else{25/* 从内容体中使用消息 */26getJspBody().invoke(sw);27getJspContext().getOut().println(sw.toString());28}29}30}

# Presenter Notes


 Source: [ch6.md](ch6.md) 73/78 

# 自定义标签属性


属性的名称是"message"，所以`setter`方法是的`setMessage()`。 现在让我们在`TLD`文件中使用的`<attribute>`元素添加此属性：

 1<taglib> 2<tlib-version>1.0</tlib-version> 3<jsp-version>2.0</jsp-version> 4<short-name>ExampleTLDwithBody</short-name> 5<tag> 6<name>Hello</name> 7<tag-class>com.tutorialspoint.HelloTag</tag-class> 8<body-content>scriptless</body-content> 9<attribute>10<name>message</name>11</attribute>12</tag>13</taglib>

# Presenter Notes


 Source: [ch6.md](ch6.md) 74/78 

# 自定义标签属性


可以在JSP文件中使用`message`属性：

1<%@taglibprefix="ex"uri="WEB-INF/custom.tld"%>2<html>3<head>4<title>Asamplecustomtag</title>5</head>6<body>7<ex:Hellomessage="This is custom tag"/>8</body>9</html>

以上实例数据输出结果为：


This is custom tag


# Presenter Notes


 Source: [ch6.md](ch6.md) 75/78 

# 自定义标签属性


可以包含以下属性

**属性****描述**`name`定义属性的名称。每个标签的是属性名称必须是唯一的。`required`指定属性是否是必须的或者可选的, 如果设置为`false`为可选。`rtexprvalue`声明在运行表达式时，标签属性是否有效。`type`定义该属性的`Java`类类型, 默认指定为 `String``description`描述信息`fragment`如果声明了该属性, 属性值将被视为一个 `JspFragment`。
# Presenter Notes


 Source: [ch6.md](ch6.md) 76/78 

# 指定相关的属性


实例：

1..... 2<attribute>3<name>attribute_name</name>4<required>false</required>5<type>java.util.Date</type>6<fragment>false</fragment>7</attribute>8..... 

如果使用了两个属性，修改`TLD`文件，如下所示：

 1.....  2<attribute> 3<name>attribute_name1</name> 4<required>false</required> 5<type>java.util.Boolean</type> 6<fragment>false</fragment> 7</attribute> 8<attribute> 9<name>attribute_name2</name>10<required>true</required>11<type>java.util.Date</type>12</attribute>13..... 

# Presenter Notes


 Source: [ch6.md](ch6.md) 77/78 

# 课程QQ群





# Presenter Notes


 Source: [ch6.md](ch6.md) 78/78 



## Table of Contents

Table of Contents[JavaBean](#slide:1)[1](#slide:1)[JavaBean](#slide:2)[2](#slide:2)[JavaBeans属性](#slide:3)[3](#slide:3)[JavaBeans属性](#slide:4)[4](#slide:4)[JavaBean程序示例](#slide:5)[5](#slide:5)[访问JavaBeans](#slide:6)[6](#slide:6)[JavaBean程序示例](#slide:7)[7](#slide:7)[访问JavaBeans对象的属性](#slide:8)[8](#slide:8)[JavaBean的属性访问的程序示例](#slide:9)[9](#slide:9)[Expression Language（EL）](#slide:10)[10](#slide:10)[Expression Language（EL）](#slide:11)[11](#slide:11)[Expression Language（EL）](#slide:12)[12](#slide:12)[JSP 表达式语言](#slide:13)[13](#slide:13)[通用操作符](#slide:14)[14](#slide:14)[通用操作符](#slide:15)[15](#slide:15)[基础操作符](#slide:16)[16](#slide:16)[JSP EL中的函数](#slide:17)[17](#slide:17)[JSP EL中的函数](#slide:18)[18](#slide:18)[JSP EL隐含对象](#slide:19)[19](#slide:19)[JSP EL隐含对象](#slide:20)[20](#slide:20)[JSP EL隐含对象](#slide:21)[21](#slide:21)[JSP EL隐含对象](#slide:22)[22](#slide:22)[header和headerValues对象](#slide:23)[23](#slide:23)[header和headerValues对象](#slide:24)[24](#slide:24)[JavaServer Pages Standard Tag Library](#slide:25)[25](#slide:25)[JSP 标准标签库（JSTL）](#slide:26)[26](#slide:26)[JSTL 库安装](#slide:27)[27](#slide:27)[核心标签](#slide:28)[28](#slide:28)[表达式控制标签](#slide:29)[29](#slide:29)[表达式控制标签](#slide:30)[30](#slide:30)[表达式控制标签](#slide:31)[31](#slide:31)[流程控制标签](#slide:32)[32](#slide:32)[流程控制标签](#slide:33)[33](#slide:33)[流程控制标签](#slide:34)[34](#slide:34)[流程控制标签](#slide:35)[35](#slide:35)[循环标签](#slide:36)[36](#slide:36)[循环标签](#slide:37)[37](#slide:37)[<c:forEach>标签](#slide:38)[38](#slide:38)[<c:forEach>标签](#slide:39)[39](#slide:39)[循环标签](#slide:40)[40](#slide:40)[URL操作标签](#slide:41)[41](#slide:41)[URL操作标签](#slide:42)[42](#slide:42)[URL操作标签](#slide:43)[43](#slide:43)[URL操作标签](#slide:44)[44](#slide:44)[URL操作标签](#slide:45)[45](#slide:45)[URL操作标签](#slide:46)[46](#slide:46)[格式化标签](#slide:47)[47](#slide:47)[格式化标签](#slide:48)[48](#slide:48)[数字日期格式化标签](#slide:49)[49](#slide:49)[<frm:parseNumber>标签](#slide:50)[50](#slide:50)[<fmt:formatDate>标签](#slide:51)[51](#slide:51)[<fmt:parseDate>标签](#slide:52)[52](#slide:52)[SQL标签](#slide:53)[53](#slide:53)[SQL标签](#slide:54)[54](#slide:54)[XML 标签](#slide:55)[55](#slide:55)[XML 标签](#slide:56)[56](#slide:56)[JSTL函数](#slide:57)[57](#slide:57)[JSP XML 数据处理](#slide:58)[58](#slide:58)[JSP XML 数据处理](#slide:59)[59](#slide:59)[在JSP中处理XML](#slide:60)[60](#slide:60)[使用JSP发送XML](#slide:61)[61](#slide:61)[main.jsp文件](#slide:62)[62](#slide:62)[使用JSP格式化XML](#slide:63)[63](#slide:63)[main.jsp文件](#slide:64)[64](#slide:64)[JSP 自定义标签](#slide:65)[65](#slide:65)[JSP 自定义标签](#slide:66)[66](#slide:66)[创建"Hello"标签](#slide:67)[67](#slide:67)[创建"Hello"标签](#slide:68)[68](#slide:68)[创建"Hello"标签](#slide:69)[69](#slide:69)[访问标签体](#slide:70)[70](#slide:70)[访问标签体](#slide:71)[71](#slide:71)[访问标签体](#slide:72)[72](#slide:72)[自定义标签属性](#slide:73)[73](#slide:73)[自定义标签属性](#slide:74)[74](#slide:74)[自定义标签属性](#slide:75)[75](#slide:75)[自定义标签属性](#slide:76)[76](#slide:76)[指定相关的属性](#slide:77)[77](#slide:77)[课程QQ群](#slide:78)[78](#slide:78)
## Help

HelpTable of ContentstExposéESCPresenter ViewpSource FilessSlide NumbersnToggle screen blankingbShow/hide next slidecNotes2Helph
**Generated with Darkslide 6.0.0**
Embedded image 'fig/QQGroup.jpg'