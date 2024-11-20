package com.yupi.yuoj.judge.codesandbox;


import com.yupi.yuoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.yupi.yuoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.yupi.yuoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

//代码沙箱工厂，通过传入的不同参数创建不同的沙箱实现类
/*这里使用的是静态代码沙箱工厂 如果确定代码沙箱示例不会出现线程安全问题，可复用，那么可以使用单例工厂模式*/
public class CodeSandboxFactory {

    //创建代码沙箱
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            case "example":
                return new ExampleCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
