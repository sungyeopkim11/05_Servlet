package edu.kh.todolist.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo implements Serializable {
                             // 직력화
	
	private String title;
	private String detail;
	private boolean complete;
	private LocalDateTime regDate;
}
