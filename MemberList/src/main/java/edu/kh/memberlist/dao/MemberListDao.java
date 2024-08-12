package edu.kh.memberlist.dao;

import java.io.IOException;
import java.util.List;

import edu.kh.memberlist.dto.Member;

public interface MemberListDao {

	// 인터페이스 메서드는
		// 묵시적(임시적)으로 public abstract다
		
		
		/**
		 * DAO 객체가 가지고 있는 memberList 반환
		 * @return MemberList
		 */
		List<Member> getMembersList();

		
		/**
		 * 회원 추가
		 * @param member
		 * @return true
		 * @throws IOException
		 */
		boolean addMember(Member member) throws IOException;


	    void saveFile() throws IOException;

	
}
