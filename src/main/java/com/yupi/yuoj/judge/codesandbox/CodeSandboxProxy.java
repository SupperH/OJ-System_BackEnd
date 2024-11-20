package com.yupi.yuoj.judge.codesandbox;

import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

//增强代理类
@Slf4j
public class CodeSandboxProxy implements CodeSandbox{

    private final CodeSandbox codesandbox;

    //通过构造器创建沙箱对象，创建代理对象
    public CodeSandboxProxy(CodeSandbox codeSandbox){
        this.codesandbox = codeSandbox;
    }
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息："+executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codesandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息："+executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
