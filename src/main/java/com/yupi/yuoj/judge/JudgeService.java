package com.yupi.yuoj.judge;

import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.yuoj.model.entity.QuestionSubmit;
import com.yupi.yuoj.model.vo.QuestionSubmitVO;

//判题服务
public interface JudgeService {

    //判题
    QuestionSubmit doJudge(long questionSubmitId);
}
