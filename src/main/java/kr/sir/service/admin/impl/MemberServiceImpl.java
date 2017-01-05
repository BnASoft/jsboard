package kr.sir.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.Member;
import kr.sir.domain.MemberGroupCount;
import kr.sir.domain.Point;
import kr.sir.domain.repository.admin.MemberEmRepository;
import kr.sir.domain.repository.admin.MemberRepository;
import kr.sir.domain.repository.admin.PointEmRepository;
import kr.sir.domain.repository.admin.PointRepository;
import kr.sir.service.admin.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	
	private MemberRepository memberRepository;
	private MemberEmRepository memberEmRepository;
	private PointRepository pointRepository;
	private PointEmRepository pointEmRepository;
	
	@Autowired
	public void setMemberRepository(MemberRepository memberRepository){
		this.memberRepository=memberRepository;
	}
	@Autowired
	public void setMemberEmRepository(MemberEmRepository memberEmRepository){
		this.memberEmRepository=memberEmRepository;
	}
	@Autowired
	public void setPointRepository(PointRepository pointRepository) {
		this.pointRepository = pointRepository;
	}
	@Autowired
	public void setPointEmRepository(PointEmRepository pointEmRepository){
		this.pointEmRepository=pointEmRepository;
	}	
	
	//전체멤버목록
	@Override
	public List<Member> getAllMembers() {		
		return memberRepository.findAll();
	}
	
	//총회원수
	@Override
	public long getCountAllMembers(){		
		return memberRepository.count();
	}
	
	//탈퇴회원수
	@Override
	public String getCountRetiredMembers(){		
		return memberRepository.getCountRetiredMembers();
	} 
	
	//차단회원수
	@Override
	public String getCountBlockedMembers(){
		return memberRepository.getCountBlockedMembers();
	}
	
	@Override
	public List<MemberGroupCount>getAllMembersList(String prefix) {
		// TODO Auto-generated method stub
		return memberEmRepository.getAllMembersList(prefix);
	}
	
	//아이디로 회원 전체 정보 셀렉트
	@Override
	public Member getOneMemer(String memberId) {		
		return memberRepository.findByMemberId(memberId);
	}
	
	//관리자가 회원추가
	public void adminSavesMember(Member member){
		memberRepository.save(member);
	}
	
	//괸리자가 회원 추방
	@Override
	public void adminDeletesMember(int id) {
		memberRepository.delete(id);
		
	}
	//관리자가 회원 수정
	@Override
	public void adminUpdatesMember(Member member) {
		
	}
	
	
	//포인트관리 건수
	@Override
	public long getCountPointlist() {		
		return pointRepository.count();
	}
	
	//모든 회원  포인트 합계
	public int getTotalPoint(){
		return pointRepository.getTotalPoint();
	}
	
	//모든 포인트 내역
	@Override
	public List<Point> getAllPointContent() {	
		return pointRepository.findAll();
	}
	
	
	
	
}
