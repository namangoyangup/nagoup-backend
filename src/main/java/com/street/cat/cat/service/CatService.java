package com.street.cat.cat.service;

import com.street.cat.cat.domain.Cat;
import com.street.cat.cat.domain.CatPoint;
import com.street.cat.cat.domain.Point;
import com.street.cat.cat.dto.CatReqDTO;
import com.street.cat.cat.dto.CatResDTO;
import com.street.cat.cat.repository.CatRepository;
import com.street.cat.common.Constant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CatService {

    private final PointService pointService;
    private final CatRepository catRepository;

    @Transactional
    public ResponseEntity<String> createCat(CatReqDTO catReqDTO) {

        // 1. 고양이 생성
        Cat cat = Cat.of(catReqDTO.getAge(), catReqDTO.getGender(), catReqDTO.getProfileImage(), catReqDTO.getNeutralizationStatus(), catReqDTO.getLocation());

        // 2. 고양이 특성 등록
        catReqDTO.getCatPoints().stream().forEach(o -> {
            Point point = pointService.retrievePoint(o);
            CatPoint catPoint = CatPoint.of(cat, point, null);
            cat.addCatPoints(catPoint);
        });

        catRepository.save(cat);

        return ResponseEntity.ok().body(null);
    }

    /**
     * 고양이 정보를 조회한다.
     * @param catId
     * @return
     */
    public ResponseEntity<CatResDTO> retrieveCat(String catId) {
        Cat cat = catRepository.findById(catId).orElseThrow(IllegalArgumentException::new);

        CatResDTO resDTO = CatResDTO.builder()
                .age(cat.getAge())
                .profileImage(cat.getProfileImage())
                .gender(cat.getGender())
                .neutralizationStatus(cat.getNeutralizationStatus())
                .location(cat.getLocation())
                .build();

        return ResponseEntity.ok().body(resDTO);
    }

    /**
     * 고양이의 외향 특성 탑 5를 조회한다.
     * @param catId
     * @return
     */
    public ResponseEntity<Set<String>> retrieveCatPoint(String catId) {
        // TODO: Querydsl 조회랑 비교해 보기 & 그룹화 ID로 하기

        // 1. catId로 고양이 정보 조회
        Cat cat = catRepository.findById(catId).orElseThrow(IllegalArgumentException::new);
        // 2. 고양이가 얻은 외향 포인트 리스트 조회
        List<Point> points = cat.getCatPoints().stream().map(CatPoint::getPoint).collect(Collectors.toList());
        // 3. 포인트로 그룹화
        Map<String, Long> countMap = points.stream().collect(Collectors.groupingBy(Point::getCharacteristics, counting()));

        // 4. 개수로 정렬
        Map<String, Long> finalMapDescendingOrder = new LinkedHashMap<>();
        countMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(Constant.CAT_POINT_LIMIT)
                .forEachOrdered(e -> finalMapDescendingOrder.put(e.getKey(), e.getValue()));

        return ResponseEntity.ok().body(finalMapDescendingOrder.keySet());
    }

}
