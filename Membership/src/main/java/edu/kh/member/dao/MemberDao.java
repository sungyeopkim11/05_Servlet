package edu.kh.member.dao;

import java.io.IOException;
import java.util.List;

import edu.kh.member.dto.Member;

public interface MemberDao {

	
	// 인터페이스 메서드는 
		// 묵시적(암묵적)으로 public abstract다!!
		
		/**
		 * DAO 객체가 가지고 있는 memberList 반환
		 * @return memberList
		 */
		List<Member> getMemberList();

		
		/**
		 * 회원 추가 
		 * @param member
		 * @return true
		 * @throws IOException
		 */
		boolean addMember(Member member) throws IOException;
		
		
		/** index 번째 회원 반환
		 * @param index
		 * @return member
		 */
		Member getMember(int index);
		
		
		/** 
		 * 파일 저장
		 */
		void saveFile() throws IOException;
	
	
	
	
	
	
	
	
	

}
