# HTML 多格式文档转换工具

一个基于 Spring Boot 的综合 Web 应用程序，用于将 HTML 文档转换为 Markdown 和 Word (.docx) 格式。

## 功能特性

<img width="331" height="439" alt="image" src="https://github.com/user-attachments/assets/1e35ab53-6e08-45ce-a54c-9d95d48b23c9" />

**把老师给的html格式课件文件转换成word、md格式，方便进行AI整理笔记**

**把老师给的html格式课件文件转换成word、md格式，方便进行AI整理笔记** 

<img width="1343" height="425" alt="image" src="https://github.com/user-attachments/assets/41a2452e-9a99-4a47-acd9-7c1121a2b8f1" />
<img width="1920" height="970" alt="image" src="https://github.com/user-attachments/assets/018207fd-93eb-4635-8726-33335ae4a217" />
<img width="1049" height="546" alt="image" src="https://github.com/user-attachments/assets/d5b3e858-beb8-4db6-90ea-948b783801aa" />

- **Web 界面**: 用户友好的仪表盘，用于文件上传和任务管理。
- **用户认证**: 安全的登录和注册系统。
- **异步处理**: 带有重试逻辑的非阻塞转换任务。
- **支持格式**:
    - HTML 转 Markdown
    - HTML 转 Word (.docx)
- **REST API**: 功能完整的 API，支持程序化访问。

## 项目结构

```
├── src/
│   ├── main/
│   │   ├── java/com/example/htmlconverter/
│   │   │   ├── cli/          # 命令行入口 (Main.java)
│   │   │   ├── config/       # Spring 配置 (Security)
│   │   │   ├── controller/   # REST 控制器
│   │   │   ├── converter/    # 格式转换器 (Markdown, Word)
│   │   │   ├── core/         # 核心接口
│   │   │   ├── model/        # JPA 实体
│   │   │   ├── repository/   # 数据访问层
│   │   │   └── service/      # 业务逻辑
│   │   └── resources/
│   │       ├── static/       # 前端资源 (HTML, CSS, JS)
│   │       └── application.properties
│   └── test/                 # 单元和集成测试
├── pom.xml                   # Maven 构建配置
└── README.md                 # 项目文档
```

## 环境要求

- Java 17 或更高版本
- Maven 3.6+

## 安装与运行

1. **构建项目**:
   ```bash
   mvn clean package
   ```

2. **运行应用**:
   ```bash
   java -jar target/html-converter-1.0-SNAPSHOT.jar
   ```

3. **访问 Web UI**:
   在浏览器中打开 [http://localhost:8080](http://localhost:8080)。

## API 使用说明

- **登录**: `POST /api/auth/login`
- **注册**: `POST /api/auth/register`
- **转换**: `POST /api/convert` (Multipart 文件上传)
- **获取任务**: `GET /api/tasks`

## 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/amazing-feature`)
3. 提交更改 (`git commit -m 'feat: Add some amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 提交 Pull Request
