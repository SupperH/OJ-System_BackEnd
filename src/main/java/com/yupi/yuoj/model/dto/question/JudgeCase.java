package com.yupi.yuoj.model.dto.question;

import lombok.Data;

/**
 * 题目用例  为了更方便的处理json字段中的某一个字段需要给对应json字段编写独立的类
 */
@Data
public class JudgeCase {
    //输入用例
    private String input;

    //输出用例
    private String output;
}
