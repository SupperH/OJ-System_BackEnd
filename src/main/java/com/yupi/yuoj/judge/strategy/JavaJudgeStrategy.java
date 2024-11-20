package com.yupi.yuoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.yupi.yuoj.model.dto.question.JudgeCase;
import com.yupi.yuoj.model.dto.question.JudgeConfig;
import com.yupi.yuoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.yuoj.model.entity.Question;
import com.yupi.yuoj.model.enums.JudgeMessageEnum;

import java.util.List;

//java判题策略
public class JavaJudgeStrategy implements JudgeStrategy {

    //执行判题
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        //从上下文获取传递的参数
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputlist();
        List<String> outPutList = judgeContext.getOutputlist();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCasesList = judgeContext.getJudgeCasesList();

        //判断题目限制
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();

        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);

        //根据沙箱执行结果，是遏制题目的判题状态和信息 默认是accepted 在下面的if中去修改
        JudgeMessageEnum judgeMessageEnum = JudgeMessageEnum.ACEEPTED;
        //1.先判断沙箱执行结果输出数量是否和预期输出数量相等
        if(outPutList.size() != inputList.size()){
            judgeMessageEnum = JudgeMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeMessageEnum.getValue());
            return judgeInfoResponse;
        }
        //2.判断每一项输出和预期输出是否相等
        for(int i = 0;i<judgeCasesList.size();i++){
            //拿到题目的输出用例进行对比
            JudgeCase judgeCase = judgeCasesList.get(i);
            if(judgeCase.getOutput().equals(outPutList.get(i))){
                judgeMessageEnum = JudgeMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }

        //获取题目的限制并且转换成实体类的形式获取值
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long needMemoryLimit = judgeConfig.getMemoryLimit();
        Long needTimeLimit = judgeConfig.getTimeLimit();

        if(memory > needMemoryLimit){
            judgeMessageEnum = JudgeMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeMessageEnum.getValue());
            return judgeInfoResponse;
        }

        //java程序本身需要额外执行10秒
        long JAVA_PROGRAM_TIME_COST=10000L;
        if((time-JAVA_PROGRAM_TIME_COST) > needTimeLimit){
            judgeMessageEnum = JudgeMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeMessageEnum.getValue());
            return judgeInfoResponse;
        }

        judgeInfoResponse.setMessage(judgeMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
