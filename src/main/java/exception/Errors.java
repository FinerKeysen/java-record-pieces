package exception;

public enum Errors {
    /**
     * 用户异常
     */
    COMMON_EXP(4000, "User not found"),
    USER_NOT_FOUND(4001, "User not found"),
    USER_DISABLED(4002, "User disabled"),
    USER_EXPIRED(4003, "User expired"),
    ERR_RSP(4004, "请求返回错误"),
    LACK_PARAMS(4005, "缺少必要参数"),
    NULL(4006, "返回结果为空"),
    UPLOAD_EMPTY(4007, "上传文件为空"),
    LACK_CONFIG(4008, "缺少配置信息"),
    EXEC_ERROR(4009, "命令执行结果错误！"),
    UNZIP_ERROR(4010, "解压文件出现异常"),
    SCRIPT_ERROR(4011, "脚本执行出现异常"),
    CHECK_ERROR(4012, "检测出现异常"),
    INSTALL_ERROR(4013, "安装过程出现异常"),
    FILE_NOT_EXIST(4014, "文件不存在"),
    FILE_CREATE_ERROR(4015, "文件或目录创建失败"),
    FILE_WRITE_ERROR(4016, "文件写入失败"),
    FILE_READ_ERROR(4017, "文件读取失败"),
    FILE_TYPE_NOT_SUPPORT(4018, "不支持的压缩类型"),
    URL_PARAM_TYPE_ERROR(4019, "url请以 'http://' 或 'https://' 或 'ftp://' 或 'file://'开头"),
    FILE_FILTER_ERROR(4020, "文件筛选失败"),

    CLUSTER_NOT_EXITS(5008, "集群不存在，请先创建集群"),
    CLUSTER_ALREADY_EXISTS(5009, "集群已存在，无需创建"),
    CLUSTER_CONFIG_FILE_READ_ERROR(50010, "读取集群配置文件异常"),
    CLUSTER_CONFIG_FILE_WRITE_ERROR(50011, "写入集群配置文件异常"),
    CLUSTER_NODE_DELETE_ERROR(50012, "删除集群节点异常"),
    CLUSTER_NODE_DUPLICATION_ERROR(50013, "集群节点已存在"),
    CLUSTER_NODE_START_ERROR(50014, "集群节点启动失败"),
    CLUSTER_NODE_STOP_ERROR(50015, "集群节点停止失败"),
    CLUSTER_NODE_EMPTY(50016, "当前集群无节点，请添加节点"),
    CLUSTER_DEPLOY_APP_ERROR(50017, "集群部署应用失败"),
    CLUSTER_EXPIRE_APP_ERROR(50018, "集群应用过期失败"),
    CLUSTER_NODE_REQUEST_ERROR(50019, "节点请求失败"),
    CLUSTER_SESSION_SERVER_ARGUMENT_ERROR(50032, "非法会话服务器地址参数"),
    CLUSTER_SESSION_TYPE_ERROR(50020, "不支持的会话服务器类型"),
    CLUSTER_SESSION_SERVER_CANNOT_CONNECT(50033, "会话服务器无法连接"),
    CLUSTER_SESSION_MANAGER_EXIST(50021, "会话服务器的Manager已存在"),
    CLUSTER_DEPLOY_APP_NODE_EMPTY(50022, "应用部署到集群，节点不能为空"),
    CLUSTER_NGINX_RELOAD_FAILED(50023, "nginx reload失败"),
    CLUSTER_NODE_NOT_EXISTS(50024, "当前集群节点不存在"),
    CLUSTER_LOADBALANCER_CREATE_FAILED(50025, "创建负载均衡器失败"),
    CLUSTER_LOADBALANCER_DELETE_FAILED(50026, "删除负载均衡器失败"),
    CLUSTER_SESSION_SERVERS_CREATE_FAILED(50027, "创建会话服务器失败"),
    CLUSTER_SESSION_SERVERS_DELETE_FAILED(50028, "删除会话服务器失败"),
    CLUSTER_AGENT_NODE_DUPLICATION_ERROR(50029, "代理节点已存在"),
    CLUSTER_AGENT_NODE_NOT_EXISTS(50030, "代理节点不存在"),
    CLUSTER_AGENT_REQUEST_ERROR(50031, "请求失败"),
    CLUSTER_SESSION_REDIS_SERVER_ONLY_ONE(50034, "Redis会话服务器暂时只支持一个接入点"),

    OSGI_CONSOLE_CONNECT_FAILED(60001, "OSGI控制端口连接失败"),
    OSGI_CONSOLE_GET_RESULT_FAILED(60002, "读取OSGI console输出失败"),
    OSGI_UNKNOWN_OPERTYPE(60003, "未知OSGI程序操作类型"),
    OSGI_OPERTYPE_FAILED(60004, "操作OSGI程序失败"),
    OSGI_DOWNLOAD_FAILED(60005, "下载OSGI程序失败"),
    OSGI_DEPLOY_TYPE_UNKNOWN(60006, "不支持该类型的OSGI程序部署"),
    OSGI_EBA_PKG_WITHOUT_MF_FILE(60007, "eba文件格式错误，找不到APPLICATION.MF文件"),
    CONNECTOR_PORT_NOT_EXIST(60008, "找不到port对应的connector"),

    LOGIN_PARAMS_REQUIRED(65001, "登录参数缺少"),
    HTTP_REQUEST_ERROR(65002, "HTTP请求失败"),

    DBCP_MONITOR_UNABLE(70003, "数据源监测总开关或单项开关未开启"),
    DBCP_NAME_ALREADY_USED_BY_OTHER_METHOD(70004, "该数据源名称已存在，如果要更换名称，请安全删除该连接池并重新创建新的连接池对象!"),
    DBCP_NAME_NONEXISTED(70005, "数据源连接池不存在"),
    DBCP_NAME_DUPLICATE(70006, "数据源连接池名已存在"),
    DBCP_LACK_CONFIG_ATTRIBUTE(70007, "缺少数据源配置属性 "),
    DBCP_CONFIG_FILE_NOT_FOUND(70008, "找不到数据源配置文件"),
    DBCP_POOL_CONNECTION_CHECK_ERROR(70009, "检测数据源连接池连通性失败"),
    DBCP_JDBC_DIRECT_CONNECT_ERROR(70010, "创建数据源连接池时数据库连接测试失败，请检查连接地址、用户名及密码"),
    DBCP_CONFIG_FILE_READ_ERROR(70011, "读取数据源配置文件异常"),
    DBCP_CONFIG_FILE_WRITE_ERROR(70012, "写入数据源配置文件异常"),
    DBCP_EXTRA_CONFIG_FILE_WRITE_ERROR(70013, "写入外部引入的数据源配置文件异常"),
    DBCP_TYPE_INCONSISTENT_IN_MULTIDS_LIST(70014, "多数据源中单数据源的类型不一致"),
    MAIL_NAME_EXIST(70015, "已存在该标识的mail配置"),
    MAIL_NAME_NOT_EXIST(70016, "不存在该标识的mail配置"),
    SNMP_NAME_EXIST(70021, "已存在该标识的snmp配置"),
    SNMP_NUNKNOWN_TYPE(70022, "未知SNMP版本类型"),
    CONNECTOR_PORT_EXIST(70023, "已存在port对应的connector"),
    PARAM_ERROR(70024, "参数错误"),
    SSL_FILE_PARAM_ERROR(70025, "SSL证书路径异常"),
    APP_BASE_EXIST_ERROR(70026, "appBase已被其他Host使用"),
    VALVE_RELOAD_ERROR(70027, "实时生效更新异常"),

    LOG_LEVEL_ENUM_NOT_FOUND(80001, "错误的日志级别"),
    UNKNOWN_TYPE(5001, "未知类型");

    private final int code;
    private final String msg;

    Errors(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodedException exception() {
        return new CodedException(this.code, this.msg);
    }

    public CodedException exception(String message) {
        return new CodedException(this.code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
