package com.greedy.dduckleaf.funding.regist.service;

import com.greedy.dduckleaf.config.BeanConfiguration;
import com.greedy.dduckleaf.config.DduckleafApplication;
import com.greedy.dduckleaf.config.JPAConfiguration;
import com.greedy.dduckleaf.funding.dto.*;
import com.greedy.dduckleaf.refund.regist.entity.Funding;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ContextConfiguration(classes = {
        BeanConfiguration.class,
        com.greedy.dduckleaf.config.ContextConfiguration.class,
        DduckleafApplication.class,
        JPAConfiguration.class
})
class FundingRegistServiceTest {

    @Autowired
    private FundingRegistService service;

    @Test
    public void initTest() {
        assertNotNull(service);
    }


    @Test
    @DisplayName("프로젝트 정보 조회")
    public void findProjectFundingInfo_test() {

    }

    @Test
    @DisplayName("은행목록과 회원정보 조회 테스트")
    public void findBankAndUserInfo_test() {

        //given
        String memberId = "USER01";
        int projectNo = 1;
        //when
        FundingRegistInfoDTO fundingRegistInfo = service.findBankAndUserInfo(memberId, projectNo);
        List<BankDTO> bankList = fundingRegistInfo.getBankList();
        MemberDTO member = fundingRegistInfo.getMember();

        //then
        assertNotNull(fundingRegistInfo);
        assertNotNull(bankList);
        assertNotNull(member);

        bankList.forEach(System.out::println);
        System.out.println("member = " + member);
    }

    @Test
    @DisplayName("펀딩 등록 테스트")
    public void registFunding_test() {

        //given
        FundingRegistDTO registDTO = new FundingRegistDTO();
        registDTO.setMemberNo(1);
        registDTO.setTotalFunding(3500);
        registDTO.setRewardTotal(1000);
        registDTO.setRewardPrice(1000);
        registDTO.setRewardAmount(1);
        registDTO.setDonate(0);
        registDTO.setShippingFee(2500);
        registDTO.setExtraShippingFee(4000);
        registDTO.setProjectNo(1);

        //then
        assertDoesNotThrow( () -> service.registFunding(registDTO));

    }

    @Test
    @DisplayName("프로젝트 종료일 조회 테스트")
    public void findProjetEndDate_test() {

        //given
        int projectNo = 1;
        String endDate = "2022-06-10";

        //when
        String result = service.findProjetEndDate(projectNo);

        //then
        assertEquals(endDate, result);

    }

    @Test
    @DisplayName("배송지 정보 변경 테스트")
    public void modifyShippingAddress_test() {
        ShippingAddressDTO address = new ShippingAddressDTO();
        address.setZipCode("1");

        assertDoesNotThrow( () -> service.modifyShippingAddress(address));

    }

    @Test
    @DisplayName("환불계좌 변경 테스트")
    public void modifyRefundAccount_test() {
        FundingDTO funding = new FundingDTO();
        funding.setFundingInfoNo(1);
        funding.setRefundAccount("000");
        funding.setRefundName("환불계좌 변경 테스트");
        funding.setRefundBankCode(new BankDTO(1, ""));

        assertDoesNotThrow( () -> service.modifyRefundAccount(funding));
    }

    @Test
    @DisplayName("한 프로젝트의 펀딩등록 시 필요 정보 조회 테스트")
    public void findProjectInfoById_test() {
        int projectNo = 1;

        ProjectDTO projectInfoDTO = service.findProjectFundingInfo(projectNo);
        
        assertNotNull(projectInfoDTO);
        System.out.println("projectInfoDTO = " + projectInfoDTO);
    }

}