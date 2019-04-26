//package cn.netkiller;
//
//import cn.netkiller.entity.secondary.Member;
//import cn.netkiller.repository.secondary.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
// 
//@Service
//public class MemberService {
// 
//    @Autowired
//    private MemberRepository memberRepository;
// 
//    @Transactional("transactionManagerSecondary") //非主事务管理器，必须写明用的是哪个，不然用的别就是默认数据源的事务管理器，就无效。
//    public void add(Member member){
//        MemberRepository.delete(member);
//        memberRepository.save(member);
//    }
//}