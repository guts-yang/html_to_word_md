# JSP基础


# Presenter Notes


 Source: [ch5.md](ch5.md) 1/86 

# Java Server Pages

- 
**JSP** 全称 **Java Server Pages**，是一种动态网页开发技术

- 使用 **JSP** 标签在 **HTML** 网页中插入 **Java** 代码


- 
**JSP** 是一种 **Java servlet**，主要用于实现 **Java web** 应用程序的用户界面部分

- 通过结合 **HTML** 代码、 **XHTML** 代码、 **XML** 元素以及嵌入 **JSP** 操作和命令来编写 
- 通过网页表单获取用户输入数据、访问数据库及其他数据源，然后动态地创建网页




**JSP**标签通常以`<%`开头, 以`%\>`结束

1<%codefragment%>

# Presenter Notes


 Source: [ch5.md](ch5.md) 2/86 

# JSP 标签

- 标签有多种功能，比如：- 访问数据库
- 记录用户选择信息
- 可以在不同的网页中传递控制信息和共享信息
- 访问 **JavaBeans** 组件等




JSP的简单例子：

1<html>2<head><title>HelloWorld!</title></head>3<body>4HelloWorld!<br>5<%out.println("Your IP address is "+request.getRemoteAddr());%>6</body>7</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 3/86 

# 为什么使用JSP？

- 
**JSP** 程序与 **CGI** 程序有着相似的功能，但和 **CGI** 程序相比，**JSP** 程序有如下优势：

- 性能更加优越，因为**JSP** 可以直接在 **HTML** 网页中动态嵌入元素而不需要单独引用 **CGI** 文件
- 服务器调用的是已经编译好的 **JSP** 文件，而不像 **CGI/Perl** 那样必须先载入解释器和目标脚本
- **JSP** 基于 **Java Servlet API**，因此，**JSP** 拥有各种强大的企业级 **Java API**，包括 **JDBC**，**JNDI**，**EJB**，**JAXP**等等
- **JSP**页面可以与处理业务逻辑的 **Servlet** 一起使用，这种模式被 **Java servlet** 模板引擎所支持




# Presenter Notes


 Source: [ch5.md](ch5.md) 4/86 

# JSP的优势

- 与 **ASP** 相比： **JSP** 有两大优势- 动态部分用 **Java**编写，而不是**VB** 或其他 **MS** 专用语言，所以更加强大与易用
- **JSP** 易于移植到非 **MS** 平台上


- 与纯 **Servlet** 相比：- **JSP** 可以很方便的编写或者修改 **HTML** 网页而不用去面对大量的 **println** 语句。


- 与 **SSI** 相比：- **SSI** 无法使用表单数据、无法进行数据库链接。




# Presenter Notes


 Source: [ch5.md](ch5.md) 5/86 

# JSP的优势

- 与 **JavaScript** 相比：- 虽然 **JavaScript** 可以在客户端动态生成 **HTML**，但是很难与服务器交互，因此不能提供复杂的服务，比如访问数据库和图像处理等等。


- 
与静态 **HTML** 相比：

- 静态 **HTML** 不包含动态信息。


- 
JSP是Java EE不可或缺的一部分，是一个完整的企业级应用平台。

- **JSP** 可以用最简单的方式来实现最复杂的应用。




# Presenter Notes


 Source: [ch5.md](ch5.md) 6/86 

# JSP 结构

- 
网络服务器需要一个 **JSP** 引擎，也就是一个容器来处理 **JSP** 页面。

- 容器负责截获对 **JSP** 页面的请求。
- **JSP** 容器与 **Web** 服务器协同合作，为 **JSP** 的正常运行提供必要的运行环境和其他服务，并且能够正确识别专属于 **JSP** 网页的特殊元素。







# Presenter Notes


 Source: [ch5.md](ch5.md) 7/86 

# Web 服务器使用JSP


创建网页的步骤 $1$




- 就像其他普通的网页一样，您的浏览器发送一个 **HTTP** 请求给服务器。
- `Web` 服务器识别出这是一个对 `JSP` 网页的请求，并且将该请求传递给 **JSP** 引擎。- 通过使用 **URL** 或者 `.jsp` 文件来完成。




# Presenter Notes


 Source: [ch5.md](ch5.md) 8/86 

# Web 服务器使用JSP


创建网页的步骤 $2$




- **JSP** 引擎从磁盘中载入 **JSP** 文件，然后将它们转化为 **Servlet**。- 这种转化只是简单地将所有模板文本改用 **println()** 语句，并且将所有的 **JSP** 元素转化成 **Java** 代码


- **JSP** 引擎将 **Servlet** 编译成可执行类，并且将原始请求传递给 **Servlet** 引擎。


# Presenter Notes


 Source: [ch5.md](ch5.md) 9/86 

# Web 服务器使用JSP


创建网页的步骤 $3$




- **Web** 服务器的某组件将会调用 **Servlet** 引擎，然后载入并执行 **Servlet** 类。- 在执行过程中，**Servlet** 产生 **HTML** 格式的输出并将其内嵌于 **HTTP response** 中上交给 **Web** 服务器。




# Presenter Notes


 Source: [ch5.md](ch5.md) 10/86 

# Web 服务器使用JSP


创建网页的步骤 $4$




- **Web** 服务器以静态 **HTML** 网页的形式将 **HTTP response** 返回到您的浏览器中。
- 最终，**Web** 浏览器处理 **HTTP response** 中动态产生的`HTML`网页，就好像在处理静态网页一样。


# Presenter Notes


 Source: [ch5.md](ch5.md) 11/86 

# JSP生命周期

- 
编译阶段：

- 
`servlet`容器编译`servlet`源文件，生成`servlet`类


- 
当浏览器请求`JSP`页面时，`JSP`引擎会首先去检查是否需要编译这个文件。

- 如果这个文件没有被编译过，或者在上次编译后被更改过，则编译这个`JSP`文件。


- 
编译的过程包括三个步骤：

- 解析`JSP`文件
- 将`JSP`文件转为`servlet`
- 编译`servlet`






# Presenter Notes


 Source: [ch5.md](ch5.md) 12/86 

# JSP生命周期

- 
初始化阶段：

- 容器载入`JSP`文件后，它会在为请求提供任何服务前调用 **jspInit()** 方法- 如果您需要执行自定义的JSP初始化任务，复写 **jspInit()** 方法就行了
- 通常情况下您可以在`jspInit()`方法中初始化数据库连接、打开文件和创建查询表。









# Presenter Notes


 Source: [ch5.md](ch5.md) 13/86 

# JSP生命周期

- 执行阶段：- 调用与`JSP`对应的`servlet`实例的服务方法
- 这一阶段描述了`JSP`生命周期中一切与请求相关的交互行为，直到被销毁。
- 当`JSP`网页完成初始化后，`JSP`引擎将会调用`_jspService()`方法。







# Presenter Notes


 Source: [ch5.md](ch5.md) 14/86 

# JSP生命周期

- 执行阶段：


`_jspService()`方法需要一个`HttpServletRequest`对象和一个`HttpServletResponse`对象作为它的参数，就像下面这样：

1void_jspService(HttpServletRequestrequest,2HttpServletResponseresponse)3{4// 服务端处理代码5}

`_jspService()`方法在每个`request`中被调用一次并且负责产生与之相对应的`response`


并且它还负责产生所有$7$个`HTTP`方法的回应，比如`GET`、`POST`、`DELETE`等等。


# Presenter Notes


 Source: [ch5.md](ch5.md) 15/86 

# JSP生命周期

- 
销毁阶段：

- `jspDestroy()`方法在`JSP`中等价于`servlet`中的销毁方法。
- `JSP`生命周期的销毁阶段描述了当一个`JSP`网页从容器中被移除时所发生的一切。
- 当需要执行任何清理工作时复写`jspDestroy()`方法，比如释放数据库连接或者关闭文件夹等等。







# Presenter Notes


 Source: [ch5.md](ch5.md) 16/86 

# JSP生命周期实例

 1<%@pagelanguage="java"contentType="text/html; charset=UTF-8" 2pageEncoding="UTF-8"%> 3<html> 4<head> 5<title>life.jsp</title> 6</head> 7<body> 8<%! 9privateintinitVar=0;10privateintserviceVar=0;11privateintdestroyVar=0;12%>13<%!14publicvoidjspInit(){15initVar++;16System.out.println("jspInit(): JSP被初始化了"+initVar+"次");17}18publicvoidjspDestroy(){19destroyVar++;20System.out.println("jspDestroy(): JSP被销毁了"+destroyVar+"次");21}22%>23<%24serviceVar++;25System.out.println("_jspService(): JSP共响应了"+serviceVar+"次请求");2627Stringcontent1="初始化次数 : "+initVar;28Stringcontent2="响应客户请求次数 : "+serviceVar;29Stringcontent3="销毁次数 : "+destroyVar;30%>31<h1>JSP生命周期测试实例</h1>32<p><%=content1%></p>33<p><%=content2%></p>34<p><%=content3%></p>35</body>36</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 17/86 

# JSP基本语法


# Presenter Notes


 Source: [ch5.md](ch5.md) 18/86 

# JSP页面

- 
JSP页面内容可以分成 **元素** 和 **模板数据** 两部分。

- 
`JSP`页面可以嵌入 **HTML标签**、 **指令**、 **动作**、 **脚本**、 **扩展标签** 等内容。


- 
**元素**是在`JSP`基本语法中定义的内容，

- `JSP`容器在转换阶段将元素翻译成相应的`Java`代码。
- `JSP`定义的元素有四种类型：**指令**、**脚本**、**动作**和**表达式语言**。


- 
`JSP`页面中其他的所有内容都是模板数据。

- `JSP`容器对模板数据不做处理，例如`HTML`内容，会直接送到客户端执行。






# Presenter Notes


 Source: [ch5.md](ch5.md) 19/86 

# 脚本元素

- 
脚本元素是`JSP`页面中嵌入的`Java`代码。


- 
脚本元素包括: 

- **声明**(Declarations)、 
- **表达式**(Expressions) 
- **脚本小程序** (Scriptlets)三种类型，




语法格式为：

1<％codefragment％> 

也可以使用`XML`编写

1<jsp:declaration/expressio/scriptle>2codefragment 3</jsp:declaration/expressio/scriptle> 

# Presenter Notes


 Source: [ch5.md](ch5.md) 20/86 

# 声明

- 声明的作用是在`JSP`程序中声明变量、方法和类。


声明语法：

1<%!declaration;[declaration;]...%>

可以使用`XML`编写

1<jsp:declaration>2codefragment 3</jsp:declaration>
- 可以一次声明多个变量、方法和类，这些声明必须符合`Java`语法。
- 一般情况下，声明的对象只在当前页面中起作用。


# Presenter Notes


 Source: [ch5.md](ch5.md) 21/86 

# 声明变量

- 变量类型可以是`Java`语言允许的任何数据类型。将这些变量称为 **JSP页面的成员变量**。
- 这些变量在整个`JSP`页面内有效。
- 当用多个用户请求一个`JSP`页面时，`JSP`容器为每个客户起动一个线程，这些线程共享`JSP`页面的成员变量，- 因此任何一个用户对`JSP`页面成员变量的操作的结果，都会影响到其他用户。




.

1<%!inti=0;%>2<%!inta,b,c;%>3<%!Circlea=newCircle(2.0);%>

# Presenter Notes


 Source: [ch5.md](ch5.md) 22/86 

# 声明方法

- 与成员变量一样，在`<%!`和`%>`之间声明的方法作为`Servlet`类的方法，在整个`JSP`页面内有效。
- 需要注意的是，在方法中定义的变量，只在该方法内有效。


**实例** (`index.jsp`)

1<%!2privateStringcheckAnswer(intp,booleanmTrueAnswer){3if(mQuestions[p].isTrueQuestion()==mTrueAnswer)4return"回答正确";5else6return"回答错误";7}8%>

# Presenter Notes


 Source: [ch5.md](ch5.md) 23/86 

# 声明类

- 除了声明变量和方法外，还可以在`<%!`和`%>`之间声明类。


由于该类在`Servlet`类的内部，以 **内部类** 形式存在，所以该类只在本`JSP`页面内有效，即在`JSP`页面的`Java`程序部分可以使用该类创建的对象。

 1<%! 2publicclassGeoQuestion{ 3privateStringmQuestion; 4privatebooleanmTrueQuestion; 5publicGeoQuestion(Stringquestion,booleantrueQuestion){ 6mQuestion=question; 7mTrueQuestion=trueQuestion; 8} 9}10%>

# Presenter Notes


 Source: [ch5.md](ch5.md) 24/86 

# 声明类变量、数组

 1<%! 2inti=0; 3privateGeoQuestion[]mQuestions=newGeoQuestion[]{ 4newGeoQuestion("北京是中华人民共和国首都，简称京。",true), 5newGeoQuestion("中国钢产量最多的省是湖南。",false), 6newGeoQuestion("新疆维吾尔族自治区是中国面积最大的省级行政区。",true) 7}; 8 9publicclassGeoQuestion{10privateStringmQuestion;11privatebooleanmTrueQuestion;12⁞13publicStringgetQuestion(){14returnmQuestion;15}16publicbooleanisTrueQuestion(){17returnmTrueQuestion;18}19}20%>

# Presenter Notes


 Source: [ch5.md](ch5.md) 25/86 

# 表达式

- 表达式是由常量、变量和运算符组成的式子，在请求处理的时候表达式被计算并插入到输出流返回给客户端。


表达式语法：

1<%=expression%>

`<%=`三个字符之间不能有空格，表达式之后不能有`；`。


**实例**

 1<%@pagelanguage="java"import="java.util.*"pageEncoding="UTF-8"%> 2<html> 3<head> 4<title>JSP表达式实例</title> 5</head> 6<body> 7<p> 8今天是:<%=(newjava.util.Date()).toLocaleString()%> 9</p>10</body>11</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 26/86 

# JSP注释

- `JSP`注释标记的文字或语句都会被`JSP`容器忽略。- 当想要隐藏或“注释掉”`JSP`页面的一部分，`JSP`注释是很有用的。




JSP注释语法：

1<%--ThisisJSPcomment--%>

创建一个`comment.jsp` 并编写入下面的代码，以测试`JSP`注释：

1<%@pagelanguage="java"import="java.util.*"pageEncoding="UTF-8"%>2<html>3<head><title>注释-示例</title></head>4<body>5<h2>ATestofComments</h2>6<%--Thiscommentwillnotbevisibleinthepagesource--%>7</body>8</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 27/86 

# 特殊的结构

- 常用的注释的语法规则：

**语法****目的**`<%-- comment --%>``JSP`注释，它将被`JSP`引擎忽略`<!-- comment -->``HTML`注释，它将被浏览器忽略`<%`表示静态`<%`的字面量`%>`表示静态`%>`的字面量`'`在使用单引号在属性中的单引号`"`双引号在属性使用双引号
# Presenter Notes


 Source: [ch5.md](ch5.md) 28/86 

# JSP指令

- `JSP`指令是在`JSP`程序中嵌入一段`Java`代码。


其基本语法：

1<%Java代码%>
- 
`JSP`指令插入在`<%`和`%>`之间，可以包含多个`Java`语句。

- 一个`JSP`页面可以包含多个`JSP`指令，这些`JSP`指令由`JSP`容器顺序执行。
- 脚本小程序`Java`代码会按顺序插入`Servlet`类的 `_jspservice()` 方法中。
- 声明中的变量也称作局部变量或方法变量，它们在`JSP`页面人的所有脚本小程序内都有效。




# Presenter Notes


 Source: [ch5.md](ch5.md) 29/86 

# 指令元素

- 
`JSP`指令用于设置整个页面属性，并告诉`JSP`引擎如何处理该页面，它并不向客户端产生任何输出。

- 通过`JSP`指令可以设置页面的引入类、内容类型和编码、错误处理和会话信息等。




指令元素的语法格式：

1<%@指令名属性名1="属性值"属性名2="属性值"…%>

其中

- `<%@`三个符号之间没有空格


# Presenter Notes


 Source: [ch5.md](ch5.md) 30/86 

# 指令元素

- `JSP`有$3$种主要的指令：**page**、 **include** 和 **taglib**

**指令****描述**`<%@ page ... %>`定义页面的依赖属性，比如脚本语言、`error`页面、缓存需求等等`<%@ include ... %>`包含其他文件`<%@ taglib ... %>`引入标签库的定义，可以是自定义标签
# Presenter Notes


 Source: [ch5.md](ch5.md) 31/86 

# Page指令


`Page`指令为容器提供当前页面的使用说明。 一个`JSP`页面可以包含多个`page`指令。 可以在`JSP`页面的任何位置编写`page`指令。按照惯例，`page`指令被编码在`JSP`页面的顶部。


`Page`指令的语法格式：

1<%@pageattribute="value"%>

等价的`XML`格式：

1<jsp:directive.pageattribute="value"/>

# Presenter Notes


 Source: [ch5.md](ch5.md) 32/86 

# Page指令的属性

**属性****描述**`buffer`指定`out`对象使用缓冲区的大小`autoFlush`控制`out`对象的 缓存区`contentType`指定当前`JSP`页面的`MIME`类型和字符编码`errorPage`指定当`JSP`页面发生异常时需要转向的错误处理页面`isErrorPage`指定当前页面是否可以作为另一个`JSP`页面的错误处理页面`extends`指定`servlet`从哪一个类继承`import`导入要使用的`Java`类`info`定义`JSP`页面的描述信息`isThreadSafe`指定对`JSP`页面的访问是否为线程安全`language`定义`JSP`页面所用的脚本语言，默认是`Java``session`指定`JSP`页面是否使用`session``isELIgnored`指定是否执行`EL`表达式`isScriptingEnabled`确定脚本元素能否被使用
# Presenter Notes


 Source: [ch5.md](ch5.md) 33/86 

# Include指令

- `JSP`可以通过`include`指令来包含其他文件。- 被包含的文件可以是`JSP`文件、`HTML`文件或文本文件。
- 包含的文件就好像是该`JSP`文件的一部分，会被同时编译执行。




`Include`指令的语法格式：

1<%@includefile="relative url"%>
- `Include`指令中的文件名实际上是一个相对的`URL`。- 如果您没有给文件关联一个路径，`JSP`编译器默认在当前路径下寻找。




# Presenter Notes


 Source: [ch5.md](ch5.md) 34/86 

# Include指令

- `Include`指令中的文件名实际上是一个相对的`URL`。- 如果您没有给文件关联一个路径，`JSP`编译器默认在当前路径下寻找。




等价的`XML`语法：

1<jsp:directive.includefile="relative url"/>
- `include`用于在编译阶段包括一个文件。- 这个指令告诉容器在编译阶段将其他外部文件的内容合并到当前`JSP`文件中。
- 可在`JSP`页面的任何位置使用`include`指令进行编码。




# Presenter Notes


 Source: [ch5.md](ch5.md) 35/86 

# Taglib指令

- 
`JSP API`允许用户自定义标签，一个自定义标签库就是自定义标签的集合。


- 
`Taglib`指令引入一个自定义标签集合的定义，包括库路径、自定义标签。




`Taglib`指令的语法：

1<%@tagliburi="uri"prefix="prefixOfTag"%>

**uri** 属性确定标签库的位置，**prefix** 属性指定标签库的前缀。


等价的`XML`语法：

1<jsp:directive.tagliburi="uri"prefix="prefixOfTag"/>

# Presenter Notes


 Source: [ch5.md](ch5.md) 36/86 

# JSP行为

- 
`JSP`行为标签使用`XML`语法结构来控制`servlet`引擎。

- 它能够动态插入一个文件，重用 **JavaBean** 组件，引导用户去另一个页面，为`Java`插件产生相关的`HTML`等等。


- 
与`JSP`指令元素不同的是，`JSP`动作元素在请求处理阶段起作用。

- `JSP`动作元素是用`XML`语法写成的。
- 利用`JSP`动作可以动态地插入文件、重用`JavaBean`组件、把用户重定向到另外的页面、为`Java`插件生成`HTML`代码。




行为标签只有一种语法格式，它严格遵守`XML`标准：

1<jsp:action_nameattribute="value"/>

# Presenter Notes


 Source: [ch5.md](ch5.md) 37/86 

# JSP 动作元素

- 行为标签基本上是一些预先就定义好的函数，下表罗列出了一些可用的`JSP`行为标签：：

**语法****描述**`jsp:include`用于在当前页面中包含静态或动态资源`jsp:useBean`寻找和初始化一个JavaBean组件`jsp:setProperty`设置 `JavaBean`组件的值`jsp:getProperty`将 `JavaBean`组件的值插入到 `output`中`jsp:forward`从一个`JSP`文件向另一个文件传递一个包含用户请求的`request`对象`jsp:plugin`用于在生成的`HTML`页面中包含`Applet`和`JavaBean`对象`jsp:element`动态创建一个`XML`元素`jsp:attribute`定义动态创建的`XML`元素的属性`jsp:body`定义动态创建的`XML`元素的主体`jsp:text`用于封装模板数据
# Presenter Notes


 Source: [ch5.md](ch5.md) 38/86 

# 常见的属性

- 
所有的动作要素都有两个属性：**id属性** 和 **scope属性**。


- 
`id`属性：

- `id`属性是动作元素的唯一标识，可以在`JSP`页面中引用。
- 动作元素创建的`id`值可以通过`PageContext`来调用。


- 
`scope`属性：

- 该属性用于识别动作元素的生命周期。 
- 
`id`属性和`scope`属性有直接关系，`scope`属性定义了相关联`id`对象的寿命。 


- 
`scope`属性有四个可能的值： 

- (a) `page` (b) `request` (c) `session` (d) `application`






# Presenter Notes


 Source: [ch5.md](ch5.md) 39/86 

# `<jsp:include\>`动作元素

- `<jsp:include\>`动作元素用来包含静态和动态的文件。 该动作把指定文件插入正在生成的页面。


语法格式如下：

1<jsp:include page="relative URL" flush="true" />
- `include`动作相关的属性列表

**属性****描述**`page`包含在页面中的相对`URL`地址。`flush`布尔属性，定义在包含资源前是否刷新缓存区。
# Presenter Notes


 Source: [ch5.md](ch5.md) 40/86 

# include动作实例


实例定义了两个文件`date.jsp`和`main.jsp`，代码如下所示：

## `date.jsp`文件代码：

1<p>2Today'sdate:<%=(newjava.util.Date()).toLocaleString()%>3</p>
## `main.jsp`文件代码：

 1<html> 2<head> 3<title>TheincludeActionExample</title> 4</head> 5<body> 6<center> 7<h2>TheincludeactionExample</h2> 8<jsp:include page="date.jsp" flush="true" /> 9</center>10</body>11</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 41/86 

# `<jsp:useBean\>`动作元素

- `jsp:useBean`动作用来装载一个将在JSP页面中使用的 **JavaBean** 。 这个功能非常有用，因为它使得我们既可以发挥`Java`组件重用的优势，同时也避免了损失`JSP`区别于`Servlet`的方便性。


`jsp:useBean`动作最简单的语法为：

1<jsp:useBean id="name" class="package.class" />

在类载入后，我们既可以通过 **jsp:setProperty** 和 **jsp:getProperty** 动作来修改和检索`bean`的属性。

- `useBean`动作相关的属性列表

**属性****描述**`class`指定`Bean`的完整包名。`type`指定将引用该对象变量的类型。`beanName`通过 `java.beans.Beans` 的 `instantiate()` 方法指定`Bean`的名字。
# Presenter Notes


 Source: [ch5.md](ch5.md) 42/86 

# `<jsp:setProperty\>`动作元素


`jsp:setProperty`用来设置已经实例化的`Bean`对象的属性，有两种用法。


**第一种用法:**


在`jsp:useBean`元素的外面（后面）使用`jsp:setProperty`

1<jsp:useBean id="myName" ... />2... 3<jsp:setProperty name="myName" property="someProperty" .../>

此时，不管`jsp:useBean`是找到了一个现有的`Bean`，还是新创建了一个`Bean`实例，`jsp:setProperty`都会执行。


# Presenter Notes


 Source: [ch5.md](ch5.md) 43/86 

# `<jsp:setProperty\>`动作元素


`jsp:setProperty`用来设置已经实例化的`Bean`对象的属性，有两种用法。


**第二种用法：**


把`jsp:setProperty`放入`jsp:useBean`元素的内部

1<jsp:useBean id="myName" ... >2... 3<jsp:setProperty name="myName" property="someProperty" .../>4</jsp:useBean>

此时，`jsp:setProperty`只有在新建`Bean`实例时才会执行，如果是使用现有实例则不执行`jsp:setProperty`。


# Presenter Notes


 Source: [ch5.md](ch5.md) 44/86 

# `<jsp:setProperty\>`动作元素四个属性


`jsp:setproperty`动作有下面四个属性,如下表：

**属性****描述**`name``name`属性是必需的。它表示要设置属性的是哪个`Bean`。`property``property`属性是必需的。它表示要设置哪个属性。有一个特殊用法：如果`property`的值是"*"，表示所有名字和`Bean`属性名字匹配的请求参数都将被传递给相应的属性`set`方法。`value``value` 属性是可选的。该属性用来指定`Bean`属性的值。字符串数据会在目标类中通过标准的`valueOf`方法自动转换成数字、`boolean`、 `byte`、`char`、`Character`。 `value`和`param`不能同时使用，但可以使用其中任意一个。`param``param` 是可选的。它指定用哪个请求参数作为`Bean`属性的值。如果当前请求没有参数，则什么事情也不做，系统不会把`null`传递给`Bean`属性的`set`方法。因此，可以让`Bean`自己提供默认属性值，只有当请求参数明确指定了新值时才修改默认属性值。
# Presenter Notes


 Source: [ch5.md](ch5.md) 45/86 

# `<jsp:getProperty\>`动作元素


`jsp:getProperty`动作提取指定`Bean`属性的值，转换成字符串，然后输出。语法格式如下：

1<jsp:useBean id="myName" ... />2... 3<jsp:getProperty name="myName" property="someProperty" .../>

与`getProperty`相关联的属性：

**属性****描述**`name`要检索的`Bean`属性名称。`Bean`必须已定义。`property`表示要提取`Bean`属性的值
# Presenter Notes


 Source: [ch5.md](ch5.md) 46/86 

# 使用Bean实例


`TestBean.java`

 1/* 文件: TestBean.java */ 2packageaction; 3 4publicclassTestBean{ 5privateStringmessage="No message specified"; 6 7publicStringgetMessage(){ 8return(message); 9}1011publicvoidsetMessage(Stringmessage){12this.message=message;13}14}

# Presenter Notes


 Source: [ch5.md](ch5.md) 47/86 

# 使用Bean实例


在`main.jsp`文件中调用该`Bean`:

 1<html> 2<head> 3<title>UsingJavaBeansinJSP</title> 4</head> 5<body> 6<center> 7<h2>UsingJavaBeansinJSP</h2> 8<jsp:useBean id="test" class="action.TestBean" /> 9<jsp:setPropertyname="test"10property="message"11value="Hello JSP..."/>12<p>Gotmessage....</p>13<jsp:getProperty name="test" property="message" />14</center>15</body>16</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 48/86 

# `<jsp:forward\>` 动作元素


`jsp:forward`动作把请求转到另外的页面。`jsp:forward`标记只有一个属性`page`。


语法格式如下所示：

1<jsp:forward page="Relative URL" />

以下是`forward`相关联的属性：

**属性****描述**`page`包含的是一个相对`URL`。`page`的值既可以直接给出，也可以在请求的时候动态计算，可以是一个`JSP`页面或者一个`Java Servlet`。
# Presenter Notes


 Source: [ch5.md](ch5.md) 49/86 

# 实例


以下实例我们使用了两个文件，分别是： `date.jps` 和 `main.jsp`


`date.js`：

1<p>2Today'sdate:<%=(newjava.util.Date()).toLocaleString()%>3</p>

`main.jsp`：

 1<html> 2<head> 3<title>TheforwardActionExample</title> 4</head> 5<body> 6<center> 7<h2>TheforwardactionExample</h2> 8<jsp:forward page="date.jsp" /> 9</center>10</body>11</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 50/86 

# `<jsp:plugin\>`动作元素

- `jsp:plugin`动作用来根据浏览器的类型，插入通过`Java`插件 运行`Java Applet`所必需的`OBJECT`或`EMBED`元素。 如果需要的插件不存在，它会下载插件，然后执行`Java`组件。 
- `Java`组件可以是一个`applet`或一个`JavaBean`。
- `plugin`动作有多个对应HTML元素的属性用于格式化`Java`组件。`param`元素可用于向`Applet` 或 `Bean` 传递参数。


以下是使用`plugin` 动作元素的典型实例:

1<jsp:plugintype="applet"codebase="dirname"code="MyApplet.class"2width="60"height="80">3<jsp:paramname="fontcolor"value="red"/>4<jsp:paramname="background"value="black"/>5<jsp:fallback>6UnabletoinitializeJavaPlugin 7</jsp:fallback>8</jsp:plugin>

# Presenter Notes


 Source: [ch5.md](ch5.md) 51/86 

# 动作元素

- `<jsp:element\>` 、 `<jsp:attribute\>`、 `<jsp:body\>`动作元素 `<jsp:element\>` 、 `<jsp:attribute\>`、 `<jsp:body\>`动作元素动态定义`XML`元素。


以下实例动态定义了`XML`元素：

 1<%@pagelanguage="java"contentType="text/html"%> 2<htmlxmlns="http://www.w3c.org/1999/xhtml" 3xmlns:jsp="http://java.sun.com/JSP/Page"> 4<head><title>GenerateXMLElement</title></head> 5<body> 6<jsp:elementname="xmlElement"> 7<jsp:attributename="xmlElementAttr"> 8Valuefortheattribute  9</jsp:attribute>10<jsp:body>11BodyforXMLelement 12</jsp:body>13</jsp:element>14</body>15</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 52/86 

# 动作元素

- `<jsp:element\>` 、 `<jsp:attribute\>`、 `<jsp:body\>`动作元素


执行时生成`HTML`代码如下：

 1<htmlxmlns="http://www.w3c.org/1999/xhtml" 2xmlns:jsp="http://java.sun.com/JSP/Page"> 3 4<head><title>Generate XML Element</title></head> 5<body> 6<xmlElementxmlElementAttr="Value for the attribute"> 7 Body for XML element  8</xmlElement> 9</body>10</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 53/86 

# `<jsp:text\>`动作元素


`<jsp:text\>`动作元素允许在`JSP`页面和文档中使用写入文本的模板，语法格式如下：

1<jsp:text>Templatedata</jsp:text>

以上文本模板不能包含其他元素，只能只能包含文本和EL表达式（注：`EL`表达式将在后续章节中介绍）。

- 请注意，在`XML`文件中，不能使用表达式如 `${whatever > 0}`，因为`>`符号是非法的。 


可以使用 `${whatever gt 0}`表达式或者嵌入在一个`CDATA`部分的值。

1<jsp:text><![CDATA[<br>]]></jsp:text>

# Presenter Notes


 Source: [ch5.md](ch5.md) 54/86 

# `<jsp:text\>`动作元素


如果需要在 `XHTML` 中声明 `DOCTYPE`,必须使用到`<jsp:text\>`动作元素，实例如下：

 1<jsp:text><![CDATA[<!DOCTYPE html 2 PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 3 "DTD/xhtml1-strict.dtd">]]> 4</jsp:text> 5<head><title>jsp:textaction</title></head> 6<body> 7<books><book><jsp:text> 8WelcometoJSPProgramming  9</jsp:text></book></books>10</body>11</html>

可以对以上实例尝试使用`<jsp:text\>`及不使用该动作元素执行结果的区别。


# Presenter Notes


 Source: [ch5.md](ch5.md) 55/86 

# `JSP`隐含对象

- 
`JSP`隐含对象是JSP容器为每个页面提供的`Java`对象，开发者可以直接使用它们而不用显式声明。 `JSP`隐含对象也被称为预定义变量。


- 
`JSP`支持九个自动定义的变量，称隐含对象：



对象描述`request``HttpServletRequest`类的实例`response``HttpServletResponse`类的实例`out``PrintWriter`类的实例，用于把结果输出至网页上`session``HttpSession`类的实例`application``ServletContext`类的实例，与应用上下文有关`config``ServletConfig`类的实例`pageContext``PageContext`类的实例，提供对`JSP`页面所有对象以及命名空间的访问`page`类似于`Java`类中的`this`关键字`Exception``Exception`类的对象，代表发生错误的`JSP`页面中对应的异常对象
# Presenter Notes


 Source: [ch5.md](ch5.md) 56/86 

# JSP隐含对象

- 
`request`对象

- `request`对象是`javax.servlet.http.HttpServletRequest` 类的实例。
- 每当客户端请求一个`JSP`页面时，`JSP`引擎就会制造一个新的`request`对象来代表这个请求。
- `request`对象提供了一系列方法来获取`HTTP`头信息，`cookies`，`HTTP`方法等等。




# Presenter Notes


 Source: [ch5.md](ch5.md) 57/86 

# JSP隐含对象

- 
`response`对象

- `response`对象是`javax.servlet.http.HttpServletResponse`类的实例。
- 当服务器创建`request`对象时会同时创建用于响应这个客户端的`response`对象。
- `response`对象也定义了处理`HTTP`头模块的接口。
- 通过这个对象，开发者们可以添加新的`cookies`，时间戳，`HTTP`状态码等等。




# Presenter Notes


 Source: [ch5.md](ch5.md) 58/86 

# JSP隐含对象

- 
`out`对象

- `out`对象是 `javax.servlet.jsp.JspWriter` 类的实例，用来在`response`对象中写入内容。
- 最初的`JspWriter`类对象根据页面是否有缓存来进行不同的实例化操作。
- 可以在`page`指令中使用`buffered='false'`属性来轻松关闭缓存。
- `JspWriter`类包含了大部分`java.io.PrintWriter`类中的方法。




# Presenter Notes


 Source: [ch5.md](ch5.md) 59/86 

# JSP隐含对象

- `JspWriter`新增了一些专为处理缓存而设计的方法。
- `JspWriter`类会抛出`IOExceptions`异常，而`PrintWriter`不会。


下表列出了用来输出`boolean`，`char`，`int`，`double`，`Srtring`，`object`等类型数据的重要方法：

方法描述`out.print(dataType dt)`输出`Type`类型的值`out.println(dataType dt)`输出`Type`类型的值然后换行`out.flush()`刷新输出流
# Presenter Notes


 Source: [ch5.md](ch5.md) 60/86 

# JSP隐含对象

- 
`session`对象

- `session`对象是 `javax.servlet.http.HttpSession` 类的实例
- 和`Java Servlets`中的`session`对象有一样的行为
- `session`对象用来跟踪在各个客户端请求间的会话。


- 
`application`对象

- `application`对象直接包装了`servlet`的`ServletContext`类的对象，是`javax.servlet.ServletContext` 类的实例
- 这个对象在`JSP`页面的整个生命周期中都代表着这个`JSP`页面
- 这个对象在`JSP`页面初始化时被创建，随着`jspDestroy()`方法的调用而被移除
- 通过向`application`中添加属性，则所有组成您`web`应用的`JSP`文件都能访问到这些属性。




# Presenter Notes


 Source: [ch5.md](ch5.md) 61/86 

# JSP隐含对象

- 
`config`对象

- `config`对象是 `javax.servlet.ServletConfig`类的实例，直接包装了`servlet`的`ServletConfig`类的对象。
- 这个对象允许开发者访问`Servlet`或者`JSP`引擎的初始化参数，比如文件路径等。




以下是`config`对象的使用方法：

1config.getServletName();

它返回包含在`<servlet-name\>`元素中的servlet名字， 注意，`<servlet-name\>`元素在 `WEB-INF\web.xml` 文件中定义。


# Presenter Notes


 Source: [ch5.md](ch5.md) 62/86 

# JSP隐含对象

- 
`pageContext` 对象

- `pageContext`对象是`javax.servlet.jsp.PageContext` 类的实例，用来代表整个`JSP`页面。 这个对象主要用来访问页面信息，同时过滤掉大部分实现细节。
- 这个对象存储了`request`对象和`response`对象的引用。 `application`对象，`config`对象，`session`对象，`out`对象可以通过访问这个对象的属性来导出。
- `pageContext`对象也包含了传给`JSP`页面的指令信息，包括缓存信息，`ErrorPage URL`,页面`scope`等。
- `PageContext`类定义了一些字段，包括`PAGE_SCOPE`，`REQUEST_SCOPE`，`SESSION_SCOPE`， `APPLICATION_SCOPE`。它也提供了40余种方法，有一半继承自`javax.servlet.jsp.JspContext` 类。 其中一个重要的方法就是`removeArribute()`，它可接受一个或两个参数。比如，`pageContext.removeArribute("attrName")`移除四个`scope`中相关属性，但是下面这种方法只移除特定scope中的相关属性：




.

1pageContext.removeAttribute("attrName",PAGE_SCOPE);

# Presenter Notes


 Source: [ch5.md](ch5.md) 63/86 

# JSP隐含对象

- `page` 对象


这个对象就是页面实例的引用。它可以被看做是整个JSP页面的代表。 page 对象就是this对象的同义词。

- exception` 对象 exception 对象包装了从先前页面中抛出的异常信息。它通常被用来产生对出错条件的适当响应。


# Presenter Notes


 Source: [ch5.md](ch5.md) 64/86 

# JSP 客户端请求


当浏览器请求一个网页时，它会向网络服务器发送一系列不能被直接读取的信息，因为这些信息是作为HTTP信息头的一部分来传送的。您可以查阅HTTP协议来获得更多的信息。 下表列出了浏览器端信息头的一些重要内容，在以后的网络编程中将会经常见到这些信息：

**信息****描述**Accept指定浏览器或其他客户端可以处理的MIME类型。它的值通常为 image/png 或 image/jpegAccept-Charset指定浏览器要使用的字符集。比如 ISO-8859-1Accept-Encoding指定编码类型。它的值通常为 gzip 或compressAccept-Language指定客户端首选语言，servlet会优先返回以当前语言构成的结果集，如果servlet支持这种语言的话。比如 en，en-us，ru等等Authorization在访问受密码保护的网页时识别不同的用户Connection表明客户端是否可以处理HTTP持久连接。持久连接允许客户端或浏览器在一个请求中获取多个文件。Keep-Alive 表示启用持久连接Content-Length仅适用于POST请求，表示 POST 数据的字节数Cookie返回先前发送给浏览器的cookies至服务器Host指出原始URL中的主机名和端口号If-Modified-Since表明只有当网页在指定的日期被修改后客户端才需要这个网页。 服务器发送304码给客户端，表示没有更新的资源If-Unmodified-Since与If-Modified-Since相反， 只有文档在指定日期后仍未被修改过，操作才会成功Referer标志着所引用页面的URL。比如，如果你在页面1，然后点了个链接至页面2，那么页面1的URL就会包含在浏览器请求页面2的信息头中User-Agent用来区分不同浏览器或客户端发送的请求，并对不同类型的浏览器返回不同的内容
# Presenter Notes


 Source: [ch5.md](ch5.md) 65/86 

# HttpServletRequest类


`request`对象是`javax.servlet.http.HttpServletRequest`类的实例。 每当客户端请求一个页面时，`JSP`引擎就会产生一个新的对象来代表这个请求。 `request`对象提供了一系列方法来获取`HTTP`信息头，包括表单数据，`cookies`，`HTTP`方法等等。


常用的获取`HTTP`信息头的方法：

**方法****描述**`Cookie[] getCookies()`返回客户端所有的`Cookie`的数组`Enumeration getAttributeNames()`返回`request`对象的所有属性名称的集合`Enumeration getHeaderNames()`返回所有`HTTP`头的名称集合`Enumeration getParameterNames()`返回请求中所有参数的集合`HttpSession getSession()`返回`request`对应的`session`对象，如果没有，则创建一个`HttpSession getSession(boolean create)`返回`request`对应的`session`对象，如果没有并且参数`create`为`true`，则返回一个新的`session`对象`Locale getLocale()`返回当前页的`Locale`对象，可以在`response`中设置`Object getAttribute(String name)`返回名称为`name`的属性值，如果不存在则返回`null`。`ServletInputStream getInputStream()`返回请求的输入流`String getAuthType()`返回认证方案的名称，用来保护`servlet`，比如 "BASIC" 或者 "SSL" 或 `null` 如果 `JSP`没设置保护措施`String getCharacterEncoding()`返回`request`的字符编码集名称`String getContentType()`返回`request`主体的MIME类型，若未知则返回`null``String getContextPath()`返回`request URI`中指明的上下文路径`String getHeader(String name)`返回`name`指定的信息头`String getMethod()`返回此`request`中的`HTTP`方法，比如 `GET`，`POST`，或`PUT``String getParameter(String name)`返回此`request`中`name`指定的参数，若不存在则返回`null``String getPathInfo()`返回任何额外的与此`request URL`相关的路径`String getProtocol()`返回此`request`所使用的协议名和版本`String getQueryString()`返回此 `request URL`包含的查询字符串`String getRemoteAddr()`返回客户端的IP地址`String getRemoteHost()`返回客户端的完整名称`String getRemoteUser()`返回客户端通过登录认证的用户，若用户未认证则返回`null``String getRequestURI()`返回`request`的`URI``String getRequestedSessionId()`返回`request`指定的`session ID``String getServletPath()`返回所请求的`servlet`路径`String[] getParameterValues(String name)`返回指定名称的参数的所有值，若不存在则返回`null``boolean isSecure()`返回`request`是否使用了加密通道，比如`HTTPS``int getContentLength()`返回`request`主体所包含的字节数，若未知的返回$-1$`int getIntHeader(String name)`返回指定名称的`request`信息头的值`int getServerPort()`返回服务器端口号
# Presenter Notes


 Source: [ch5.md](ch5.md) 66/86 

# HTTP信息头示例


使用`HttpServletRequest`类的`getHeaderNames()`方法来读取`HTTP`信息头。 这个方法以枚举的形式返回当前`HTTP`请求的头信息。


获取`Enumeration`对象后，用标准的方式来遍历`Enumeration`对象，用`hasMoreElements()`方法来确定什么时候停止，用`nextElement()`方法来获得每个参数的名字。

 1<%@pageimport="java.io.*,java.util.*"%> 2<html> 3<head> 4<title>HTTPHeaderRequestExample</title> 5</head> 6<body> 7<center> 8<h2>HTTPHeaderRequestExample</h2> 9<tablewidth="100%"border="1"align="center">10<trbgcolor="#949494">11<th>HeaderName</th><th>HeaderValue(s)</th>12</tr>13<%14EnumerationheaderNames=request.getHeaderNames();15while(headerNames.hasMoreElements()){16StringparamName=(String)headerNames.nextElement();17out.print("<tr><td>"+paramName+"</td>\n");18StringparamValue=request.getHeader(paramName);19out.println("<td> "+paramValue+"</td></tr>\n");20}21%>22</table>23</center>24</body>25</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 67/86 

# HTTP信息头示例


访问`main.jsp`，将会得到以下结果：

 1<h1>HTTP Header Request Example</h1> 2<tablewidth="100%"border="1"align="center"> 3<tbody> 4<tr><th>Header Name</th><th>Header Value(s)</th></tr> 5<tr><td>accept</td><td>*/*</td></tr> 6<tr><td>accept-language</td><td>en-us</td></tr> 7<tr><td>user-agent</td><td>Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; InfoPath.2; MS-RTC LM 8)</td></tr> 8<tr><td>accept-encoding</td><td>gzip, deflate</td></tr> 9<tr><td>host</td><td>localhost:8080</td></tr>10<tr><td>connection</td><td>Keep-Alive</td></tr>11<tr><td>cache-control</td><td>no-cache</td></tr>12</tbody>13</table>

# Presenter Notes


 Source: [ch5.md](ch5.md) 68/86 

# JSP 服务器响应

- `Response`响应对象主要将JSP容器处理后的结果传回到客户端。 可以通过`response`变量设置HTTP的状态和向客户端发送数据，如`Cookie`、`HTTP`文件头信息等。


一个典型的响应：

 1HTTP/1.1200OK  2Content-Type:text/html  3Header2:...  4...  5HeaderN:...  6(BlankLine)  7<!doctype ...> 8<html> 9<head>...</head>10<body>11... 12</body>13</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 69/86 

# JSP 服务器响应


状态行包含`HTTP`版本信息，比如`HTTP/1.1`，一个状态码，比如$200$，还有一个非常短的信息对应着状态码，比如`OK`。 在网络编程中常用到`HTTP1.1`响应头中最有用的部分：

**响应头****描述**`Allow`指定服务器支持的`request`方法（`GET`，`POST`等等）`Cache-Control`指定响应文档能够被安全缓存的情况。通常取值为 `Public`，`Private` 或`No-cache` 等等。 `Public`意味着文档可缓存，`Private`意味着文档只为单用户服务并且只能使用私有缓存。`No-cache` 意味着文档不被缓存。`Connection`命令浏览器是否要使用持久的`HTTP`连接。`close`**值** 命令浏览器不使用持久`HTTP`连接，而`keep-alive` 意味着使用持久化连接。`Content-Disposition`让浏览器要求用户将响应以给定的名称存储在磁盘中`Content-Encoding`指定传输时页面的编码规则`Content-Language`表述文档所使用的语言，比如`en`， `en-us`,，`ru`等等`Content-Length`表明响应的字节数。只有在浏览器使用持久化 (`keep-alive`) `HTTP` 连接时才有用`Content-Type`表明文档使用的`MIME`类型`Expires`指明啥时候过期并从缓存中移除`Last-Modified`指明文档最后修改时间。客户端可以 缓存文档并且在后续的请求中提供一个 `If-Modified-Since`请求头`Location`在$300$秒内，包含所有的有一个状态码的响应地址，浏览器会自动重连然后检索新文档`Refresh`指明浏览器每隔多久请求更新一次页面。`Retry-After`与$503$ (`Service Unavailable`)一起使用来告诉用户多久后请求将会得到响应`Set-Cookie`指明当前页面对应的cookie
# Presenter Notes


 Source: [ch5.md](ch5.md) 70/86 

# HttpServletResponse类

- 
`response`对象是`javax.servlet.http.HttpServletRequest`类的一个实例。 就像服务器会创建`request`对象一样，它也会创建一个客户端响应。


- 
`response`对象定义了处理创建HTTP信息头的接口。 通过使用这个对象，开发者们可以添加新的`cookie`或时间戳，还有`HTTP`状态吗等等。



## 由HttpServletResponse 类提供的设置HTTP响应头的方法：

c**方法****描述**`String encodeRedirectURL(String url)`对`sendRedirect()`方法使用的`URL`进行编码`String encodeURL(String url)`将`URL`编码，回传包含`Session ID`的`URL``boolean containsHeader(String name)`返回指定的响应头是否存在`boolean isCommitted()`返回响应是否已经提交到客户端`void addCookie(Cookie cookie)`添加指定的`cookie`至响应中`void addDateHeader(String name, long date)`添加指定名称的响应头和日期值`void addHeader(String name, String value)`添加指定名称的响应头和值`void addIntHeader(String name, int value)`添加指定名称的响应头和`int`值`void flushBuffer()`将任何缓存中的内容写入客户端`void reset()`清除任何缓存中的任何数据，包括状态码和各种响应头`void resetBuffer()`清除基本的缓存数据，不包括响应头和状态码`void sendError(int sc)`使用指定的状态码向客户端发送一个出错响应，然后清除缓存`void sendError(int sc, String msg)`使用指定的状态码和消息向客户端发送一个出错响应`void sendRedirect(String location)`使用指定的URL向客户端发送一个临时的间接响应`void setBufferSize(int size)`设置响应体的缓存区大小`void setCharacterEncoding(String charset)`指定响应的编码集（`MIME`字符集），例如UTF-8`void setContentLength(int len)`指定HTTP servlets中响应的内容的长度，此方法用来设置 HTTP Content-Length 信息头`void setContentType(String type)`设置响应的内容的类型，如果响应还未被提交的话`void setDateHeader(String name, long date)`使用指定名称和值设置响应头的名称和内容`void setHeader(String name, String value)`使用指定名称和值设置响应头的名称和内容`void setIntHeader(String name, int value)`使用指定名称和值设置响应头的名称和内容`void setLocale(Locale loc)`设置响应的语言环境，如果响应尚未被提交的话`void setStatus(int sc)`设置响应的状态码
# Presenter Notes


 Source: [ch5.md](ch5.md) 71/86 

# HTTP响应头程序示例


接下来的例子使用`setIntHeader()`方法和`setRefreshHeader()`方法来模拟一个数字时钟：

 1<%@pageimport="java.io.*,java.util.*"%> 2<html> 3<head> 4<title>AutoRefreshHeaderExample</title> 5</head> 6<body> 7<center> 8<h2>AutoRefreshHeaderExample</h2> 9<%10// 设置每隔5秒自动刷新11response.setIntHeader("Refresh",5);12// 获取当前时间13Calendarcalendar=newGregorianCalendar();14Stringam_pm;15inthour=calendar.get(Calendar.HOUR);16intminute=calendar.get(Calendar.MINUTE);17intsecond=calendar.get(Calendar.SECOND);18if(calendar.get(Calendar.AM_PM)==0)19am_pm="AM";20else21am_pm="PM";22StringCT=hour+":"+minute+":"+second+" "+am_pm;23out.println("Current Time is: "+CT+"\n");24%>25</center>26</body>27</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 72/86 

# JSP 表单处理


我们在浏览网页的时候，经常需要向服务器提交信息，并让后台程序处理。浏览器中使用 **GET** 和 **POST** 方法向服务器提交数据。

- `GET` 方法


`GET`方法将请求的编码信息添加在网址后面，网址与编码信息通过"?"号分隔。如下所示：


`http://www.w3cschool.cc/hello?key1=value1&key2=value2`


`GET方法是浏览器默认传递参数的方法，一些敏感信息，如密码等建议不使用GET方法。 用get时，传输数据的大小有限制 （注意不是参数的个数有限制），最大为$1024$字节。


# Presenter Notes


 Source: [ch5.md](ch5.md) 73/86 

# JSP 表单处理


我们在浏览网页的时候，经常需要向服务器提交信息，并让后台程序处理。浏览器中使用 **GET** 和 **POST** 方法向服务器提交数据。

- POST 方法 一些敏感信息，如密码等我们可以同过`POST`方法传递，`post`提交数据是隐式的。 `POST`提交数据是不可见的，`GET`是通过在`url`里面传递的（可以看一下你浏览器的地址栏）。


`JSP`使用 **getParameter()** 来获得传递的参数，**getInputStream()** 方法用来处理客户端的二进制数据流的请求。


# Presenter Notes


 Source: [ch5.md](ch5.md) 74/86 

# JSP 读取表单数据


JSP 以自动解析的方式处理表单数据，根据情况不同使用以下不同的方法：

- **getParameter()** : 使用 **request.getParameter()** 方法来获取表单参数的值。
- **getParameterValues()** : 获得如 **checkbox** 类（名字相同，但值有多个）的数据。 接收数组变量 ，如 **checkobx** 类型
- **getParameterNames()** :该方法可以取得所有变量的名称，该方法返回一个 **Emumeration**。
- **getInputStream()** :调用此方法来读取来自客户端的二进制数据流。


# Presenter Notes


 Source: [ch5.md](ch5.md) 75/86 

# 使用 URL 的 GET 方法实例


以下是一个简单的`URL`,并使用`GET`方法来传递`URL`中的参数：


`http://localhost:8080/main.jsp?first_name=ZARA&last_name=ALI`


以下是 **main.jsp** 文件的`JSP`程序用于处理客户端提交的表单数据，使用 **getParameter()** 方法来获取提交的数据：

 1<html> 2<head> 3<title>UsingGETMethodtoReadFormData</title> 4</head> 5<body> 6<center> 7<h1>UsingGETMethodtoReadFormData</h1> 8<ul> 9<li><p><b>FirstName:</b>10<%=request.getParameter("first_name")%>11</p></li>12<li><p><b>LastName:</b>13<%=request.getParameter("last_name")%>14</p></li>15</ul>16</body>17</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 76/86 

# 使用表单的 GET 方法实例


以下是一个简单的`HTML`表单，该表单通过`GET`方法将客户端数据提交 到`main.jsp`文件中：

 1<html> 2<body> 3<formaction="main.jsp"method="GET"> 4 First Name: <inputtype="text"name="first_name"> 5<br/> 6 Last Name: <inputtype="text"name="last_name"/> 7<inputtype="submit"value="Submit"/> 8</form> 9</body>10</html>

将以上`HTML`代码保存到`Hello.htm`文件中。 


# Presenter Notes


 Source: [ch5.md](ch5.md) 77/86 

# 使用表单的 POST 方法实例


修改代码，如下所示：

 1<html> 2<head> 3<title>UsingGETandPOSTMethodtoReadFormData</title> 4</head> 5<body> 6<center> 7<h1>UsingGETMethodtoReadFormData</h1> 8<ul> 9<li><p><b>FirstName:</b>10<%=request.getParameter("first_name")%>11</p></li>12<li><p><b>LastName:</b>13<%=request.getParameter("last_name")%>14</p></li>15</ul>16</body>17</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 78/86 

# 传递 Checkbox 数据到JSP程序


复选框 checkbox 可以传递一个甚至多个数据。


`CheckBox.htm`：

 1<html> 2<body> 3<formaction="main.jsp"method="POST"target="_blank"> 4<inputtype="checkbox"name="maths"checked="checked"/> Maths  5<inputtype="checkbox"name="physics"/> Physics  6<inputtype="checkbox"name="chemistry"checked="checked"/> 7 Chemistry  8<inputtype="submit"value="Select Subject"/> 9</form>10</body>11</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 79/86 

# 传递 Checkbox 数据到JSP程序

- 复选框 `checkbox` 可以传递一个甚至多个数据。


`main.jsp`文件代码，用于处理复选框数据：

 1<html> 2<head> 3<title>ReadingCheckboxData</title> 4</head> 5<body> 6<center> 7<h1>ReadingCheckboxData</h1> 8<ul> 9<li><p><b>MathsFlag:</b>10<%=request.getParameter("maths")%>11</p></li>12<li><p><b>PhysicsFlag:</b>13<%=request.getParameter("physics")%>14</p></li>15<li><p><b>ChemistryFlag:</b>16<%=request.getParameter("chemistry")%>17</p></li>18</ul>19</body>20</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 80/86 

# getParameterNames方法


使用 **HttpServletRequest** 的 **getParameterNames()** 来读取所有可用的表单参数，该方法可以取得所有变量的名称，该方法返回一个 **Emumeration**。

 1<%@pageimport="java.io.*,java.util.*"%> 2<html> 3<head> 4<title>HTTPHeaderRequestExample</title> 5</head> 6<body> 7<center> 8<h2>HTTPHeaderRequestExample</h2> 9<tablewidth="100%"border="1"align="center">10<trbgcolor="#949494">11<th>ParamName</th><th>ParamValue(s)</th>12</tr>13<%14EnumerationparamNames=request.getParameterNames();15while(paramNames.hasMoreElements()){16StringparamName=(String)paramNames.nextElement();17out.print("<tr><td>"+paramName+"</td>\n");18StringparamValue=request.getHeader(paramName);19out.println("<td> "+paramValue+"</td></tr>\n");20}21%>22</table>23</center>24</body>25</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 81/86 

# session对象

- 
`JSP`利用`servlet`提供的`HttpSession`接口来识别一个用户，存储这个用户的所有访问信息。


- 
默认情况下，`JSP`允许会话跟踪，一个新的`HttpSession`对象将会自动地为新的客户端实例化。




禁止会话跟踪需要显式地关掉它，通过将`page`指令中`session`属性值设为`false`来实现：

1<%@pagesession="false"%>
- `JSP`引擎将隐含的`session`对象暴露给开发者。由于提供了`session`对象，开发者就可以方便地存储或检索数据。


# Presenter Notes


 Source: [ch5.md](ch5.md) 82/86 

# `session`对象的一些重要方法：

**方法****描述**`public Object getAttribute(String name)`返回`session`对象中与指定名称绑定的对象，如果不存在则返回`null``public Enumeration getAttributeNames()`返回`session`对象中所有的对象名称`public long getCreationTime()`返回`session`对象被创建的时间， 以毫秒为单位，从1970年1月1号凌晨开始算起`public String getId()`返回`session`对象的`ID``public long getLastAccessedTime()`返回客户端最后访问的时间，以毫秒为单位，从1970年1月1号凌晨开始算起`public int getMaxInactiveInterval()`返回最大时间间隔，以秒为单位，`servlet` 容器将会在这段时间内保持会话打开`public void invalidate()`将`session`无效化，解绑任何与该`session`绑定的对象`public boolean isNew()`返回是否为一个新的客户端，或者客户端是否拒绝加入`session``public void removeAttribute(String name)`移除`session`中指定名称的对象`public void setAttribute(String name, Object value)`使用指定的名称和值来产生一个对象并绑定到`session`中`public void setMaxInactiveInterval(int interval)`用来指定时间，以秒为单位，`servlet`容器将会在这段时间内保持会话有效
# Presenter Notes


 Source: [ch5.md](ch5.md) 83/86 

# `JSP Session`应用

- 本例子描述了如何使用`HttpSession`对象来获取创建时间和最后一次访问时间。


为`request`对象关联一个新的`session`对象，如果这个对象尚未存在的话。

 1<%@pageimport="java.io.*,java.util.*"%> 2<% 3// 获取session创建时间 4DatecreateTime=newDate(session.getCreationTime()); 5// 获取最后访问页面的时间 6DatelastAccessTime=newDate(session.getLastAccessedTime()); 7Stringtitle="Welcome Back to my website"; 8IntegervisitCount=newInteger(0); 9StringvisitCountKey=newString("visitCount");10StringuserIDKey=newString("userID");11StringuserID=newString("ABCD");1213// 检测网页是否由新的访问用户14if(session.isNew()){15title="Welcome to my website";16session.setAttribute(userIDKey,userID);17session.setAttribute(visitCountKey,visitCount);18}19visitCount=(Integer)session.getAttribute(visitCountKey);20visitCount=visitCount+1;21userID=(String)session.getAttribute(userIDKey);22.setAttribute(visitCountKey,visitCount);23%>24<html>25<head>26<title>SessionTracking</title>27</head>28<body>29<center>30<h1>SessionTracking</h1>31</center>32<tableborder="1"align="center">33<trbgcolor="#949494">34<th>Sessioninfo</th>35<th>Value</th>36</tr>37<tr>38<td>id</td>39<td><%out.print(session.getId());%></td>40</tr>41<tr>42<td>CreationTime</td>43<td><%out.print(createTime);%></td>44</tr>45<tr>46<td>TimeofLastAccess</td>47<td><%out.print(lastAccessTime);%></td>48</tr>49<tr>50<td>UserID</td>51<td><%out.print(userID);%></td>52</tr>53<tr>54<td>Numberofvisits</td>55<td><%out.print(visitCount);%></td>56</tr>57</table>58</body>59</html>

# Presenter Notes


 Source: [ch5.md](ch5.md) 84/86 

# 删除Session数据

- 
移除一个特定的属性： 调用`public void removeAttribute(String name)` 方法来移除指定的属性。


- 
删除整个会话： 调用`public void invalidate()` 方法来使整个`session`无效。


- 
设置会话有效期： 调用 `public void setMaxInactiveInterval(int interval)` 方法来设置`session`超时。


- 
登出用户： 支持`servlet2.4`版本的服务器，可以调用 `logout()`方法来登出用户，并且使所有相关的`session`无效。




配置`web.xml`文件：

1<session-config>2<session-timeout>15</session-timeout>3</session-config>

# Presenter Notes


 Source: [ch5.md](ch5.md) 85/86 

# 课程QQ群





# Presenter Notes


 Source: [ch5.md](ch5.md) 86/86 



## Table of Contents

Table of Contents[JSP基础](#slide:1)[1](#slide:1)[Java Server Pages](#slide:2)[2](#slide:2)[JSP 标签](#slide:3)[3](#slide:3)[为什么使用JSP？](#slide:4)[4](#slide:4)[JSP的优势](#slide:5)[5](#slide:5)[JSP的优势](#slide:6)[6](#slide:6)[JSP 结构](#slide:7)[7](#slide:7)[Web 服务器使用JSP](#slide:8)[8](#slide:8)[Web 服务器使用JSP](#slide:9)[9](#slide:9)[Web 服务器使用JSP](#slide:10)[10](#slide:10)[Web 服务器使用JSP](#slide:11)[11](#slide:11)[JSP生命周期](#slide:12)[12](#slide:12)[JSP生命周期](#slide:13)[13](#slide:13)[JSP生命周期](#slide:14)[14](#slide:14)[JSP生命周期](#slide:15)[15](#slide:15)[JSP生命周期](#slide:16)[16](#slide:16)[JSP生命周期实例](#slide:17)[17](#slide:17)[JSP基本语法](#slide:18)[18](#slide:18)[JSP页面](#slide:19)[19](#slide:19)[脚本元素](#slide:20)[20](#slide:20)[声明](#slide:21)[21](#slide:21)[声明变量](#slide:22)[22](#slide:22)[声明方法](#slide:23)[23](#slide:23)[声明类](#slide:24)[24](#slide:24)[声明类变量、数组](#slide:25)[25](#slide:25)[表达式](#slide:26)[26](#slide:26)[JSP注释](#slide:27)[27](#slide:27)[特殊的结构](#slide:28)[28](#slide:28)[JSP指令](#slide:29)[29](#slide:29)[指令元素](#slide:30)[30](#slide:30)[指令元素](#slide:31)[31](#slide:31)[Page指令](#slide:32)[32](#slide:32)[Page指令的属性](#slide:33)[33](#slide:33)[Include指令](#slide:34)[34](#slide:34)[Include指令](#slide:35)[35](#slide:35)[Taglib指令](#slide:36)[36](#slide:36)[JSP行为](#slide:37)[37](#slide:37)[JSP 动作元素](#slide:38)[38](#slide:38)[常见的属性](#slide:39)[39](#slide:39)[`<jsp:include\>`动作元素](#slide:40)[40](#slide:40)[include动作实例](#slide:41)[41](#slide:41)[`<jsp:useBean\>`动作元素](#slide:42)[42](#slide:42)[`<jsp:setProperty\>`动作元素](#slide:43)[43](#slide:43)[`<jsp:setProperty\>`动作元素](#slide:44)[44](#slide:44)[`<jsp:setProperty\>`动作元素四个属性](#slide:45)[45](#slide:45)[`<jsp:getProperty\>`动作元素](#slide:46)[46](#slide:46)[使用Bean实例](#slide:47)[47](#slide:47)[使用Bean实例](#slide:48)[48](#slide:48)[`<jsp:forward\>` 动作元素](#slide:49)[49](#slide:49)[实例](#slide:50)[50](#slide:50)[`<jsp:plugin\>`动作元素](#slide:51)[51](#slide:51)[动作元素](#slide:52)[52](#slide:52)[动作元素](#slide:53)[53](#slide:53)[`<jsp:text\>`动作元素](#slide:54)[54](#slide:54)[`<jsp:text\>`动作元素](#slide:55)[55](#slide:55)[`JSP`隐含对象](#slide:56)[56](#slide:56)[JSP隐含对象](#slide:57)[57](#slide:57)[JSP隐含对象](#slide:58)[58](#slide:58)[JSP隐含对象](#slide:59)[59](#slide:59)[JSP隐含对象](#slide:60)[60](#slide:60)[JSP隐含对象](#slide:61)[61](#slide:61)[JSP隐含对象](#slide:62)[62](#slide:62)[JSP隐含对象](#slide:63)[63](#slide:63)[JSP隐含对象](#slide:64)[64](#slide:64)[JSP 客户端请求](#slide:65)[65](#slide:65)[HttpServletRequest类](#slide:66)[66](#slide:66)[HTTP信息头示例](#slide:67)[67](#slide:67)[HTTP信息头示例](#slide:68)[68](#slide:68)[JSP 服务器响应](#slide:69)[69](#slide:69)[JSP 服务器响应](#slide:70)[70](#slide:70)[HttpServletResponse类](#slide:71)[71](#slide:71)[HTTP响应头程序示例](#slide:72)[72](#slide:72)[JSP 表单处理](#slide:73)[73](#slide:73)[JSP 表单处理](#slide:74)[74](#slide:74)[JSP 读取表单数据](#slide:75)[75](#slide:75)[使用 URL 的 GET 方法实例](#slide:76)[76](#slide:76)[使用表单的 GET 方法实例](#slide:77)[77](#slide:77)[使用表单的 POST 方法实例](#slide:78)[78](#slide:78)[传递 Checkbox 数据到JSP程序](#slide:79)[79](#slide:79)[传递 Checkbox 数据到JSP程序](#slide:80)[80](#slide:80)[getParameterNames方法](#slide:81)[81](#slide:81)[session对象](#slide:82)[82](#slide:82)[`session`对象的一些重要方法：](#slide:83)[83](#slide:83)[`JSP Session`应用](#slide:84)[84](#slide:84)[删除Session数据](#slide:85)[85](#slide:85)[课程QQ群](#slide:86)[86](#slide:86)
## Help

HelpTable of ContentstExposéESCPresenter ViewpSource FilessSlide NumbersnToggle screen blankingbShow/hide next slidecNotes2Helph
**Generated with Darkslide 6.0.0**
Embedded image 'fig/jsp-arch.jpg' Embedded image 'fig/jsp-processing.jpg' Embedded image 'fig/jsp-processing.jpg' Embedded image 'fig/jsp-processing.jpg' Embedded image 'fig/jsp-processing.jpg' Embedded image 'fig/jsp_life_cycle.jpg' Embedded image 'fig/jsp_life_cycle.jpg' Embedded image 'fig/jsp_life_cycle.jpg' Embedded image 'fig/QQGroup.jpg'