package com.greedy.dduckleaf.refund.regist.service;

import com.greedy.dduckleaf.common.utility.DateFormatting;
import com.greedy.dduckleaf.config.BeanConfiguration;
import com.greedy.dduckleaf.config.DduckleafApplication;
import com.greedy.dduckleaf.config.JPAConfiguration;
import com.greedy.dduckleaf.refund.regist.dto.RefundingDTO;
import com.greedy.dduckleaf.refund.regist.entity.Refunding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ContextConfiguration(classes = {
        BeanConfiguration.class,
        JPAConfiguration.class,
        DduckleafApplication.class,
        com.greedy.dduckleaf.config.ContextConfiguration.class
})
class RefundingRegistServiceTest {

    @Autowired
    private RefundingRegistService service;

    @Test
    public void initTest() {

        assertNotNull(service);
    }

    @Test
    public void registRefunding_test() {
        //given
        RefundingDTO refunding = new RefundingDTO();
        refunding.setRefundingDate(DateFormatting.getDateAndTime());
        refunding.setRefundingCategoryNo(1);
        refunding.setRefundingReason("");
        refunding.setRefundingStatusNo(1);
        refunding.setProjectNo(1);
        refunding.setBankNo(1);
        refunding.setRefundingAccount("");
        refunding.setRefundingMemberName("");
        refunding.setMemberNo(1);
        refunding.setFundingInfoNo(1);

        assertDoesNotThrow( () -> service.registRefunding(refunding));
    }

}









































