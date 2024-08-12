package edu.kh.memberlist.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.kh.memberlist.dao.MemberListDao;
import edu.kh.memberlist.dao.MemberListDaoImpl;
import edu.kh.memberlist.dto.Member;

public class MemberListServiceImpl implements MemberListService {

	// dao 객체 부모 참조 변수 선언
		private MemberListDao dao = null;
		
		private String[] gradeArr = {"일반", "골드", "다이아"};
		
		// 기본 생성자
		// - Member
		public void MemberServiceImpl() throws FileNotFoundException, 
		                                  ClassNotFoundException, 
		                                  IOException {
			
			dao = new MemberListDaoImpl();
		}
		
		// 회원 추가
		@Override
		public boolean addMember(String name, String phone) throws IOException {
			
			// 1) 회원 목록을 얻어와 휴대폰 번호 중복 검사
			List<Member> memberList = dao.getMembersList();
			
			for(Member member : memberList) {
				// 휴대폰 번호가 같은 경우 == 중복인 경우
				if(phone.equals(member.getPhone())) {
					return false;
				}
			}
			
			// 2) 중복이 아닌경우
			//    입력 받은 정보를 이용해 Member 객체를 생성하고
			//    DAO에 전달하여 파일에 저장
			Member member = new Member(name, phone, 0, Member.COMMON);
			
			// DAO 메서드 호출 후 결과 반환 받기
			boolean result = dao.addMember(member);
			
			return result;
		}
		
		
		// DAO에서 조회한 memberList를 그대로 반환
		// (해당 서비스 메서드는 따로 처리할 조건/기능이 없어서
		// 중간에서 전달만 해주는 역할이됨)
		@Override
		public Map<String, Object> getMemberList() {
			 
			List<Member> memberList = dao.getMembersList();
			
			int memberCount = 0;
			
			for(Member member : memberList) {
				if(member.is()) {}
			}
			
			return ;
		}
		
		// 이름 검색
		@Override
		public List<Member> selectName(String searchName) {
			
			// DAO를 이용해서 회원 전체 목록 조회
			List<Member> memberList = dao.getMembersList();
			
			// memberList에 저장된 요소(회원) 중
			// 이름이 같은 회원을 찾아서 
			// 검색 결과를 저장할 별도 List에 추가
			List<Member> searchList = new ArrayList<Member>();
			
			for(Member member : memberList) {
				
				if(member.getName().equals(searchName)) {
					searchList.add(member);
				}
			}
			
			return searchList; // 검색 결과 반환
		}
		
		
		// 금액 누적
		@Override
		public String updateAmount(Member target, int acc) throws IOException {
			
			// 이전 금액 백업 -> 출력할 문자열 만들 때 사용
			int before = target.getAmount();
			
			// 대상 회원의 금액 누적하기
			target.setAmount(before + acc);
			
			// 등급 판별
			int currentAmount = target.getAmount();
			
			int grade = 0; // 판별된 등급을 저장할 변수
			
			if(currentAmount < 100000) grade = Member.COMMON;
			else if(currentAmount < 1000000 ) grade = Member.GOLD;
			else                             grade = Member.DIAMOND;
			
			StringBuilder sb = new StringBuilder();
			sb.append(target.getName());
			sb.append(" 회원님의 누적 금액\n");
			sb.append(before + " -> " + currentAmount);
			
			// 이전 회원의 등급과
			// 새로 판별된 등급이 다른 경우
			if(target.getGrade() != grade) {
				String str
				    = String.format("\n* %s * 등급으로 변경 되셨습니다",
				    		gradeArr[grade]);
				sb.append(str);
				
				// 회원의 등급을 판별된 등급(grade)으로 변경
				target.setGrade(grade);
			}
			
			
			dao.saveFile();
				
				
			return sb.toString();
		}
		
		
		
		@Override
		public String updateMember(Member target, String updatePhone) throws IOException{
			
			String before = target.getPhone();
			
			target.setPhone(updatePhone);
			
			// 추력 문자열 만들기
			StringBuilder sb =new StringBuilder();
			
			sb.append(target.getName());
			sb.append("님의 전화번호가 변경 되었습니다\n");

			sb.append(before + " -> " + updatePhone);
			
			dao.saveFile();
			return sb.toString();
		}
		
		// 회원탈퇴
		@Override
		public String deleteMember(Member target) throws IOException {
			
			// 회원 목록을 얻어오기
			List<Member> memberList = dao.getMembersList();
			
			// 회원 목록에서 target을 제거(remove)하기
			// boolean List.remove(Object obj)
			// -> List에 저장된 요소 중 obj와 같은 요소 제거
			// * 조건 : 요소 객체가
			//          equals() 오버라이딩 되어 있어야함
			boolean result = memberList.remove(target);
			
			
			dao.saveFile(); // 탈퇴 데이터 저장
			
			return target.getName() + "회원이 탈퇴 처리 되었습니다";
		}
	
}
