package com.linkwechat.web.controller.wecom;

import com.linkwechat.common.core.controller.BaseController;
import com.linkwechat.common.core.page.TableDataInfo;
import com.linkwechat.wecom.domain.WeGroup;
import com.linkwechat.wecom.domain.WeGroupMember;
import com.linkwechat.wecom.service.IWeGroupMemberService;
import com.linkwechat.wecom.service.IWeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 群组相关
 * @Author: Robin
 * @Description:
 * @Date: create in 2020/9/21 0021 23:53
 */

@RestController
@RequestMapping("/wecom/group/chat")
public class WeGroupController extends BaseController {
    @Autowired
    private IWeGroupService weGroupService;

    @Autowired
    private IWeGroupMemberService weGroupMemberService;

    @PreAuthorize("@ss.hasPermi('wecom:group:list')")
    @GetMapping({"/list"})
    public TableDataInfo list(WeGroup weGroup) {
        startPage();
        List<WeGroup> list = this.weGroupService.selectWeGroupList(weGroup);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('wecom:member:list')")
    @GetMapping({"/members"})
    public TableDataInfo list(WeGroupMember weGroupMember) {
        startPage();
        List<WeGroupMember> list = this.weGroupMemberService.selectWeGroupMemberList(weGroupMember);
        return getDataTable(list);
    }
}