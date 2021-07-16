package com.street.cat.cat.service;

import com.street.cat.cat.domain.Point;
import com.street.cat.cat.repository.PointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PointService {
     private final PointRepository pointRepository;

    /**
     * 포인트 객체를 반환한다.
     * 만약 해당 특성이 없을 시 생성한다.
     * @param Characteristics
     * @return
     */
     public Point retrievePoint(String Characteristics){
         Optional<Point> byCharacteristics = pointRepository.findByCharacteristics(Characteristics);
         Point point;

         if(byCharacteristics.isEmpty()){
             point = Point.of(Characteristics);
             pointRepository.save(point);
         } else {
             point = byCharacteristics.get();
         }

         return point;
     }
}
