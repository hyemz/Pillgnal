package com.pillgnal.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pillgnal.backend.domain.pill.Pill;
import com.pillgnal.backend.domain.pill.PillListResponseDto;
import com.pillgnal.backend.domain.pill.PillRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PillService {
	private final PillRepository pillRepository;

	/**
	 * 찾기 처리 ( 알약 )
	 *
	 * @param pill_name
	 * @return id(Long)
	 *
	 * @author Kimjaeseung
	 */

	public PillListResponseDto doFindPillByPill(String pWritingfront, String pShape) { // 알약의 앞쪽 문자와 모양 받아서 해당되는 알약들 모두																	// 번환
		List<Pill> pill = pillRepository.findAllByPWritingfrontAndPShape(pWritingfront, pShape);
		if (pill.size() == 0) {
			return PillListResponseDto.builder().success(false).error("약이없습니다").build();
		}
		return PillListResponseDto.builder().success(true).pillList(pill).build();
	}

	public PillListResponseDto doFindPillByPre(List<String> nameList) { // list로 알약 이름 받아서 각 알약의 특징 반환
		// 타이레놀, 레아큐탄, 솔레미오
		List<Pill> pill = pillRepository.findByPNameIn(nameList);
		
		if(pill.size() == 0) {
			return PillListResponseDto.builder().success(false).error("약이없습니다").build();
		}
		return PillListResponseDto.builder().success(true).build();
	}

}
