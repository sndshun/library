package com.sndshun.library.vo;

import com.sndshun.library.entity.BookType;
import lombok.Data;

/**
 * 书籍类型表(BookType)表实体类
 *
 * @author sndshun
 * @since 2022-03-26 17:16:02
 */

@Data
public class BookTypeVo extends BookType {
    /**
     * 创建者
     */

    private String createName;
    /**
     * 更新者
     */

    private String updateName;

}
