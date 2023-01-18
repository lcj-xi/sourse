package org.xi.studentmanagesystem.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xi.studentmanagesystem.exception.REnum;
import org.xi.studentmanagesystem.utils.RUtil;
import org.xi.studentmanagesystem.vo.R;

@RestController
public class Error {

    @RequestMapping("errors")
    public R error(){
        return RUtil.error(REnum.SESSION_ERROR);
    }
}
