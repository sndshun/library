package com.sndshun.library.vo;

import com.sndshun.library.entity.BookTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//import lombok.Data;

/**
 * @Author mr.sun
 * @Creation 2022 2022/4/7 15:32
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTagVo extends BookTag {


    /**
     * 创建者
     */

    private String createName;
    /**
     * 更新者
     */

    private String updateName;
}
