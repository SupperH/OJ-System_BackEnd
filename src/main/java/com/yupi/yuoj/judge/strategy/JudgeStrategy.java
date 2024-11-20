package com.yupi.yuoj.judge.strategy;

import com.yupi.yuoj.judge.codesandbox.model.JudgeInfo;

public interface JudgeStrategy {

    //执行判题
    JudgeInfo doJudge(JudgeContext judgeContext);
}
