package com.yupi.yuoj.judge.codesandbox;

import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.yuoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CodeSandboxTest {

    //读取配置文件中的代码沙箱配置 :example是定义默认值，意思是如果yml的type没有定义那么默认使用example
    @Value("${codesandbox.type:example}")
    private String type;

    //通过配置文件读取配置调用沙箱
    @Test
    void executeCodeByValue() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);

        String code = "int main() {}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2","3 4");

        //因为我们在ExecuteCodeRequest中使用了build注解，所以我们可以通过下面的方法直接初始化对象的值，这里用的就是构造者模式
        /*链式调用 初始化对象*/
        ExecuteCodeRequest  executeCodeRequest = ExecuteCodeRequest.builder().
                code(code).
                language(language).
                inputList(inputList).
                build();

        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

        //使用代理类的方式，打印前后日志
        @Test
    void executeCodeByproxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);

        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "\n" +
                "\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "\n" +
                "        System.out.println(\"结果\" + (a + b));\n" +
                "\n" +
                "    }\n" +
                "}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2","3 4");

        //因为我们在ExecuteCodeRequest中使用了build注解，所以我们可以通过下面的方法直接初始化对象的值，这里用的就是构造者模式
        /*链式调用 初始化对象*/
        ExecuteCodeRequest  executeCodeRequest = ExecuteCodeRequest.builder().
                code(code).
                language(language).
                inputList(inputList).
                build();

        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
}